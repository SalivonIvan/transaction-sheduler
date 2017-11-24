package com.datastech.transactionsheduler.route;

import com.datastech.transactionsheduler.route.repository.JobRepository;
import com.datastech.transactionsheduler.TransactionShedulerApplication;
import com.datastech.transactionsheduler.TransferDTO;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 *
 * @author ivan
 */
@RunWith(CamelSpringBootRunner.class)
@SpringBootTest(classes = TransactionShedulerApplication.class)
@ActiveProfiles(profiles = "test")
public class JobRepositoryIT {

    @Autowired
    private ProducerTemplate template;

    @Test
    public void testCreateJob() {
        TransferDTO job = new TransferDTO();
        job.setData("data");
        job.setName("new a job");
        Object response = template.requestBody(JobRepository.CREATE_JOB, job);
        Assert.assertNotNull(response);
    }

    @Test
    public void testGetJobs() {
        TransferDTO job = new TransferDTO();
        Object response = template.requestBody(JobRepository.GET_JOBS,job);
        Assert.assertNotNull(response);
    }
    @Test
    public void testGetJobsByFilter() {
        TransferDTO job = new TransferDTO();
        job.setName("new a job");
        Object response = template.requestBody(JobRepository.GET_JOBS,"{\"name\":\"new a job\"}");
        Assert.assertNotNull(response);
    }


}
