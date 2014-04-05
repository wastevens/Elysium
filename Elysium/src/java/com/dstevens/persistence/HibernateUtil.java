package com.dstevens.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    
    private static SessionFactory sessionFactory = createSessionFactory();

    private static final SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        System.out.println("Configuring...");
        configuration.configure();
        System.out.println("Configuration successful");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }
  
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
