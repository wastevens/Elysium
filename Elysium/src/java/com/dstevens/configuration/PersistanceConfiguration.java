package com.dstevens.configuration;

import java.util.Properties;

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

import javax.sql.DataSource;

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
		return System.getProperty("DB_DRIVER", "com.mysql.jdbc.Driver");
	}
    
    private String hostName() {
    	return System.getProperty("DB_HOSTNAME", "localhost");
    }
    
    private String dbName() {
    	return System.getProperty("DB_NAME", "elysium");
    }
    
    private String dbPort() {
    	return System.getProperty("DB_PORT", "3306");
    }
    
    private String dbUser() {
    	return System.getProperty("DB_USERNAME", "admin");
    }
    
    private String dbPassword() {
    	return System.getProperty("DB_PASSWORD", "admin");
    }
    
    @Bean
    public PlatformTransactionManager transactionManager(){
       JpaTransactionManager transactionManager = new JpaTransactionManager();
       transactionManager.setEntityManagerFactory(this.entityManagerFactory().getObject());
       return transactionManager;
    }
    
}
