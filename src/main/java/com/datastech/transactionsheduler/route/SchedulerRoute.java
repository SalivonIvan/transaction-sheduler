package com.datastech.transactionsheduler.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SchedulerRoute extends RouteBuilder {

    @Value("${iba.name.timer}")
    private String timerName;
    @Value("${iba.job.uri}")
    private String jobUri;
    @Value("${iba.cron.trigger}")
    private String trigger;

    @Override
    public void configure() throws Exception {
        from("quartz2://" + timerName + "?cron=" + trigger + "&fireNow=true").routeId(timerName)
                .to("activemq:queue:" + jobUri)
                .log("JOB[" + jobUri + "] for iba scheduler transfer was invoke at ${date:now}");
    }
}
