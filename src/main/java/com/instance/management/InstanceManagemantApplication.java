package com.instance.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;

@SpringBootApplication(exclude = {GsonAutoConfiguration.class})
public class InstanceManagemantApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstanceManagemantApplication.class, args);
	}

}
