package com.isen.hibernate;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

/**
 * @author Isen
 * @date 2018/12/27 10:03
 * @since 1.0
 */
@Configuration
public class AppConfig {

    /**
     * 基于XML的映射
     */
    @Bean
    public LocalSessionFactoryBean sessionFactoryBean(DataSource dataSource){
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setMappingResources(new String[]{"db/hbm.xml"});
        Properties properties = new Properties();
        properties.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
        factoryBean.setHibernateProperties(properties);
        return factoryBean;
    }

    /**
     * 基于注解的映射
     */
    @Bean
    public LocalSessionFactoryBean sessionFactoryBean2(DataSource dataSource){
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan(new String[]{"com.isen.hibernate.domain"});
//        factoryBean.setAnnotatedClasses(new Class<?>[]{xx.domain.xx.class});
        Properties properties = new Properties();
        properties.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
        factoryBean.setHibernateProperties(properties);
        return factoryBean;
    }

    /**
     * 会在所有拥有@Repository注解的类上添加一个通知器（advisor），这样
     * 就会捕获任何平台相关的异常并以Spring非检查型数据访问异常的形式重新抛出。
     */
    @Bean
    public BeanPostProcessor persistenceTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
