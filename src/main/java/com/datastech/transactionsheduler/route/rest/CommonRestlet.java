package com.datastech.transactionsheduler.route.rest;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

/**
 * @author ivan
 */
@Component
public class CommonRestlet extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration()
//                .component("restlet")
                .component("servlet")
                .host("{{app.host}}")
                .port("{{app.port}}")
                .contextPath("{{app.contextPath}}")
                .bindingMode(RestBindingMode.auto)
                //add swagger for api rest
                .apiContextPath("/api-doc").apiProperty("api.title", "Scheduler API").apiProperty("api.version", "1.0.0")
                // and enable CORS
                .apiProperty("cors", "true");
    }

}
