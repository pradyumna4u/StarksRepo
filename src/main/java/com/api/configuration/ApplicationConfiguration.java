package com.api.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ JMSConfiguration.class })
@ComponentScan(basePackages = { "com.api" })
public class ApplicationConfiguration {

}
