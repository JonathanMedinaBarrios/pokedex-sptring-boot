package com.springboot.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@EnableFeignClients
@SpringBootApplication
@EnableCaching
public class SpringbootPokedexApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootPokedexApplication.class, args);
	}

}
