package com.musala.droneservice;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DroneServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DroneServiceApplication.class, args);
    }



}
