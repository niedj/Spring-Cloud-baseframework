package com.sc.framework.hoxton.basecomponent.shutdown.grace.core;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;

public class TomcatShutdownHook implements ShutdownHook, TomcatConnectorCustomizer {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private volatile Connector connector;

    private static volatile boolean startFlag = false;

    @Override
    public void customize(final Connector connector) {
        this.connector = connector;
    }

    @Override
    public void startGraceShutdown() {
        TomcatShutdownHook.startFlag = true;
    }

    @Override
    public boolean isStartGraceShutdown() {
        return TomcatShutdownHook.startFlag;
    }

    @Override
    public void pauseRequest() throws InterruptedException {
        final Executor executor = this.connector.getProtocolHandler().getExecutor();
        this.connector.pause();
        this.log.debug("Tomcat Pause Request Success");
    }

    @Override
    public void shutdown(long delayTime, TimeUnit delayTimeUnit) throws InterruptedException {
        final Executor executor = this.connector.getProtocolHandler().getExecutor();
        if (executor instanceof ThreadPoolExecutor) {
            final ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)executor;
            threadPoolExecutor.shutdown();
            if (!threadPoolExecutor.awaitTermination(delayTime, delayTimeUnit)) {
                this.log.warn(
                    "Tomcat can not shutdown gracefully . Proceeding with force shutdown.DelayTime:{},DelayTimeUnit:{}",
                    delayTime, delayTimeUnit);
            } else {
                this.log.debug("Tomcat ThreadPool is empty");
            }
        } else {
            this.log.warn("Executor is not ThreadPoolExecutor instance...");
        }
    }

    @Override
    public Health health() {
        if (this.isStartGraceShutdown()) {
            return Health.outOfService().build();
        } else {
            return Health.up().build();
        }
    }
}
