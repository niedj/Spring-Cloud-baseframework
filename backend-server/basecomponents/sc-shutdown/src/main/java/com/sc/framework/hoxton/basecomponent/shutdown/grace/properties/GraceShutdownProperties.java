package com.sc.framework.hoxton.basecomponent.shutdown.grace.properties;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "management.endpoint.shutdown")
public class GraceShutdownProperties {

    private TimeUnit healthWaitTimeUnit = TimeUnit.SECONDS;

    private TimeUnit shutdownTimeUnit = TimeUnit.SECONDS;

    private long healthWaitTime = 10L;

    private long shutdownTime = 60L;

    /**
     * @return the healthWaitTimeUnit
     */
    public TimeUnit getHealthWaitTimeUnit() {
        return this.healthWaitTimeUnit;
    }

    /**
     * @param healthWaitTimeUnit
     *            the healthWaitTimeUnit to set
     */
    public void setHealthWaitTimeUnit(TimeUnit healthWaitTimeUnit) {
        this.healthWaitTimeUnit = healthWaitTimeUnit;
    }

    /**
     * @return the shutdownTimeUnit
     */
    public TimeUnit getShutdownTimeUnit() {
        return this.shutdownTimeUnit;
    }

    /**
     * @param shutdownTimeUnit
     *            the shutdownTimeUnit to set
     */
    public void setShutdownTimeUnit(TimeUnit shutdownTimeUnit) {
        this.shutdownTimeUnit = shutdownTimeUnit;
    }

    /**
     * @return the healthWaitTime
     */
    public long getHealthWaitTime() {
        return this.healthWaitTime;
    }

    /**
     * @param healthWaitTime
     *            the healthWaitTime to set
     */
    public void setHealthWaitTime(long healthWaitTime) {
        this.healthWaitTime = healthWaitTime;
    }

    /**
     * @return the shutdownTime
     */
    public long getShutdownTime() {
        return this.shutdownTime;
    }

    /**
     * @param shutdownTime
     *            the shutdownTime to set
     */
    public void setShutdownTime(long shutdownTime) {
        this.shutdownTime = shutdownTime;
    }

}
