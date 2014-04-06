package com.dstevens.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationConfiguration {

    public static ApplicationContext appConfig() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.dstevens");
        context.refresh();
        return context;
    }
    
}
