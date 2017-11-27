package com.datastech.transactionsheduler.route.service;

import com.datastech.transactionsheduler.TransactionShedulerApplication;
import com.datastech.transactionsheduler.TransferDTO;
import com.datastech.transactionsheduler.route.repository.JobRepository;
import java.util.List;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
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
public class TransferServiceTest {

    @Autowired
    private ProducerTemplate template;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of configure method, of class TransferService.
     */
    @Test
    public void testCreateRoute() {
        TransferDTO job = new TransferDTO();
        job.setData("data");
        job.setName("new a job");
        Object response = template.requestBody(TransferService.CREATE, job);
        List<TransferDTO> response1 = template.requestBody(TransferService.GET_ALL, null,List.class);
        Assert.assertNotNull(response1);
    }

}
