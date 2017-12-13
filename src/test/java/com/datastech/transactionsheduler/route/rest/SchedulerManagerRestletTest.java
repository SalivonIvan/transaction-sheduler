package com.datastech.transactionsheduler.route.rest;

import com.datastech.transactionsheduler.TransactionShedulerApplication;
import com.datastech.transactionsheduler.dto.SchedulerDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.List;

/**
 * Created by ivan salivon on 13.12.17.
 */
@RunWith(CamelSpringBootRunner.class)
@SpringBootTest(classes = TransactionShedulerApplication.class)
@ActiveProfiles(profiles = "test")
public class SchedulerManagerRestletTest {

    @Autowired
    private ProducerTemplate template;
    @Autowired
    private JacksonDataFormat formatt;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetRest() throws IOException {
        List<SchedulerDTO> schedulers;
        String response = template.requestBody("http://{{app.host}}:{{app.port}}/{{app.contextPath}}/manager?restletMethods=GET", null, String.class);
        schedulers = formatt.getObjectMapper().readValue(response, new TypeReference<List<SchedulerDTO>>() {
        });
        Assert.assertNotNull(response);
        Assert.assertTrue(!schedulers.isEmpty());
    }
}