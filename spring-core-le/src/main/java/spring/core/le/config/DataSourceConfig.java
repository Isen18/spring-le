package spring.core.le.config;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jndi.JndiObjectFactoryBean;

/**
 * @author Isen
 * @date 2018/12/26 16:53
 * @since 1.0
 */
@Configuration
public class DataSourceConfig {
    @Profile("production")
    @Bean
    public JndiObjectFactoryBean dataSource0(){
        JndiObjectFactoryBean jndiObjectFB = new JndiObjectFactoryBean();
        jndiObjectFB.setJndiName("/jdbc/SplitterDB");
        jndiObjectFB.setResourceRef(true);
        jndiObjectFB.setProxyInterface(javax.sql.DataSource.class);

        return jndiObjectFB;
    }

    @Profile("qa")
    @Bean
    public BasicDataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:tcp://localhost/spitter");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        dataSource.setInitialSize(5);
        return dataSource;
    }

    @Profile("qa2")
    @Bean
    public DataSource dataSource2(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:tcp://localhost/spitter");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    @Profile("development")
    @Bean
    public DataSource dataSource3(){
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:db/schema.sql")
                .addScript("classpath:db/test-data.sql")
                .build();
    }
}
