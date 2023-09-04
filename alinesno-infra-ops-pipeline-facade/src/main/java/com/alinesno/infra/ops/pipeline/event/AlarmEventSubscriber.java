package com.alinesno.infra.ops.pipeline.event;

import com.alinesno.infra.ops.pipeline.entity.TransEntity;
import com.alinesno.infra.ops.pipeline.service.ITransService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * AlarmEventSubscriber是报警事件的订阅者。
 * 它实现了ApplicationListener接口，用于监听并处理报警事件。
 */
@Component
public class AlarmEventSubscriber implements ApplicationListener<AlarmEvent> {

    private static final Logger log = LoggerFactory.getLogger(AlarmEventSubscriber.class);

    @Autowired
    private ITransService transService;

    /**
     * 处理报警事件
     *
     * @param event 报警事件对象
     */
    @Override
    public void onApplicationEvent(AlarmEvent event) {
        log.info("trans count = {}", event);

        TransEntity trans = transService.getById(event.getTransId());
        trans.setTotalDataCount(event.getTotalCount());
        trans.setProcessDataCount(event.getTransCount());

        transService.update(trans);
    }
}
