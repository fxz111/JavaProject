package com.college.WordService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.college"})
public class WordApplication {
    public static void main(String[] args) {
        SpringApplication.run(WordApplication.class,args);
    }
}
