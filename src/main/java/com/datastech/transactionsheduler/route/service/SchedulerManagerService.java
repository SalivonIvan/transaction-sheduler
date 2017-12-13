package com.datastech.transactionsheduler.route.service;

import com.datastech.transactionsheduler.dto.SchedulerDTO;
import com.datastech.transactionsheduler.service.SchedulerBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ivan salivon on 07.12.17.
 */
@Component
public class SchedulerManagerService extends RouteBuilder {

    public static final String GET_ALL_SCHEDULERS = "direct:GET_ALL_SCHEDULERS";
    public static final String LAUNCHING_SCHEDULER = "direct:LAUNCHING_SCHEDULER";
    public static final String UPDATE_STATE_SCHEDULER = "direct:UPDATE_STATE_SCHEDULER";
    public static final String STOP_SCHEDULER = "direct:STOP_SCHEDULER";
    public static final String DELETE_SCHEDULER = "direct:DELETE_SCHEDULER";

    @Autowired
    private SchedulerBuilder schedulerBuilder;

    @Override
    public void configure() throws Exception {
        from(GET_ALL_SCHEDULERS)
                .setBody(simple("${camelContext.getRoutes()}")).end()
                .split(body(), new TestAgregation())
                .log("Split line ${body}");

        from(LAUNCHING_SCHEDULER)
                .marshal().json(JsonLibrary.Jackson)
                .unmarshal().json(JsonLibrary.Jackson, SchedulerDTO.class)
                .bean(schedulerBuilder)
                .log("Scheduler is launching successful");

        from(UPDATE_STATE_SCHEDULER)
                .marshal().json(JsonLibrary.Jackson)
                .unmarshal().json(JsonLibrary.Jackson, SchedulerDTO.class)
                .setBody(simple("controlbus:route?routeId=${header.timerName}&action=${body.action}"))
                .routingSlip(simple("${body}"));

        from(STOP_SCHEDULER).to("controlbus:route?routeId=scheduler1&action=status").log("SUCCESSFUL STOPED ROUTE");

        from(DELETE_SCHEDULER)
                .process(exchange -> {
                    exchange.getContext().removeRoute(exchange.getIn().getHeader("timerName", String.class));
                });
//                .setBody(simple("${camelContext.removeRoute(${header.routeId})}"));

    }
}
