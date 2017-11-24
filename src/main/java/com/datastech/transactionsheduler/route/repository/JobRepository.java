package com.datastech.transactionsheduler.route.repository;

import com.mongodb.MongoClient;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author ivan
 */
@Component
public class JobRepository extends RouteBuilder {

    public static final String CREATE_JOB = "direct:createJob";
    public static final String GET_JOB = "direct:getJob";
    public static final String GET_JOBS = "direct:getJobs";
    public static final String DELETE_JOB = "direct:deleteJob";
    public static final String UPDATE_JOB = "direct:updateJob";
    private static final String BASIC_URI = "mongodb:mongoClient?database=testDB&collection=job";

    @Value("${mongodb.database}")
    private String database;

    @Value("${mongodb.collection}")
    private String collection;

    @Override
    public void configure() throws Exception {
        
        from(CREATE_JOB)
                .to(BASIC_URI+"&operation=insert");
        
        from(GET_JOBS)
                .to(BASIC_URI+"&operation=findAll");
        
        from(GET_JOB)
                .to(BASIC_URI+"&operation=findById");
        
        from(DELETE_JOB)
                .to(BASIC_URI+"&operation=remove");
        
        from(UPDATE_JOB)
                .to(BASIC_URI+"&operation=save");
    }
}
