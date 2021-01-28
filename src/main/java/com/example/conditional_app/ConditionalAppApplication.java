package com.example.conditional_app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

@SpringBootApplication
public class ConditionalAppApplication {
    private static final Logger log = LoggerFactory.getLogger(ConditionalAppApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ConditionalAppApplication.class, args);
    }

    @Bean
    @Conditional(CheckConfiguration.class)
    public void thisExecutesOnSuccessToCondition() {
        //test/or execute function here if successful
        log.warn("HOST AVAILABLE");
    }

}
