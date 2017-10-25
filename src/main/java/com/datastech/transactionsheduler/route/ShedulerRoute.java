package com.datastech.transactionsheduler.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ShedulerRoute extends RouteBuilder {

    private final static String MAIN_SHEDULER = "quartz2://main?";

    @Override
    public void configure() throws Exception {
        from(MAIN_SHEDULER)
                .log("Worked Quarze");
    }
}
