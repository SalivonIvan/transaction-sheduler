package com.datastech.transactionsheduler.route.rest;

import com.datastech.transactionsheduler.dto.SchedulerDTO;
import com.datastech.transactionsheduler.route.service.SchedulerManagerService;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * @author ivan
 */
@Component
public class SchedulerManagerRestlet extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        rest("manager")
                .get().to(SchedulerManagerService.GET_ALL_SCHEDULERS)
                .description("Find all schedulers").outTypeList(SchedulerDTO.class)

                .post().to(SchedulerManagerService.LAUNCHING_SCHEDULER)
                .description("Launching scheduler").type(SchedulerDTO.class)

                .patch("/{timerName}").to(SchedulerManagerService.UPDATE_STATE_SCHEDULER)
                .description("Update state of scheduler").type(SchedulerDTO.class)

                .post("/stop").to(SchedulerManagerService.STOP_SCHEDULER)

                .delete("/{timerName}").to(SchedulerManagerService.DELETE_SCHEDULER);
    }

}
