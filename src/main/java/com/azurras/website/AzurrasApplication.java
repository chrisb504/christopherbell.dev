package com.azurras.website;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AzurrasApplication {
	private final Log LOG = LogFactory.getLog(AzurrasApplication.class);
    public static void main(String[] args) {
		SpringApplication.run(AzurrasApplication.class, args);
	}
}
