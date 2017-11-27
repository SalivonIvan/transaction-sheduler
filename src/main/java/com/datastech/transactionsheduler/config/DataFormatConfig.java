package com.datastech.transactionsheduler.config;

import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.spi.DataFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author ivan
 */
@Configuration
public class DataFormatConfig {

    @Bean("json-jackson")
    public DataFormat getJacksonDataFormat() {
        JacksonDataFormat dataFormat = new JacksonDataFormat();
        dataFormat.setInclude("NON_NULL");
        return dataFormat;
    }

}
