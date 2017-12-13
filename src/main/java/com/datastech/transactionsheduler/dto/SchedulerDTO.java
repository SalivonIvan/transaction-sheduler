package com.datastech.transactionsheduler.dto;

import lombok.Data;
import org.quartz.Calendar;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by ivan salivon on 07.12.17.
 */
@Data
public class SchedulerDTO implements Serializable {

    private static final long serialVersionUID = -1193629980047872623L;

    private String timerName;
    private String jobUri;
    private String status;
    private String cron;
    private String action;
    private String groupName;
    private String triggerName;
    private Boolean stateful;
    private Boolean fireNow;
    private Boolean deleteJob;
    private Boolean pauseJob;
    private Boolean durableJob;
    private Boolean recoverableJob;
    private Long triggerStartDelay;
    private Integer startDelayedSeconds;
    private Boolean autoStartScheduler;
    private Boolean usingFixedCamelContextName;
    private Boolean prefixJobNameWithEndpointId;
    private Map<String, Object> triggerParameters;
    private Map<String, Object> jobParameters;
    private Calendar customCalendar;

}
