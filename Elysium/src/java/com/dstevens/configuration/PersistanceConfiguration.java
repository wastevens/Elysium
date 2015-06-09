package com.dstevens.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.dstevens")
@EnableTransactionManagement
public class PersistanceConfiguration {
    
	@Value("${db.driver:com.mysql.jdbc.Driver}") private String driver;
	@Value("${db.host:localhost}") private String host;
	@Value("${db.name:elysium}") private String name;
	@Value("${db.port:3306}") private String port;
	@Value("${db.user:root}") private String user;
	@Value("${db.password:password}") private String password;
	
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
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.event.merge.entity_copy_observer", "allow");
        properties.setProperty("hibernate.show_sql", "false");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.use_sql_comments", "false");
        properties.setProperty("hibernate.auto_close_session", "true");
        properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
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
        String jdbcUrl = "jdbc:mysql://" + hostName() + ":" + dbPort() + "/" + dbName() + "?user=" + dbUser() + "&password=" + dbPassword();
        dataSource.setUrl(jdbcUrl);
        dataSource.setDriverClassName(driver());
        return dataSource;
    }

	private String driver() {
		return driver;
	}
    
    private String hostName() {
    	return host;
    }
    
    private String dbName() {
    	return name;
    }
    
    private String dbPort() {
    	return port;
    }
    
    private String dbUser() {
    	return user;
    }
    
    private String dbPassword() {
    	return password;
    }
    
    @Bean
    public PlatformTransactionManager transactionManager(){
       JpaTransactionManager transactionManager = new JpaTransactionManager();
       transactionManager.setEntityManagerFactory(this.entityManagerFactory().getObject());
       return transactionManager;
    }
    
}
