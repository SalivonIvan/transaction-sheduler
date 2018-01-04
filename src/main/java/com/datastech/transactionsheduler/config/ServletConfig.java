package com.datastech.transactionsheduler.config;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ivan salivon on 04.01.18.
 */
@Configuration
public class ServletConfig {

    @Value("${app.contextPath}")
    private String contextPath;

    @Bean
    public ServletRegistrationBean camelServletRegistrationBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new CamelHttpTransportServlet(), "/" + contextPath + "/*");
        registration.setName("CamelServlet");
        return registration;
    }

}
