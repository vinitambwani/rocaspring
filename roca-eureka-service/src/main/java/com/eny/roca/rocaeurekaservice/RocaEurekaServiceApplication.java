package com.eny.roca.rocaeurekaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class RocaEurekaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RocaEurekaServiceApplication.class, args);
	}

}

