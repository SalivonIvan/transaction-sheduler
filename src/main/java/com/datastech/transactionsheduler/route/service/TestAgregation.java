package com.datastech.transactionsheduler.route.service;

import com.datastech.transactionsheduler.dto.SchedulerDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Route;
import org.apache.camel.component.quartz2.QuartzConsumer;
import org.apache.camel.component.quartz2.QuartzEndpoint;
import org.apache.camel.impl.EventDrivenConsumerRoute;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.quartz.Scheduler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan salivon on 08.12.17.
 */
public class TestAgregation implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        List<SchedulerDTO> shedulers;
        if (oldExchange==null)
            shedulers = new ArrayList<>();
        else
           shedulers = oldExchange.getIn().getBody(List.class);

        if (newExchange.getIn().getBody(Route.class).getConsumer() instanceof QuartzConsumer) {
            EventDrivenConsumerRoute route = newExchange.getIn().getBody(EventDrivenConsumerRoute.class);
            QuartzEndpoint endpoint = (QuartzEndpoint) route.getEndpoint();
            Scheduler component = endpoint.getComponent().getScheduler();
            SchedulerDTO sheduler = new SchedulerDTO();
            sheduler.setTimerName(endpoint.getTriggerName());
            sheduler.setGroupName(endpoint.getGroupName());
            sheduler.setCron(endpoint.getCron());
            sheduler.setStatus(route.getStatus().toString());
            sheduler.setTriggerParameters(endpoint.getTriggerParameters());
            sheduler.setJobParameters(endpoint.getJobParameters());
            shedulers.add(sheduler);
        }

        newExchange.getIn().setBody(shedulers);
        return newExchange;
    }
}
