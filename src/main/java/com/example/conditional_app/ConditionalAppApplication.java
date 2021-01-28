package com.example.conditional_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

@SpringBootApplication
public class ConditionalAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConditionalAppApplication.class, args);
    }

    @Bean
    @Conditional(CheckConfiguration.class)
    public void thisExecutesOnSuccessToCondition() {
        //test/or execute function here if successful
    }

}
