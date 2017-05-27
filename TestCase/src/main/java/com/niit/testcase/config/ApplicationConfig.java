package com.niit.testcase.config;

	import java.util.Properties;
	import javax.sql.DataSource;
	import org.hibernate.SessionFactory;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.transaction.annotation.EnableTransactionManagement;
    
	import com.niit.testcase.model.User;
    import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.ComponentScan;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.jdbc.datasource.DriverManagerDataSource;
	import org.springframework.orm.hibernate4.HibernateTransactionManager;
	import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

	@Configuration
	@ComponentScan("com.niit")
	@EnableTransactionManagement
	public class ApplicationConfig 
	{

	@Bean(name = "dataSource")
	public DataSource getH2DataSource() 
	{
	System.out.println("Starting of the method getH2DataSource");
	DriverManagerDataSource dataSource = new DriverManagerDataSource();
	dataSource.setDriverClassName("org.h2.Driver");
	dataSource.setUrl("jdbc:h2:tcp://localhost/~/testcase");
	dataSource.setUsername("sa");
	dataSource.setPassword("sa");
	System.out.println("Data Source Creation"); 
	return dataSource;
	}

	private Properties getHibernateProperties() 
	{
	System.out.println("Starting of the method getHibernateProperties");
	Properties properties = new Properties();
	properties.setProperty("hibernate.hbm2ddl.auto", "update");
	properties.put("hibernate.show_sql", "true");
	properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	System.out.println("Hibernate Properties Implementation");
	return properties;
	}

	@Autowired(required=true)
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) 
	{
	System.out.println("Starting of the method getSessionFactory");
	LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
	sessionBuilder.addProperties(getHibernateProperties());
	sessionBuilder.addAnnotatedClass(User.class);
	System.out.println("Session Factory Implementation");
	return sessionBuilder.buildSessionFactory();
	}

	@Autowired(required=true)
	@Bean(name= "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) 
	{
	System.out.println("Starting of the method getTransactionManager");
	HibernateTransactionManager transactionManager=new HibernateTransactionManager(sessionFactory);
	System.out.println("Transaction Implementation");
	return transactionManager;
	}

	}
