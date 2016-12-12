package br.com.desafio.conf;

import java.util.Properties;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*
 *@author Renato Muniz
 *Classe que substitui o arquivo persistence.xml 
 */

@EnableTransactionManagement
public class JPAConfiguration {
	
	/*
	 * @return LocalContainerEntityManagerFactoryBean
	 * Método utilizado para criar um factory do EntityManager.
	 * É uma classe utilitaria do Spring (LocalContainerEntityManagerFactoryBean)
	 * Toda classe anotada com @bean se torna gerenciável pelo container do Spring
	 * 
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan("br.com.desafio.models");
		
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());
		
		return em;
	}
	
	/*
	 * Método utilizado para criar um dataSource para o banco
	 * Toda classe anotada com @bean se torna gerenciável pelo container do Spring
	 * 
	 */
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/desafio_db");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return	dataSource;
	} 
	
	/*
	 *Propriedades para configuraçãoo da JPA
	 */
	private Properties additionalProperties(){
		Properties properties = new	Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.dialect",	"org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("hibernate.show_sql", "true");
		return	properties;
	}
	
	/*
	 * Método que habilita o controle transacional pelo Spring
	 * Toda classe anotada com @bean se torna gerenciável pelo container do Spring
	 */
	@Bean
	public PlatformTransactionManager transactionManager (EntityManagerFactory emf){
		JpaTransactionManager transactionManager = new	JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return	transactionManager;
	}

}
