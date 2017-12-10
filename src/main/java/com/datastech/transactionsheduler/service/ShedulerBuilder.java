package com.datastech.transactionsheduler.service;

import com.datastech.transactionsheduler.dto.ShedulerDTO;
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
        ShedulerDTO sheduler = exchange.getIn().getBody(ShedulerDTO.class);
        String shedulerId = prepareShedulerId(sheduler);
        exchange.getContext().addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {

                from("quartz2://" + shedulerId + "?trigger.repeatInterval=3000").routeId(shedulerId)
                        .log("working new route");
            }
        });
    }

    private String prepareShedulerId(ShedulerDTO sheduler){
        String shedulerId;
        if (sheduler.getShedulerId() == null)
            shedulerId = halfRouteId1 + (++halfRouteId2);
        else
            shedulerId = sheduler.getShedulerId();
        return shedulerId;
    }

}
