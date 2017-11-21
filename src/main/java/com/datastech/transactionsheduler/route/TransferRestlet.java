package com.datastech.transactionsheduler.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 *
 * @author ivan
 */
@Component
public class TransferRestlet extends RouteBuilder{

    @Override
    public void configure() throws Exception {
       
        rest("auto-transfers")
                .get()
                .to(HelloRoute.HELLO);
    }
    
}
