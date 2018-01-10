package com.datastech.transactionsheduler.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by ivan salivon on 10.01.18.
 */
@Configuration
@PropertySource("file:${backbase.config}")
public class BackbaseConfig {
}
