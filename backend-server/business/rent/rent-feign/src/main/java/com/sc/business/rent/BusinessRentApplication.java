package com.sc.business.rent;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.sc.framework.hoxton.basecomponent.shutdown.grace.core.ShutdownJob;
import com.sc.shutdown.ShutDownAfterProcess;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan("com.sc")
@MapperScan("net.xdclass.base_project.mapper")
public class BusinessRentApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessRentApplication.class, args);
    }

    @Bean
    public ShutdownJob shutdownJob() {
        return new ShutDownAfterProcess();
    }
}
