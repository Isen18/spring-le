package spring.core.le.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import spring.core.le.dao.impl.JdbcStudentRepository;
import spring.core.le.dao.inter.StudentRepository;

/**
 * @author Isen
 * @date 2018/12/26 18:17
 * @since 1.0
 */
@Configuration
public class AppConfig {
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

//    @Bean
//    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource){
//        return new NamedParameterJdbcTemplate(dataSource);
//    }

//    @Bean
//    public StudentRepository studentRepository(NamedParameterJdbcTemplate jdbcTemplate){
//        return new JdbcStudentRepository(jdbcTemplate);
//    }
    @Bean
    public StudentRepository studentRepository(JdbcTemplate jdbcTemplate){
        return new JdbcStudentRepository(jdbcTemplate);
    }
}
