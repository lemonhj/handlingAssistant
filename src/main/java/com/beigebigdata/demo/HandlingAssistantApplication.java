package com.beigebigdata.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.beigebigdata.bdCourt.ca","com.septinary"})
@MapperScan("com.beigebigdata.bdCourt.ca")
public class HandlingAssistantApplication {

	public static void main(String[] args) {
		SpringApplication.run(HandlingAssistantApplication.class, args);
	}
}
