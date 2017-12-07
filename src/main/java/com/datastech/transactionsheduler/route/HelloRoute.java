package com.datastech.transactionsheduler.route;

import com.datastech.transactionsheduler.service.ShedulerBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloRoute extends RouteBuilder {

    @Autowired
    private ShedulerBuilder shedulerBuilder;
    public final static String HELLO = "direct:hello";

    @Override
    public void configure() throws Exception {
        from(HELLO)
                .setBody(simple("Hellouuuu"))
                .bean(shedulerBuilder);
    }
}
