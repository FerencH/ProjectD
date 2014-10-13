package com.habony.common;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import java.util.logging.Logger;


@Interceptor
@Loggable
public class LoggingInterceptor {

    //@Inject
    //private Logger logger;

    @AroundInvoke
    public Object logMethod(InvocationContext ic) throws Exception {
        //logger.entering(ic.getTarget().getClass().getName(), ic.getMethod().getName());
    	System.out.println("Enter: " + ic.getMethod().getName());
        try {
            return ic.proceed();
        } finally {
        	System.out.println("Exit: " + ic.getMethod().getName());
            //logger.exiting(ic.getTarget().getClass().getName(), ic.getMethod().getName());
        }
    }
}

