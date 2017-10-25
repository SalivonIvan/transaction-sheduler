package com.datastech.transactionsheduler.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class HelloRoute extends RouteBuilder {
    public final static String HELLO= "direct:hello";
    @Override
    public void configure() throws Exception {
        from(HELLO)
                .setBody(simple("Hellouuuu"));
    }
}
