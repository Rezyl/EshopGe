package eshopGery.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@EnableWebMvc
@EnableJpaRepositories(value = "eshopGery.repository", entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager")
@ComponentScan(basePackages = "eshopGery")
@PropertySource("classpath:config.properties")
@EnableTransactionManagement
@Import({ SecurityConfig.class })
@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {

	private static final int ONE_MEGABYTE = 1000000;
	// TODO properties file
	// @Value("${jdbc.driverClassName}")
	@Value("com.mysql.jdbc.Driver")
	private String driverClass;
	@Value("${jdbc.url}")
	private String jdbcUrl;
	@Value("${jdbc.username}")
	private String user;
	@Value("${jdbc.password}")
	private String password;

	@Value("${hibernate.hbm2ddl.auto}")
	private String hibProp1;

	@Autowired
	private ServletContext servletContext;

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setOrder(1);
		return resolver;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(this.sessionFactory().getObject());
		return transactionManager;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean session = new LocalSessionFactoryBean();
		session.setPackagesToScan("eshopGery");
		session.setDataSource(dataSource());
		session.setAnnotatedPackages("eshopGery");
		session.setConfigLocation(new ClassPathResource("hibernate.cfg.xml"));
		return session;
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("eshopGery.repository", "eshopGery.model");
		factory.setDataSource(dataSource());
		factory.afterPropertiesSet();

		return factory.getObject();
	}

	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/eshopg?characterEncoding=UTF-8");
		dataSource.setUsername("root");
		dataSource.setPassword("password");
		return dataSource;
	}

	@Bean
	public ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:message");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	// @Bean
	// public PropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer() {
	// PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
	// propertyPlaceholderConfigurer.setLocations(new Resource[] {new ClassPathResource("config.properties")});
	// return propertyPlaceholderConfigurer;
	// }

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSize(ONE_MEGABYTE);
		return resolver;
	}

	@Bean(autowire = Autowire.BY_TYPE)
	public org.springframework.mail.javamail.JavaMailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setProtocol("smtps");
		mailSender.setPort(465);
		mailSender.setHost("smtp.seznam.cz");
        String username = "mr.gerstberger@seznam.cz";
        mailSender.setUsername(username);
		mailSender.setPassword("popelnice01");

		Properties properties = new Properties();
		properties.setProperty("mail.smtps.auth", String.valueOf(true));
		mailSender.setJavaMailProperties(properties);
		return mailSender;
	}

	/**
	 * FREE Maker TEMPLATES
	 */
	@Bean
	public ViewResolver getViewResolver() {

		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		resolver.setCache(false);
		resolver.setSuffix(".ftl");
		resolver.setOrder(2);
		return resolver;

	}

	@Bean
	public FreeMarkerConfigurer getFreemarkerConfig() {
		FreeMarkerConfigurer result = new FreeMarkerConfigurer();
		result.setTemplateLoaderPath("WEB-INF/templates/");

		return result;
	}

	/**
	 * Initialization app
	 */
	@Bean
	public Initialization getInitialization() {
		return new Initialization(servletContext);
	}

}
