package com.eny.roca.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@org.springframework.context.annotation.Configuration
@EnableTransactionManagement
@PropertySource(value = { "classpath:application.properties" })
public class DBConfiguration {
	
	 @Autowired
	    private Environment env;
	
	 @Bean
	    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer()
	    {
	        return new PropertySourcesPlaceholderConfigurer();
	    }  
	@Bean
	public DataSourceTransactionManager txnManagerRoca() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean
	public DataSource dataSource() {
		/* JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
		 jndiDataSourceLookup.setResourceRef(true);
		return jndiDataSourceLookup.getDataSource("java:comp/env/jdbc/rocadb");*/ 
		 BasicDataSource dataSource = new BasicDataSource();
	        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
	        dataSource.setUrl(env.getProperty("spring.datasource.url"));
	        dataSource.setUsername(env.getProperty("spring.datasource.username"));
	        dataSource.setPassword(env.getProperty("spring.datasource.password"));
	        return dataSource;
		
	}
	
	@Bean(name="jdbcTemplateRoca")
	public JdbcTemplate getRocaJDBCTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
		jdbcTemplate.setFetchSize(1000);
		return jdbcTemplate;
	}
	

}
