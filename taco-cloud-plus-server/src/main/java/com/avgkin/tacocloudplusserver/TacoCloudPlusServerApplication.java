package com.avgkin.tacocloudplusserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.avgkin")
public class TacoCloudPlusServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudPlusServerApplication.class, args);
    }

}
