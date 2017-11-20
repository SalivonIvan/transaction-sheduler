/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datastech.transactionsheduler.config;

import org.apache.camel.component.restlet.RestletComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author ivan
 */
@Configuration
public class RestletConfig {

    @Bean("restlet")
    public RestletComponent getRestletComponent() {
        return new RestletComponent();
    }

}
