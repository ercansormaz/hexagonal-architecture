package dev.ercan.poc.hexagonal.adapter.soap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "dev.ercan.poc.hexagonal")
@ComponentScan(basePackages = {"dev.ercan.poc.hexagonal"})
@EntityScan(basePackages = "dev.ercan.poc.hexagonal")
public class SoapServiceAdapterApplication {

  public static void main(String[] args) {
    SpringApplication.run(SoapServiceAdapterApplication.class, args);
  }

}