package com.archevolution.chapter10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class Chapter8Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter8Application.class, args);
	}
}
