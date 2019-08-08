package com.instance.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = {GsonAutoConfiguration.class})
@EnableAsync
public class InstanceManagemantApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstanceManagemantApplication.class, args);
	}

}
