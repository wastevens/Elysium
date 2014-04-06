package com.dstevens.configuration;

import java.util.Properties;

import javax.sql.DataSource;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.*;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.dstevens")
@EnableTransactionManagement
public class PersistanceConfiguration {

    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
       LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
       JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
       factoryBean.setDataSource(dataSource());
       factoryBean.setJpaVendorAdapter(vendorAdapter);
       factoryBean.setPackagesToScan("com.dstevens");
       factoryBean.setJpaProperties(hibernateProperties());
       return factoryBean;
    }
    
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.show_sql", "false");
        properties.setProperty("hibernate.format_sql", "false");
        properties.setProperty("hibernate.use_sql_comments", "false");
        properties.setProperty("hibernate.auto_close_session", "true");
        properties.setProperty("hibernate.c3p0.acquire_increment", "5");
        properties.setProperty("hibernate.c3p0.idle_test_period", "1800");
        properties.setProperty("hibernate.c3p0.max_size", "600");
        properties.setProperty("hibernate.c3p0.max_statements", "50");
        properties.setProperty("hibernate.c3p0.min_size", "5");
        properties.setProperty("hibernate.c3p0.timeout", "1800");
        return properties;
    }

    private DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/elysium");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("admin");
        dataSource.setPassword("admin");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
       JpaTransactionManager transactionManager = new JpaTransactionManager();
       transactionManager.setEntityManagerFactory(this.entityManagerFactory().getObject());
       return transactionManager;
    }
    
}
