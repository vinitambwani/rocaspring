package com.eny.roca.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
@EnableEurekaClient
@SpringBootApplication
@ComponentScan("com.eny.roca")
public class RocaDbServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RocaDbServiceApplication.class, args);
	}
}
