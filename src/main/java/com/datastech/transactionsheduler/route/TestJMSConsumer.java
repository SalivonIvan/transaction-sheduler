package com.datastech.transactionsheduler.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by ivan salivon on 11.12.17.
 */
@Component
public class TestJMSConsumer extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("activemq:queue:Totally.Rocks")
                .process(exchange -> {
                    System.out.println("IN REQUEST:" + exchange.getIn().getBody(String.class));
                })
                .setBody(constant("RESPONSE for JMS PRODUCER"));

    }
}
