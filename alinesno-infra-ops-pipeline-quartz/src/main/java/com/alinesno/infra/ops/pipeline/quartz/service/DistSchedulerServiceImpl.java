package com.alinesno.infra.ops.pipeline.quartz.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alinesno.infra.ops.pipeline.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.pipeline.dto.TaskInfoDto;
import com.alinesno.infra.ops.pipeline.dto.ValueAttributeDto;
import com.alinesno.infra.ops.pipeline.entity.JobEntity;
import com.alinesno.infra.ops.pipeline.entity.TransEntity;
import com.alinesno.infra.ops.pipeline.enums.JobStatusEnums;
import com.alinesno.infra.ops.pipeline.quartz.event.JobAlarmEvent;
import com.alinesno.infra.ops.pipeline.quartz.event.JobAlarmEventPublisher;
import com.alinesno.infra.ops.pipeline.quartz.utils.SystemUtils;
import com.alinesno.infra.ops.pipeline.service.IDistSchedulerService;
import com.alinesno.infra.ops.pipeline.service.IExecutorScriptService;
import com.alinesno.infra.ops.pipeline.service.IJobService;
import com.alinesno.infra.ops.pipeline.service.ITransService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DistSchedulerServiceImpl 类是分布式调度任务服务实现类。
 * 该类实现了IDistSchedulerService接口，用于创建Cron定时任务。
 *
 * 作者：luoxiaodong
 * 版本：1.0.0
 */
@Service
public class DistSchedulerServiceImpl implements IDistSchedulerService {

    private static final Logger log = LoggerFactory.getLogger(DistSchedulerServiceImpl.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ITransService transService;

    @Autowired
    private IJobService jobService;

    @Autowired
    private JobAlarmEventPublisher alarmEventPublisher;

    @Autowired
    private IExecutorScriptService executorScriptService;

    @Value("${alinesno.data.pipeline.workspacePath}")
    private String workspacePath;

    @Override
    public void createCronJob(TaskInfoDto taskInfoDto, JobEntity jobEntity, List<TransEntity> listTrans) throws SQLException, IOException {
        // 使用Comparator接口创建一个比较器，按照order_step字段的值从小到大排序
        Comparator<TransEntity> comparator = Comparator.comparingInt(TransEntity::getOrderStep);
        Map<String, Object> contextMap = new HashMap<>();

        // 使用Collections工具类的sort方法对列表进行排序
        listTrans.sort(comparator);

        File sourceFile = null;

        int step = 1;
        long readDataCount = 0;
        for (TransEntity trans : listTrans) {
            log.debug("--->>>>>>>>>>> step = {}, trans = {}", step++, JSONObject.toJSONString(trans));

//            if(JobStatusEnums.COMPLETED.getStatus().equals(trans.getStatus())){
//               continue;
//            }

            // 更新任务状态
            trans.setStatus(JobStatusEnums.PROCESSING.getStatus());
            transService.update(trans);

            try {
                // 处理定时任务
                ExecutorScriptDto executorScriptDto = new ExecutorScriptDto();
                executorScriptDto.setScriptContent(trans.getContextScript());
                executorScriptDto.setType(trans.getPluginName());

                // 脚本不为空
                if (StringUtils.isNotBlank(trans.getTransContext())) {
                    log.debug("--->>>>>> trans = {}", trans.getTransContext());
                    executorScriptDto.setAttributes(JSONArray.parseArray(trans.getTransContext(), ValueAttributeDto.class));
                }

                executorScriptService.run(executorScriptDto, contextMap);

                // 更新任务状态
                trans.setStatus(JobStatusEnums.COMPLETED.getStatus());
                transService.update(trans);

            } catch (Exception e) {
                trans.setStatus(JobStatusEnums.FAILED.getStatus());
                transService.update(trans);

                JobAlarmEvent alarmEvent = new JobAlarmEvent(trans.getId());
                alarmEvent.setStep(step);
                alarmEvent.setTransName(trans.getName());

                alarmEventPublisher.publishEvent(alarmEvent);

                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void createCronJob(Long jobId) throws SQLException, IOException {
        JobEntity jobEntity = jobService.getById(jobId);
        TaskInfoDto taskInfoDto = JSONObject.parseObject(jobEntity.getJobContext(), TaskInfoDto.class);

        List<TransEntity> transList = transService.list(new LambdaQueryWrapper<TransEntity>().eq(TransEntity::getJobId, jobId));

        // 使用Comparator接口创建一个比较器，按照order_step字段的值从小到大排序
        Comparator<TransEntity> comparator = Comparator.comparingInt(TransEntity::getOrderStep);

        // 使用Collections工具类的sort方法对列表进行排序
        transList.sort(comparator);

        StopWatch stopWatch = new StopWatch("func2");
        stopWatch.start("phase1");
        log.info("开始进行数据查询.");

        this.createCronJob(taskInfoDto, jobEntity, transList);

        stopWatch.stop();
        log.info("转换成流式查询时间:{}", SystemUtils.formatMilliseconds(stopWatch.getLastTaskTimeMillis()));
    }
}
