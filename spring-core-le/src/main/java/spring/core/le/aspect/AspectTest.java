package spring.core.le.aspect;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Isen
 * @date 2018/12/26 23:50
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AspectConfig.class})
@ActiveProfiles("development")
public class AspectTest {
    @Autowired
    private Person person;

    @Test
    public void test(){
        person.say();
        ((Food)person).eat();
    }
}
