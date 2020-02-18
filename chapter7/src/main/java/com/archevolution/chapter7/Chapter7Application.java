package com.archevolution.chapter7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class Chapter7Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter7Application.class, args);
	}
}
