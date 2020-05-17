package com.sc.eurekaserver.shutdown;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sc.framework.hoxton.basecomponent.shutdown.grace.core.ShutdownJob;

public class ShutDownAfterProcess implements ShutdownJob {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /*
     * (non-Javadoc) curl -X POST http://127.0.0.1:8000/actuator/shutdown
     * @see com.gxlu.shutdown.grace.core.ShutdownJob#executeShutdownJob()
     */
    @Override
    public void executeShutdownJob() {
        // TODO Auto-generated method stub
        this.log.info("Eureka server is shutdown !!!");
    }

}
