package com.alinesno.infra.ops.pipeline.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AlarmEventSubscriber implements ApplicationListener<AlarmEvent> {

    private static final Logger log = LoggerFactory.getLogger(AlarmEventSubscriber.class) ;

    @Override
    public void onApplicationEvent(AlarmEvent event) {
        log.info("trans count = {}" , event);

    }
}