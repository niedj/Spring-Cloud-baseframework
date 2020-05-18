package com.sc.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.sc.framework.hoxton.basecomponent.shutdown.grace.core.ShutdownJob;
import com.sc.shutdown.ShutDownAfterProcess;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public ShutdownJob shutdownJob() {
        return new ShutDownAfterProcess();
    }
}
