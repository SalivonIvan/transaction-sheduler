package com.datastech.transactionsheduler.route.service;

import com.datastech.transactionsheduler.dto.ShedulerDTO;
import com.datastech.transactionsheduler.service.ShedulerBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ivan salivon on 07.12.17.
 */
@Component
public class ShedulerManagerService extends RouteBuilder {

    public static final String GET_ALL_SHEDULERS = "direct:GET_ALL_SHEDULERS";
    public static final String LAUNCHING_SHEDULER = "direct:LAUNCHING_SHEDULER";
    public static final String UPDATE_STATE_SHEDULER = "direct:UPDATE_STATE_SHEDULER";
    public static final String STOP_SHEDULER = "direct:STOP_SHEDULER";
    public static final String DELETE_SHEDULER = "direct:DELETE_SHEDULER";

    @Autowired
    private ShedulerBuilder shedulerBuilder;

    @Override
    public void configure() throws Exception {
        from(GET_ALL_SHEDULERS)
                .setBody(simple("${camelContext.getRoutes()}"))
                .split(body(), new TestAgregation())
//                .filter(exchange -> exchange.getIn().getBody(Route.class).getConsumer() instanceof QuartzConsumer)
                .process(exchange -> {
                    System.out.println("next step");
                })
                .end();

        from(LAUNCHING_SHEDULER)
                .marshal().json(JsonLibrary.Jackson)
                .unmarshal().json(JsonLibrary.Jackson, ShedulerDTO.class)
                .bean(shedulerBuilder)
                .log("Sheduler is launching successful");

        from(UPDATE_STATE_SHEDULER)
                .marshal().json(JsonLibrary.Jackson)
                .unmarshal().json(JsonLibrary.Jackson, ShedulerDTO.class)
                .setBody(simple("controlbus:route?routeId=${header.timerName}&action=${body.action}"))
                .routingSlip(simple("${body}"));

        from(STOP_SHEDULER).to("controlbus:route?routeId=sheduler1&action=status").log("SUCCESSFUL STOPED ROUTE");

        from(DELETE_SHEDULER)
                .process(exchange -> {
                    exchange.getContext().removeRoute(exchange.getIn().getHeader("timerName", String.class));
                });
//                .setBody(simple("${camelContext.removeRoute(${header.routeId})}"));

    }
}
