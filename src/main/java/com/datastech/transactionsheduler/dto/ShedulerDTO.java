package com.datastech.transactionsheduler.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Map;

/**
 * Created by ivan salivon on 07.12.17.
 */
@Data
public class ShedulerDTO implements Serializable {

    private static final long serialVersionUID = -1193629980047872623L;

    private String shedulerId;
    private String status;
    private String cron;
    private String action;
    private String groupName;
    private String triggerName;
    private boolean stateful;
    private boolean fireNow;
    private boolean deleteJob = true;
    private boolean pauseJob;
    private boolean durableJob;
    private boolean recoverableJob;
    private long triggerStartDelay = 500;
    private int startDelayedSeconds;
    private boolean autoStartScheduler = true;
    private boolean usingFixedCamelContextName;
    private boolean prefixJobNameWithEndpointId;
    private Map<String, Object> triggerParameters;
    private Map<String, Object> jobParameters;
    private Calendar customCalendar;

}
