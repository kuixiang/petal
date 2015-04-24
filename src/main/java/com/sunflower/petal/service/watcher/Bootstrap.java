package com.sunflower.petal.service.watcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger log = LoggerFactory.getLogger(Bootstrap.class);

    public void onApplicationEvent(ContextRefreshedEvent e) {
        log.info("正在校验应用程序...");
        log.info("应用程序启动完毕!");
    }
}
