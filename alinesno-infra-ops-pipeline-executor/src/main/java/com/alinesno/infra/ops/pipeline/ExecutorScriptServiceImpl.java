package com.alinesno.infra.ops.pipeline;

import com.alinesno.infra.ops.pipeline.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.pipeline.dto.TaskInfoDto;
import com.alinesno.infra.ops.pipeline.dto.TaskStepInfo;
import com.alinesno.infra.ops.pipeline.entity.JobEntity;
import com.alinesno.infra.ops.pipeline.entity.TransEntity;
import com.alinesno.infra.ops.pipeline.service.IExecutorScriptService;
import com.alinesno.infra.ops.pipeline.service.IJobService;
import com.alinesno.infra.ops.pipeline.service.ITransService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ExecutorScriptServiceImpl类是运行脚本的实现类，实现了IExecutorScriptService接口。
 * 它用于运行脚本任务。
 */
@Service
public class ExecutorScriptServiceImpl implements IExecutorScriptService {

    private static final Logger log = LoggerFactory.getLogger(ExecutorScriptServiceImpl.class);

    private static final Gson gson = new Gson();

    @Autowired
    private IJobService jobService ;

    @Autowired
    private ITransService transService ;

    @Override
    public void run(ExecutorScriptDto executorScriptDto, Map<String, Object> contextMap) {
        // 获取任务类型
        String type = executorScriptDto.getType();

        log.debug("运行脚本服务: {}", executorScriptDto);
        // 确保任务类型不为空
        Assert.hasLength(type, "任务类型为空.");

        // 根据任务类型获取相应的插件并执行脚本
        ScriptExecutorContext.getRunnerPlugin(type).run(executorScriptDto , contextMap);
    }

    /**
     * 任务信息实体
     * @param taskInfoDto 执行器脚本DTO
     * @return
     */
    @Override
    public JobEntity analyzeTaskInfo(TaskInfoDto taskInfoDto) {
        // 在此方法中实现分析任务信息的逻辑

        // 创建一个Job
        JobEntity jobEntity = new JobEntity() ;
        jobEntity.setName(taskInfoDto.getName());
        jobEntity.setDescription(taskInfoDto.getDescribe());
        jobEntity.setJobContext(gson.toJson(taskInfoDto));
        jobService.save(jobEntity) ;

        // 创建任务节点
        List<TransEntity> listTrans = new ArrayList<>() ;

        // 处理插件
         genPluginsTrans(taskInfoDto.getWorkflow() , listTrans , jobEntity)  ;

        transService.saveBatch(listTrans) ;

        return jobEntity ;
    }

    /**
     * 处理插件任务
     *
     * @param plugins
     * @param listTrans
     * @param jobEntity
     */
    private void genPluginsTrans(List<TaskStepInfo> plugins, List<TransEntity> listTrans, JobEntity jobEntity) {

        if(!plugins.isEmpty()){

            for(TaskStepInfo p : plugins){
                TransEntity t = new TransEntity() ;

                t.setJobId(jobEntity.getId());

                t.setOrderStep(listTrans.size()+1);
                t.setType(p.getType());
                t.setName(p.getName());
                t.setTransContext(gson.toJson(p.getPlugin().getPluginAttributes()));
                t.setPluginName(p.getPlugin().getName());
                t.setContextScript(p.getPlugin().getContextScript());
                t.setParentStep(p.getParentStep());

                listTrans.add(t);
            }
        }
    }

}
