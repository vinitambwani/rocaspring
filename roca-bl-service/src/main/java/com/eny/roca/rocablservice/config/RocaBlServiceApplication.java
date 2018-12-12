package com.eny.roca.rocablservice.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@SpringBootApplication
@ComponentScan("com.eny.roca")
public class RocaBlServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RocaBlServiceApplication.class, args);
	}
}
