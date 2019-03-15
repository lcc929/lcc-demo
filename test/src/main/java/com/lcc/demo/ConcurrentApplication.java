package com.lcc.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class ConcurrentApplication {

  public static void main(String[] args) {
    SpringApplication.run(ConcurrentApplication.class, args);
  }
}
