package com.isen.hibernate;

import com.isen.pojo.Student;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Isen
 * @date 2018/12/27 10:21
 * @since 1.0
 */
@Repository
public class HibernateStudentRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public HibernateStudentRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }

    public long count(){
        return finadAll().size();
    }

    public Student save(Student student){
        Serializable id = currentSession().save(student);
        return new Student((Integer)id, student.getStudentId(), student.getName(), student.getAge(),
                student.getSex(), student.getIsOk(), student.getBirthday(), student.getModifiryTm());
    }

    public Student findOne(int id){
        return currentSession().get(Student.class, id);
    }

    public Student findByName(String name){
        Query query = currentSession().createQuery("from Student where name=?", Student.class);
        query.setParameter(0, name);
        List<Student> list = query.list();
        if(list.size() == 0){
            return null;
        }
        return list.get(0);
    }

    public List<Student> finadAll(){
        return currentSession().createQuery("from Student", Student.class).list();
    }
}
