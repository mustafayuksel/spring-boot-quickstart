package com.mustafayuksel.quickstart.springbootquickstart;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.interceptor.AbstractMonitoringInterceptor;

import java.util.Date;

public class PerformanceMonitorInterceptor extends AbstractMonitoringInterceptor {
	private static final long serialVersionUID = 1L;
	private static Logger LOGGER = LogManager.getLogger
            ("PerformanceMonitorInterceptor");

    public PerformanceMonitorInterceptor() {
    }

    public PerformanceMonitorInterceptor(boolean useDynamicLogger) {
        setUseDynamicLogger(useDynamicLogger);
    }

    @Override
    protected Object invokeUnderTrace(MethodInvocation methodInvocation, Log log) throws Throwable {
        String name = createInvocationTraceName(methodInvocation);
        long start = System.currentTimeMillis();
        LOGGER.info("Method " + name + " execution " + "started at:" + new Date());
        try {
            return methodInvocation.proceed();
        } finally {
            long end = System.currentTimeMillis();
            long time = end - start;
            LOGGER.info("Method " + name + " execution lasted: " + time + " ms");
            LOGGER.info("Method " + name + " execution ended at: " + new Date());

            if (time > 10) {
                LOGGER.warn("Method execution longer than 10 ms!");
            }
        }
    }
}