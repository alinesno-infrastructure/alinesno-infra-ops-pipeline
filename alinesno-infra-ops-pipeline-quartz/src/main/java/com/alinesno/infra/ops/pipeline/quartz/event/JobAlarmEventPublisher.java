package com.alinesno.infra.ops.pipeline.quartz.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * JobAlarmEventPublisher 类用于发布任务告警事件。
 * 该类通过注入 ApplicationEventPublisher，实现了发布事件的功能。
 *
 * 作者：luoxiaodong
 * 版本：1.0.0
 */
@Component
public class JobAlarmEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * 发布任务告警事件。
     *
     * @param e 任务告警事件对象
     */
    public void publishEvent(JobAlarmEvent e) {
        applicationEventPublisher.publishEvent(e) ;
    }
}
