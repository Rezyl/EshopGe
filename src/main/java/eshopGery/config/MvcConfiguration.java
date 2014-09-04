package eshopGery.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
@ComponentScan(basePackages= "eshopGery")
@EnableWebMvc
@PropertySource("classpath:config.properties")
@EnableTransactionManagement
@Import({SecurityConfig.class})
public class MvcConfiguration extends WebMvcConfigurerAdapter{

    private static final int ONE_MEGABYTE = 1000000;
    //TODO properties file
//    @Value("${jdbc.driverClassName}")
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

	@Bean
	public ViewResolver viewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
        resolver.setOrder(1);
		return resolver;
	}

    @Bean
    public HibernateTransactionManager getHibernateTransactionManager() {
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
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/eshopg?characterEncoding=UTF-8");
		dataSource.setUsername("root");
		dataSource.setPassword("password");
		return dataSource;
	}

//    @Bean
//    public PropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer() {
//        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
//        propertyPlaceholderConfigurer.setLocations(new Resource[] {new ClassPathResource("config.properties")});
//        return propertyPlaceholderConfigurer;
//    }

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
        mailSender.setHost("smtp.gmail.com");
        mailSender.setUsername("l.rezner@gmail.com");
        mailSender.setPassword("popelnice07");

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


    }
