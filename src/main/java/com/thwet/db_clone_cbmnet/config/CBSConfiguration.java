package com.thwet.db_clone_cbmnet.config;

import java.util.Properties;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(entityManagerFactoryRef = "cbs", transactionManagerRef = "cbsTransactionManager", basePackages = {
		"com.thwet.db_clone_cbmnet.repository.cbs" })
public class CBSConfiguration {
	@Value("${cbs.datasource.driver-class-name}")
	private String driverClassName;

	@Value("${cbs.datasource.username}")
	private String user;

	@Value("${cbs.datasource.password}")
	private String password;

	@Value("${cbs.datasource.url}")
	private String url;

	@Value("${cbs.hibernate.dialect}")
	private String hibernateDialect;

	@Value("${cbs.hibernate.show_sql}")
	private String hibernateShowSql;

	@Value("${cbs.hibernate.format_sql}")
	private String hibernateFormatSql;

	@Value("${cbs.hibernate.hbm2ddl.auto}")
	private String hibernateHbm2DdlAuto;

	@Value("${cbs.entitymanager.packagesToScan}")
	private String packagesToScan;

	@Primary
	@Bean("cbsDataSource")
	public DataSource getDataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		dataSource.setJdbcUrl(url);
		dataSource.setMaximumPoolSize(30);
		dataSource.setMinimumIdle(30);
		return dataSource;
	}

	@Primary
	@Bean("cbsTransactionManager")
	@Autowired
	public PlatformTransactionManager getTransactionManager(@Qualifier("cbs") EntityManagerFactory entityManagerFactory)
			throws NamingException {
		JpaTransactionManager jpaTransaction = new JpaTransactionManager();
		jpaTransaction.setEntityManagerFactory(entityManagerFactory);
		return jpaTransaction;
	}

	@Primary
	@Bean("cbs")
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(getDataSource());
		emf.setPackagesToScan(packagesToScan);
		emf.setJpaVendorAdapter(getHibernateAdapter());
		emf.setPersistenceUnitName("cbs");
		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.dialect", hibernateDialect);
		jpaProperties.put("hibernate.hbm2ddl.auto", "none");
		jpaProperties.put("hibernate.show_sql", hibernateShowSql);
		jpaProperties.put("hibernate.format_sql", hibernateFormatSql);
		emf.setJpaProperties(jpaProperties);
		return emf;
	}

	@Primary
	@Bean("cbsHibernateAdapter")
	public JpaVendorAdapter getHibernateAdapter() {
		return new HibernateJpaVendorAdapter();
	}
}
