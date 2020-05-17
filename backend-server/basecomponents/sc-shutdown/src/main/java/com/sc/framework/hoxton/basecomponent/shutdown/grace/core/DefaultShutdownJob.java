package com.sc.framework.hoxton.basecomponent.shutdown.grace.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultShutdownJob implements ShutdownJob {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void executeShutdownJob() {
        this.log.info("Execute Default Shutdown Job,There Is nothing To do...");
    }
}
