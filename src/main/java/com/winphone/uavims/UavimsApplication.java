package com.winphone.xjwrj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableTransactionManagement
@SpringBootApplication
@EnableScheduling
public class xjwrjApplication {

	public static void main(String[] args) {
		SpringApplication.run(xjwrjApplication.class, args);

	}
}
