package com.example.version;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class VersionApplication {

  public static void main(String[] args) {

    SpringApplication.run(VersionApplication.class, args);
  }

}
