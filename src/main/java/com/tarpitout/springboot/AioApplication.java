package com.tarpitout.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.tarpitout.springboot.controller","com.tarpitout.springboot.service"})
public class AioApplication {

	public static void main(String[] args) {
		SpringApplication.run(AioApplication.class, args);
	}

}
