package com.sc.framework.hoxton.basecomponent.shutdown.grace.endpoint;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.actuate.context.ShutdownEndpoint;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.sc.framework.hoxton.basecomponent.shutdown.grace.core.ShutdownHook;
import com.sc.framework.hoxton.basecomponent.shutdown.grace.core.ShutdownJob;

@Endpoint(id = "shutdown", enableByDefault = false)
public class GraceShutdownEndpoint extends ShutdownEndpoint {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private static final Map<String, String> NO_CONTEXT_MESSAGE =
        Collections.unmodifiableMap(Collections.singletonMap("message", "No context to shutdown."));

    private static final String SHUTDOWN_MESSAGE =
        "Shutting down : Wait health check in %s(%s),Force shut down in %s(%s), bye...";

    private ConfigurableApplicationContext context;

    private ShutdownHook shutdownHook;

    private TimeUnit healthWaitTimeUnit = TimeUnit.SECONDS;

    private TimeUnit shutdownTimeUnit = TimeUnit.SECONDS;

    private long healthWaitTime = 10L;

    private long shutdownTime;

    private ShutdownJob shutdownJob;

    public GraceShutdownEndpoint(ShutdownHook shutdownHook, long shutdownTime) {
        this(shutdownHook, null, shutdownTime, null, -1);
    }

    public GraceShutdownEndpoint(ShutdownHook shutdownHook, long shutdownTime, long healthWaitTime) {
        this(shutdownHook, null, shutdownTime, null, healthWaitTime);
    }

    public GraceShutdownEndpoint(ShutdownHook shutdownHook, TimeUnit shutdownTimeUnit, long shutdownTime) {
        this(shutdownHook, shutdownTimeUnit, shutdownTime, null, -1);
    }

    public GraceShutdownEndpoint(ShutdownHook shutdownHook, TimeUnit shutdownTimeUnit, long shutdownTime,
        TimeUnit healthWaitTimeUnit, long healthWaitTime) {
        super();
        this.shutdownHook = shutdownHook;
        if (null != healthWaitTimeUnit) {
            this.healthWaitTimeUnit = healthWaitTimeUnit;
        }
        if (null != shutdownTimeUnit) {
            this.shutdownTimeUnit = shutdownTimeUnit;
        }
        if (healthWaitTime > 0) {
            this.healthWaitTime = healthWaitTime;
        }
        if (shutdownTime > 0) {
            this.shutdownTime = shutdownTime;
        }
    }

    @Override
    @WriteOperation
    public Map<String, String> shutdown() {
        if (this.context == null) {
            return GraceShutdownEndpoint.NO_CONTEXT_MESSAGE;
        }
        try {
            return Collections.singletonMap("message", String.format(GraceShutdownEndpoint.SHUTDOWN_MESSAGE,
                this.healthWaitTime, this.healthWaitTimeUnit, this.shutdownTime, this.shutdownTimeUnit));
        } finally {
            final Thread thread = new Thread(() -> {
                try {
                    this.shutdownHook.startGraceShutdown();
                    this.log.info("Grace Shutdown Progress Starting...,Wait:{}({}) Health Check To Out Of Service",
                        this.healthWaitTime, this.healthWaitTimeUnit);
                    this.healthWaitTimeUnit.sleep(this.healthWaitTime);
                    this.log.info("Grace Shutdown Progress Start Pause Request");
                    this.shutdownHook.pauseRequest();
                    this.log.info("Grace Shutdown Progress Start Shutdown");
                    this.shutdownHook.shutdown(this.shutdownTime, this.shutdownTimeUnit);
                    if (null != this.shutdownJob) {
                        this.log.info("Grace Shutdown Progress Execute Custom Shutdown Job ...");
                        this.shutdownJob.executeShutdownJob();
                    }
                    this.context.close();
                } catch (final InterruptedException ex) {
                    this.log.error("Grace shutdownn progress has been interrupted :{} ", ex.getMessage());
                    Thread.currentThread().interrupt();
                }
            });
            thread.setContextClassLoader(this.getClass().getClassLoader());
            thread.start();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        if (context instanceof ConfigurableApplicationContext) {
            this.context = (ConfigurableApplicationContext)context;
        }
    }

    public void setShutdownJob(ShutdownJob shutdownJob) {
        this.shutdownJob = shutdownJob;
    }
}
