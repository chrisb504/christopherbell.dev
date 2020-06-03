package com.azurras.website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackages = "com.azurras.website")
public class AzurrasApplicationTest {
    public static void main(String[] args) {
        SpringApplication.run(AzurrasApplicationTest.class, args);
    }
}