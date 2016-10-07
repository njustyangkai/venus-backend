/**
 * 
 */
package com.venus.frame.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class SystemInitializer implements ApplicationListener<ContextRefreshedEvent>
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0)
    {
        logger.info("=========================SystemInitializer=========================");
    }
    
}
