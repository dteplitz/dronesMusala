package com.example.dronesv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DronesV2Application {

    public static void main(String[] args) {
        SpringApplication.run(DronesV2Application.class, args);
    }

}
