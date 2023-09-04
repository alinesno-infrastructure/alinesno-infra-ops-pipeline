package com.alinesno.infra.ops.pipeline.quartz.config;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.concurrent.Executor;

/**
 * SchedulerConfig 类是调度器配置类。
 * 该类用于配置调度器和相关的线程池。
 *
 * 作者：luoxiaodong
 * 版本：1.0.0
 */
@Configuration
public class SchedulerConfig {

    @Autowired
    private DataSource dataSource;

    /**
     * 获取调度器对象
     *
     * @return 调度器对象
     * @throws Exception
     */
    @Bean
    public Scheduler scheduler() throws Exception {
        return schedulerFactoryBean().getScheduler();
    }

    /**
     * 获取SchedulerFactoryBean对象
     *
     * @return SchedulerFactoryBean对象
     * @throws IOException
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setSchedulerName("Cluster_Scheduler");
        factory.setDataSource(dataSource);
        factory.setApplicationContextSchedulerContextKey("applicationContext");
        factory.setTaskExecutor(schedulerThreadPool());
        factory.setStartupDelay(0); // 延迟0s执行
        return factory;
    }

    /**
     * 配置调度器线程池
     *
     * @return Executor对象
     */
    @Bean
    public Executor schedulerThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors());
        executor.setQueueCapacity(Runtime.getRuntime().availableProcessors());
        return executor;
    }
}
