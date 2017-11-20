/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
