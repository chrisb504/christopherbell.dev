package dev.christopherbell.azurras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackages = "dev.christopherbell.azurras")
public class ApplicationTest {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationTest.class, args);
    }
}