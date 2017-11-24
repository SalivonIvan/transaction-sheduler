package com.datastech.transactionsheduler.route.rest;

import com.datastech.transactionsheduler.route.HelloRoute;
import com.datastech.transactionsheduler.route.repository.JobRepository;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 *
 * @author ivan
 */
@Component
public class TransferRestlet extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        rest("auto-transfers")
                .get().to(JobRepository.GET_JOBS)
                .post().to(HelloRoute.HELLO);
    }

}
