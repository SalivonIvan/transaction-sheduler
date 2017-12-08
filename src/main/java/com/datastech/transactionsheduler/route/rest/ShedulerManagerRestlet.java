package com.datastech.transactionsheduler.route.rest;

import com.datastech.transactionsheduler.route.service.ShedulerManagerService;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * @author ivan
 */
@Component
public class ShedulerManagerRestlet extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        rest("manager")
                .get().to(ShedulerManagerService.GET_ALL_SHEDULERS)
                .post().to(ShedulerManagerService.LAUNCHING_SHEDULER)
                .patch().to(ShedulerManagerService.UPDATE_STATE_SHEDULER)
                .post("/stop").to(ShedulerManagerService.STOP_SHEDULER)
                .delete("/{routeId}").to(ShedulerManagerService.DELETE_SHEDULER);
    }

}
