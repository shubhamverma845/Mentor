package com.zuulForMentor.mentorGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
@EnableDiscoveryClient
public class MentorGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MentorGatewayApplication.class, args);
	}

}
