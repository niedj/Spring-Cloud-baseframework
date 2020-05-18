/**
 * Project Name:envirorments-configserver
 * File Name:ShutDownAfterProcess.java
 * Package Name:com.sc.shutdown
 * Date:2020-05-19 01:01:52
 * Copyright (c) 2020, CCS DIG All Rights Reserved.
 *
 */

package com.sc.shutdown;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sc.framework.hoxton.basecomponent.shutdown.grace.core.ShutdownJob;

public class ShutDownAfterProcess implements ShutdownJob {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /*
     * (non-Javadoc) curl -X POST http://127.0.0.1:18796/actuator/shutdown
     * @see com.gxlu.shutdown.grace.core.ShutdownJob#executeShutdownJob()
     */
    @Override
    public void executeShutdownJob() {
        // TODO Auto-generated method stub
        this.log.info("Gateway is shutdown !!!");
    }

}
