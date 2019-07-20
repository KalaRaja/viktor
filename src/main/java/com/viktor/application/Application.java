package com.viktor.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.viktor"})
@EntityScan(basePackages = {"com.viktor.model"} )
@EnableJpaRepositories(basePackages = {"com.viktor.repo"})
public class Application {

	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
