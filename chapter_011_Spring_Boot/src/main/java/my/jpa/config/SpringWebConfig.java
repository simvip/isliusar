package my.jpa.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import my.jpa.controllers.ItemServlet;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "my.jpa")
@EnableJpaRepositories(basePackages = "my.jpa.repository")
@PropertySource(value= {"classpath:application.properties"})
public class SpringWebConfig implements WebMvcConfigurer {

    private static final Logger LOGGER = Logger.getLogger(ItemServlet.class);

    @Value("${local.ImageStorage}")
    private String localImageStorage;

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/"};


    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/users").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/item").setViewName("item");
        registry.addViewController("/item/**").setViewName("item");
        registry.addViewController("/test").setViewName("test");
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/webjars/**")) {
            registry.addResourceHandler("/webjars/**").addResourceLocations(
                    "classpath:/META-INF/resources/webjars/");
        }
        if (!registry.hasMappingForPattern("/**")) {
            registry.addResourceHandler("/**")
                    .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
        }
        if (localImageStorage != null){
            LOGGER.info("Serving image content from "+localImageStorage);
            registry.addResourceHandler("/image/**").addResourceLocations("file:///"+localImageStorage);
        }

        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");

        return resolver;
    }


    @Bean(destroyMethod = "close")
    DataSource dataSource(Environment env) {
        HikariConfig dataSourceConfig = new HikariConfig();
        dataSourceConfig.setDriverClassName(env.getRequiredProperty("db.driver"));
        dataSourceConfig.setConnectionTestQuery("SELECT 1");
        dataSourceConfig.setJdbcUrl(env.getRequiredProperty("db.url"));
        dataSourceConfig.setUsername(env.getRequiredProperty("db.username"));
        dataSourceConfig.setPassword(env.getRequiredProperty("db.password"));
        dataSourceConfig.addDataSourceProperty("characterEncoding", "utf8");
        dataSourceConfig.setValidationTimeout(3000);
        dataSourceConfig.setConnectionTimeout(10000);

        return new HikariDataSource(dataSourceConfig);
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
                                                                Environment env) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("my.jpa");

        Properties jpaProperties = new Properties();

        //Configures the used database dialect. This allows Hibernate to create SQL
        //that is optimized for the used database.
        jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));

        //Set Encoding
        jpaProperties.put("hibernate.connection.charSet",
                env.getRequiredProperty("hibernate.connection.charSet")
        );
        jpaProperties.put("hibernate.hbm2ddl.import_files_sql_extractor",
                env.getRequiredProperty("hibernate.hbm2ddl.import_files_sql_extractor")
        );
        jpaProperties.put("hibernate.connection.useUnicode",
                env.getRequiredProperty("hibernate.connection.useUnicode")
        );
        jpaProperties.put("hibernate.connection.characterEncoding",
                env.getRequiredProperty("hibernate.connection.characterEncoding")
        );
        jpaProperties.put("hibernate.connection.characterSetResults",
                env.getRequiredProperty("hibernate.connection.characterSetResults")
        );

        //Specifies the action that is invoked to the database when the Hibernate
        //SessionFactory is created or closed.
        jpaProperties.put("hibernate.hbm2ddl.auto",
                env.getRequiredProperty("hibernate.hbm2ddl.auto")
        );
        // Populate dp from files.
        jpaProperties.put("hibernate.hbm2ddl.import_files",
                env.getRequiredProperty("hibernate.hbm2ddl.import_files")
        );

        //Configures the naming strategy that is used when Hibernate creates
        //new database objects and schema elements
        jpaProperties.put("hibernate.ejb.naming_strategy",
                env.getRequiredProperty("hibernate.ejb.naming_strategy")
        );

        //If the value of this property is true, Hibernate writes all SQL
        //statements to the console.
        jpaProperties.put("hibernate.show_sql",
                env.getRequiredProperty("hibernate.show_sql")
        );

        //If the value of this property is true, Hibernate will format the SQL
        //that is written to the console.
        jpaProperties.put("hibernate.format_sql",
                env.getRequiredProperty("hibernate.format_sql")
        );


        // Disable feature detection by this undocumented parameter. Check the org.hibernate.engine.jdbc.internal.JdbcServiceImpl.configure method for more details.
        jpaProperties.put("spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults",
                env.getRequiredProperty("spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults")
        );

        jpaProperties.put("hibernate.jdbc.lob.non_contextual_creation",
                env.getRequiredProperty("hibernate.jdbc.lob.non_contextual_creation")
        );


        entityManagerFactoryBean.setJpaProperties(jpaProperties);
        return entityManagerFactoryBean;
    }
    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
