package com.datastech.transactionsheduler.route.service;

import com.datastech.transactionsheduler.TransferDTO;
import com.datastech.transactionsheduler.route.repository.JobRepository;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

/**
 *
 * @author ivan
 */
@Component
public class TransferService extends RouteBuilder {

    public static final String GET_ALL = "direct:getAll";
    public static final String CREATE = "direct:create";

    @Override
    public void configure() throws Exception {

        from(CREATE)
                .to(JobRepository.CREATE_JOB);

        from(GET_ALL)
                .to(JobRepository.GET_JOBS)
                .marshal().json(JsonLibrary.Jackson)
//                .unmarshal().json(JsonLibrary.Gson, String.class)
                .process((exchange) -> {
                    String json = exchange.getIn().getBody(String.class);
                    System.out.println("");
                });
//                .unmarshal().json(JsonLibrary.Gson, TransferDTO[].class);
    }

}
