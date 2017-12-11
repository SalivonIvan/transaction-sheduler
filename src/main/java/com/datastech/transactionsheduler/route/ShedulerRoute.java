package com.datastech.transactionsheduler.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ShedulerRoute extends RouteBuilder {

    private final static String MAIN_SHEDULER = "quartz2://sheduler-transfer?cron=";
    @Value("${cron.trigger}")
    private String trigger;

    @Override
    public void configure() throws Exception {
        from(MAIN_SHEDULER + trigger).routeId("sheduler")
                .process(exchange -> {
                    System.out.println("");
                })
                .log("Worked Quarze");
    }
}
