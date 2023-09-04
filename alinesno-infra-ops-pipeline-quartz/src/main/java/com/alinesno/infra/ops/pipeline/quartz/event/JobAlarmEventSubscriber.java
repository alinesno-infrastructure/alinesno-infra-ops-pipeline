package com.alinesno.infra.ops.pipeline.quartz.event;

import com.alinesno.infra.ops.pipeline.entity.TransEntity;
import com.alinesno.infra.ops.pipeline.service.ITransService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * JobAlarmEventSubscriber 类是任务告警事件的订阅者。
 * 该类实现了 ApplicationListener 接口，用于监听并处理任务告警事件。
 *
 * 作者：luoxiaodong
 * 版本：1.0.0
 */
@Component
public class JobAlarmEventSubscriber implements ApplicationListener<JobAlarmEvent> {

    private static final Logger log = LoggerFactory.getLogger(JobAlarmEventSubscriber.class);

    @Autowired
    private ITransService transService;

    /**
     * 当接收到任务告警事件时，触发该方法进行处理。
     *
     * @param event 任务告警事件对象
     */
    @Override
    public void onApplicationEvent(JobAlarmEvent event) {
        log.info("trans count = {}", event);

        TransEntity trans = transService.getById(event.getTransId());
        trans.setTotalDataCount(event.getTotalCount());
        trans.setProcessDataCount(event.getTransCount());

        transService.update(trans);
    }
}
