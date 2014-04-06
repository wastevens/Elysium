package com.dstevens.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.orm.jpa.*;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
public class PersistanceConfiguration {

    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
       LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
       JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
       factoryBean.setDataSource(dataSource());
       factoryBean.setJpaVendorAdapter(vendorAdapter);
       factoryBean.setPackagesToScan("com.dstevens");
       return factoryBean;
    }
    
    private DataSource dataSource() {
        SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/elysium");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("admin");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
       JpaTransactionManager transactionManager = new JpaTransactionManager();
       transactionManager.setEntityManagerFactory(
        this.entityManagerFactoryBean().getObject() );
       
       return transactionManager;
    }
    
}
