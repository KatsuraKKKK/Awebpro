package com.tarpitout.aio.frameworks.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan(basePackages = {"com.tarpitout.aio.frameworks.springboot.*"})
@ImportResource(locations = {"classpath:puppy.xml"})
public class AioApplication {

	public static void main(String[] args) {
		SpringApplication.run(AioApplication.class, args);
	}

}
