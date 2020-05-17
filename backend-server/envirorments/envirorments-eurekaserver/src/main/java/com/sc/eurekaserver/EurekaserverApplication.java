package com.sc.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.sc.eurekaserver.shutdown.ShutDownAfterProcess;
import com.sc.framework.hoxton.basecomponent.shutdown.grace.core.ShutdownJob;

@SpringBootApplication
@EnableEurekaServer
@ComponentScan("com.sc")
public class EurekaserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaserverApplication.class, args);
    }

    @Bean
    public ShutdownJob shutdownJob() {
        return new ShutDownAfterProcess();
    }
}
