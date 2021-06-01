package com.college.DreService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.college"})
@EnableDiscoveryClient
public class DreApplication {
    public static void main(String[] args) {
        SpringApplication.run(DreApplication.class,args);
    }
}
