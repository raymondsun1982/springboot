package com.raymond;


import org.springframework.boot.SpringApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@org.springframework.boot.autoconfigure.SpringBootApplication
@EnableTransactionManagement(order = 10)
public class SpringBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplication.class, args);
	}
}
