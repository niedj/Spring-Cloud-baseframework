package com.sc.framework.hoxton.basecomponent.shutdown.grace.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.sc.framework.hoxton.basecomponent.shutdown.grace.core.DefaultShutdownJob;
import com.sc.framework.hoxton.basecomponent.shutdown.grace.core.ShutdownJob;
import com.sc.framework.hoxton.basecomponent.shutdown.grace.core.TomcatShutdownHook;
import com.sc.framework.hoxton.basecomponent.shutdown.grace.endpoint.GraceShutdownEndpoint;
import com.sc.framework.hoxton.basecomponent.shutdown.grace.properties.GraceShutdownProperties;

@Configuration
@ConditionalOnProperty(prefix = "management.endpoint.shutdown", name = "enabled", havingValue = "true",
    matchIfMissing = true)
@Import(ServletWebServerFactoryAutoConfiguration.BeanPostProcessorsRegistrar.class)
public class GraceShutdownConfig {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Bean
    public GraceShutdownProperties graceShutdownProperties() {
        return new GraceShutdownProperties();
    }

    @Bean
    @ConditionalOnMissingBean(ShutdownJob.class)
    public ShutdownJob shutdownJob() {
        return new DefaultShutdownJob();
    }

    @Bean
    public TomcatShutdownHook tomcatShutdownHook() {
        TomcatShutdownHook tomcatShutdownHook = new TomcatShutdownHook();
        this.log.info("Init TomcatShutdownHook Success");
        return tomcatShutdownHook;
    }

    @Bean
    public ServletWebServerFactory tomcatServletContainer(TomcatShutdownHook tomcatShutdownHook) {
        TomcatServletWebServerFactory servletWebServerFactory = new TomcatServletWebServerFactory();
        servletWebServerFactory.addConnectorCustomizers(tomcatShutdownHook);
        return servletWebServerFactory;
    }

    @Bean
    public GraceShutdownEndpoint graceShutdownEndpoint(TomcatShutdownHook tomcatShutdownHook, ShutdownJob shutdownJob,
        GraceShutdownProperties graceShutdownPropertie) {
        GraceShutdownEndpoint graceShutdownEndpoint = new GraceShutdownEndpoint(tomcatShutdownHook,
            graceShutdownPropertie.getShutdownTimeUnit(), graceShutdownPropertie.getShutdownTime(),
            graceShutdownPropertie.getHealthWaitTimeUnit(), graceShutdownPropertie.getHealthWaitTime());
        graceShutdownEndpoint.setShutdownJob(shutdownJob);
        this.log.info("Init GraceShutdownEndpoint Success");
        return graceShutdownEndpoint;
    }

}
