package spring.core.le.pojo;

import java.util.Date;

/**
 * @author Isen
 * @date 2018/12/26 18:28
 * @since 1.0
 */
public class Student {
    private Integer id;

    private Integer studentId;

    private String name;

    private Integer age;

    private String sex;

    private Date isOk;

    private Date birthday;

    private Date modifiryTm;

    public Student(){}

    public Student(Integer id, Integer studentId, String name, Integer age, String sex,
            Date isOk, Date birthday, Date modifiryTm) {
        this.id = id;
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.isOk = isOk;
        this.birthday = birthday;
        this.modifiryTm = modifiryTm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getIsOk() {
        return isOk;
    }

    public void setIsOk(Date isOk) {
        this.isOk = isOk;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getModifiryTm() {
        return modifiryTm;
    }

    public void setModifiryTm(Date modifiryTm) {
        this.modifiryTm = modifiryTm;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", isOk=" + isOk +
                ", birthday=" + birthday +
                ", modifiryTm=" + modifiryTm +
                '}';
    }
}
