package spring.core.le.dao;

import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.core.le.config.AppConfig;
import spring.core.le.config.DataSourceConfig;
import spring.core.le.dao.inter.StudentRepository;
import spring.core.le.pojo.Student;


/**
 * @author Isen
 * @date 2018/12/26 19:18
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DataSourceConfig.class, AppConfig.class})
@ActiveProfiles("development")
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void addTest(){
        Student student = new Student(3, 121212, "isen22", 18, "男", new Date(), new Date(), new Date());
        studentRepository.add(student);
    }

    @Test
    public void findOneTest(){
        Student student = studentRepository.findOne(3);
        System.out.println(student);
    }
}
