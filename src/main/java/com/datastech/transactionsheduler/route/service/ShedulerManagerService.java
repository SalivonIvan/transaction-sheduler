package com.datastech.transactionsheduler.route.service;

import com.datastech.transactionsheduler.dto.ShedulerDTO;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
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

    @Override
    public void configure() throws Exception {

        from(GET_ALL_SHEDULERS)
                .setBody(simple("${camelContext.getRoutes()}"))
                .split(body(), new TestAgregation())
//                .filter(exchange -> exchange.getIn().getBody(Route.class).getConsumer() instanceof QuartzConsumer)
                .process(exchange -> {
//                    Route route = exchange.getIn().getBody(Route.class);
//                    QuartzEndpoint endpoint = (QuartzEndpoint) route.getEndpoint();
////        if (oldExchange == null) {
//                    List<ShedulerDTO> shedulers = new ArrayList<>();
//                    ShedulerDTO sheduler = new ShedulerDTO();
//                    sheduler.setRouteId(route.getId());
//                    sheduler.setCron(endpoint.getCron());
//                    sheduler.setStatus(endpoint.getStatus().toString());
//                    exchange.getIn().setBody(sheduler);
                    System.out.println("next step");
                }).end();
//                .marshal().json(JsonLibrary.Jackson);
//                .log("NOT SUPPORTED OPERATION");

        from(LAUNCHING_SHEDULER).log("NOT SUPPORTED OPERATION");

        from(UPDATE_STATE_SHEDULER)
                .marshal().json(JsonLibrary.Jackson)
                .unmarshal().json(JsonLibrary.Jackson, ShedulerDTO.class)
                .setBody(simple("controlbus:route?routeId=${body.routeId}&action=${body.action}"))
                .routingSlip(simple("${body}"));

        from(STOP_SHEDULER).to("controlbus:route?routeId=sheduler1&action=status").log("SUCCESSFUL STOPED ROUTE");

        from(DELETE_SHEDULER)
                .process(exchange -> {
                    exchange.getContext().removeRoute(exchange.getIn().getHeader("routeId", String.class));
                });
//                .setBody(simple("${camelContext.removeRoute(${header.routeId})}"));

    }
}
