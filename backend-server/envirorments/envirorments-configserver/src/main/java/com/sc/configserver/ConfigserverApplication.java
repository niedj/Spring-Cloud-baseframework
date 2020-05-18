package com.sc.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.sc.framework.hoxton.basecomponent.shutdown.grace.core.ShutdownJob;
import com.sc.shutdown.ShutDownAfterProcess;

@SpringBootApplication
@EnableConfigServer
@RefreshScope
@EnableEurekaClient
@ComponentScan("com.sc")
public class ConfigserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigserverApplication.class, args);
    }

    @Bean
    public ShutdownJob shutdownJob() {
        return new ShutDownAfterProcess();
    }
}
