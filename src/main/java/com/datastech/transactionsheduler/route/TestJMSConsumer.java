package com.datastech.transactionsheduler.route;

import org.apache.camel.builder.RouteBuilder;

/**
 * Created by ivan salivon on 11.12.17.
 */
//@Component
public class TestJMSConsumer extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("activemq:queue:Totally.Rocks")
                .setBody(constant("RESPONSE for JMS PRODUCER"))
        .log("JOB[activemq:queue:Totally.Rocks] was send response");

    }
}
