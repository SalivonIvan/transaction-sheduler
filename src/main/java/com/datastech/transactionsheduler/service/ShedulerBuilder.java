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

    private String halfTimerName1 = "sheduler";
    private int halfTimerName2 = 0;

    public void addNewRoute(Exchange exchange) throws Exception {
        ShedulerDTO sheduler = exchange.getIn().getBody(ShedulerDTO.class);
        String timerName = prepareTimerName(sheduler);
        exchange.getContext().addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {

                from(prepareUri(sheduler)).routeId(timerName)
                        .to(sheduler.getJobUri())
                        .log("JOB[" + sheduler.getJobUri() + "] was invoke");
            }
        });
    }

    private String prepareTimerName(ShedulerDTO sheduler) {
        String timerName;
        if (sheduler.getTimerName() == null)
            timerName = halfTimerName1 + (++halfTimerName2);
        else
            timerName = sheduler.getTimerName();
        return timerName;
    }

    private String prepareUri(ShedulerDTO sheduler) {
        StringBuilder uri = new StringBuilder("quartz2://");
        if (sheduler.getGroupName() != null)
            uri.append(sheduler.getGroupName()).append("/");
        uri.append(prepareTimerName(sheduler)).append("?");
        if (sheduler.getTriggerParameters() != null && !sheduler.getTriggerParameters().isEmpty()) {
            sheduler.getTriggerParameters().forEach((k, v) -> {
                uri.append("trigger.").append(k).append("=").append(v).append("&");
            });
        }
        if (sheduler.getJobParameters() != null && !sheduler.getJobParameters().isEmpty()) {
            sheduler.getJobParameters().forEach((k, v) -> {
                uri.append("job.").append(k).append("=").append(v).append("&");
            });
        }
        if (sheduler.getCron() != null)
            uri.append("cron=").append(sheduler.getCron()).append("&");
        if (sheduler.getFireNow() != null)
            uri.append("fireNow=").append(sheduler.getFireNow()).append("&");
        if (sheduler.getDeleteJob() != null)
            uri.append("deleteJob=").append(sheduler.getDeleteJob()).append("&");
        if (sheduler.getPauseJob() != null)
            uri.append("pauseJob=").append(sheduler.getPauseJob()).append("&");
        if (sheduler.getDurableJob() != null)
            uri.append("durableJob=").append(sheduler.getDurableJob()).append("&");
        if (sheduler.getRecoverableJob() != null)
            uri.append("recoverableJob=").append(sheduler.getRecoverableJob()).append("&");
        if (sheduler.getUsingFixedCamelContextName() != null)
            uri.append("usingFixedCamelContextName=").append(sheduler.getUsingFixedCamelContextName()).append("&");
        if (sheduler.getCustomCalendar() != null)
            uri.append("customCalendar=").append(sheduler.getCustomCalendar()).append("&");

        if (uri.charAt(uri.length() - 1) == '&')
            uri.deleteCharAt(uri.length() - 1);
        return uri.toString();
    }

}
