package dev.ercan.poc.hexagonal.adapter.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "dev.ercan.poc.hexagonal")
@ComponentScan(basePackages = {"dev.ercan.poc.hexagonal"})
@EntityScan(basePackages = "dev.ercan.poc.hexagonal")
public class RestApiAdapterApplication {

  public static void main(String[] args) {
    SpringApplication.run(RestApiAdapterApplication.class, args);
  }

}