package com.example.LaBeaute;

import com.example.LaBeaute.controllers.AppErrorController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = AppErrorController.class)
public class LaBeauteApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaBeauteApplication.class, args);
	}

}
