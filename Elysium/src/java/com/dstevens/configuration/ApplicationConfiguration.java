package com.dstevens.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationConfiguration {

    private enum ApplicationConfigurationSingleton {
        SINGLETON;
        
        private final AnnotationConfigApplicationContext context;
        
        private ApplicationConfigurationSingleton() {
            context = new AnnotationConfigApplicationContext("com.dstevens");
        }
    }
    
    public static ApplicationContext appConfig() {
        return ApplicationConfigurationSingleton.SINGLETON.context;
    }
    
}
