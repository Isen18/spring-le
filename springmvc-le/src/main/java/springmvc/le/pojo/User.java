package springmvc.le.pojo;

import java.io.Serializable;
import java.util.Date;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @author Isen
 * @date 2019/1/4 16:34
 * @since 1.0
 */
public class User implements Serializable {

    private static final long serialVersionUID = 8421083250017240572L;
    private String name;
    private int age;
    private Date birthday;
    private Tell tell;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Tell getTell() {
        return tell;
    }

    public void setTell(Tell tell) {
        this.tell = tell;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", tell=" + tell +
                '}';
    }

    public static void main(String[] args) {
        User user = new User();
        BeanWrapper beanWrapper = new BeanWrapperImpl(user);
//        beanWrapper.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));

        beanWrapper.setPropertyValue("name", "isen");
        beanWrapper.setPropertyValue("age", "12");
        beanWrapper.setPropertyValue("birthday", new Date().toString());
//        beanWrapper.setPropertyValue("birthday", "1990-01-01");

        System.out.println(user);
    }
}
