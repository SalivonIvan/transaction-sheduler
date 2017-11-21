package com.datastech.transactionsheduler.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 *
 * @author ivan
 */
@Component
public class SessionRestlet extends RouteBuilder{

    @Override
    public void configure() throws Exception {
         rest("users/session")
                .get()
                .to(HelloRoute.HELLO);
    }
    
}
