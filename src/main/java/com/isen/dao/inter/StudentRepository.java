package com.isen.dao.inter;

import com.isen.pojo.Student;

/**
 * @author Isen
 * @date 2018/12/26 18:20
 * @since 1.0
 */
public interface StudentRepository {

    void add(Student student);

    Student findOne(long id);
}
