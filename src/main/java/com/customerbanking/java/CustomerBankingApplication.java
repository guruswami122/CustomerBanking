package com.customerbanking.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CustomerBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerBankingApplication.class, args);
	}

}
