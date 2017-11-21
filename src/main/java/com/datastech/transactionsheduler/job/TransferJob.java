package com.datastech.transactionsheduler.job;

import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 *
 * @author ivan
 */
@Component
public class TransferJob implements Job{

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        System.out.println("Hello World! - " + new Date());
    }
    
}
