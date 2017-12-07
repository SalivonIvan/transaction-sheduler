package com.datastech.transactionsheduler.service;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by ivan salivon on 06.12.17.
 */
@Component
public class ShedulerBuilder {

    private String halfRouteId1 = "sheduler";
    private int halfRouteId2 = 0;

    public void addNewRoute(Exchange exchange) throws Exception {

        exchange.getContext().addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                String routeId = halfRouteId1 + (++halfRouteId2);
                from("quartz2://sheduler"+routeId+"?trigger.repeatInterval=3000").routeId(routeId)
                        .log("working new route");
            }
        });
    }

}
