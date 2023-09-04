package com.alinesno.infra.ops.pipeline.api.provider;

import com.alinesno.infra.common.core.context.SpringContext;
import com.alinesno.infra.ops.pipeline.service.IDistSchedulerService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;

/**
 * DataTransferJob是一个执行数据转换任务的Quartz作业。
 * 该作业实现了Quartz的Job接口，并在execute方法中执行数据转换逻辑。
 * 它使用SpringContext获取IDistSchedulerService实例来创建Cron定时任务。
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class DataTransferJob implements Job {

    private static final Logger log = LoggerFactory.getLogger(DataTransferJob.class) ;

    @Override
    public void execute(JobExecutionContext context) {
        // 从作业上下文获取作业数据映射
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();

        // 从作业数据映射中获取任务ID
        Long jobId = jobDataMap.getLong("jobId");
        log.debug("jobId = {}", jobId);

        // 使用Spring上下文获取IDistSchedulerService实例
        IDistSchedulerService distSchedulerService = SpringContext.getBean(IDistSchedulerService.class);

        try {
            // 调用IDistSchedulerService的createCronJob方法创建Cron定时任务
            distSchedulerService.createCronJob(jobId);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }


}
