package com.datastech.transactionsheduler.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ShedulerRoute extends RouteBuilder {

    @Value("${iba.name.timer}")
    private String timerName;
    @Value("${iba.job.uri}")
    private String jobUri;
    @Value("${iba.cron.trigger}")
    private String trigger;

    @Override
    public void configure() throws Exception {
        from("quartz2://"+timerName +"?cron="+ trigger+"&fireNow=true").routeId(timerName)
                .to(jobUri)
                .log("JOB["+jobUri+"] for iba sheduler transfer was invoke");
    }
}
