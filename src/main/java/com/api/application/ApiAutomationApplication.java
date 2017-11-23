package com.api.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.api.configuration.JMSConfiguration;

@SpringBootApplication
@Import({ JMSConfiguration.class })
public class ApiAutomationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiAutomationApplication.class, args);
    }
}
