package com.datastech.transactionsheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;

@SpringBootApplication(exclude = JmsAutoConfiguration.class)
public class TransactionShedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionShedulerApplication.class, args);
	}
}
