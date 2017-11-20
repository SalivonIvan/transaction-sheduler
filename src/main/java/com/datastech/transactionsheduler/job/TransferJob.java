/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
