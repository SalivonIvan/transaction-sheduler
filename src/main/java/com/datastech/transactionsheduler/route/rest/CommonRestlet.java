package com.datastech.transactionsheduler.route.rest;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author ivan
 */
@Component
public class CommonRestlet extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("restlet")
                .host("{{app.host}}")
                .port("{{app.port}}")
                .contextPath("{{app.contextPath}}")
                .bindingMode(RestBindingMode.auto);
    }

}
