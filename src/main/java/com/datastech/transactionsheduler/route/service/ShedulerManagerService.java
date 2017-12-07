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

    @Override
    public void configure() throws Exception {

        from(GET_ALL_SHEDULERS).log("NOT SUPPORTED OPERATION");

        from(LAUNCHING_SHEDULER).log("NOT SUPPORTED OPERATION");

        from(UPDATE_STATE_SHEDULER)
                .marshal().json(JsonLibrary.Jackson)
                .unmarshal().json(JsonLibrary.Jackson, ShedulerDTO.class)
                .setBody(simple("controlbus:route?routeId=${body.routeId}&action=${body.action}"))
                .routingSlip(simple("${body}"))
                .log("NOT SUPPORTED OPERATION");

        from(STOP_SHEDULER).to("controlbus:route?routeId=sheduler1&action=status").log("SUCCESSFUL STOPED ROUTE");

    }
}
