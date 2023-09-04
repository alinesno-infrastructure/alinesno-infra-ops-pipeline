package com.alinesno.infra.ops.pipeline.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * AlarmEventPublisher是报警事件的发布者。
 * 它负责发布报警事件到应用程序事件发布器。
 */
@Component
public class AlarmEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * 发布报警事件
     *
     * @param e 报警事件对象
     */
    public void publishEvent(AlarmEvent e) {
        applicationEventPublisher.publishEvent(e);
    }
}
