 package com.psoft.healthcareinsuance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

 @SpringBootApplication
 //@EnableCaching
public class HealthCareInsuranceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthCareInsuranceApplication.class, args);
    }

}
