package com.softroute.shipmentmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class ShipmentMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShipmentMicroserviceApplication.class, args);
	}

}
