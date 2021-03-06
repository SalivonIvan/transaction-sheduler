package com.datastech.transactionsheduler.config;

import org.apache.camel.component.quartz2.QuartzComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    @Bean
    public QuartzComponent getQuartzComponent() {
        QuartzComponent quartz = new QuartzComponent();
        quartz.setStartDelayedSeconds(20);
        return quartz;
    }
}
