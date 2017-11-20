/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datastech.transactionsheduler.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
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
