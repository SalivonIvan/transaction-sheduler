package com.datastech.transactionsheduler.controller;

import com.datastech.transactionsheduler.route.HelloRoute;
import org.apache.camel.EndpointInject;
import org.apache.camel.FluentProducerTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sheduler")
public class HelloController {

    @EndpointInject(uri = HelloRoute.HELLO)
    private FluentProducerTemplate producer;

    @RequestMapping("/task")
    public String sawHello() {
//        return "Hello";
        return producer.request(String.class);
    }
}
