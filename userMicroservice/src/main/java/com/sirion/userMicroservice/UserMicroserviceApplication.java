package com.sirion.userMicroservice;

import com.sirion.userMicroservice.Repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import sun.rmi.runtime.Log;

import java.util.logging.Logger;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class UserMicroserviceApplication {

	public static void main(String[] args){
	    SpringApplication.run(UserMicroserviceApplication.class, args);
	}

}
