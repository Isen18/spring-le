package spring.core.le.dao.impl;

import java.sql.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import spring.core.le.dao.inter.StudentRepository;
import spring.core.le.pojo.Student;

/**
 * @author Isen
 * @date 2018/12/26 18:21
 * @since 1.0
 */
@Repository
public class JdbcStudentRepository implements StudentRepository {

    private JdbcOperations jdbcOperations;
//    private NamedParameterJdbcTemplate jdbcOperations;

    private final static String SQL_INTER = "insert into student(id, student_id, name, age, sex, isOk, birthday) values (?, ?, ?, ?, ?, ?, ?)";
//    private final static String SQL_INTER = "insert into student(id, student_id, name, age, sex, isOk, birthday) "
//        + "values (:id, :studentId, :name, :age, :sex, :isOk, :birthday)";

    private final static String SQL_SELECT_BY_ID = "select * from student where id=?";

    @Autowired
    public JdbcStudentRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }
//    @Autowired
//    public JdbcStudentRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
//        this.jdbcOperations = namedParameterJdbcTemplate;
//    }

    @Override
    public void add(Student student) {
        jdbcOperations.update(SQL_INTER,
                student.getId(),
                student.getStudentId(),
                student.getName(),
                student.getAge(),
                student.getSex(),
                student.getIsOk(),
                student.getBirthday());

        //绑定参数
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("id", student.getId());
//        paramMap.put("studentId", student.getStudentId());
//        paramMap.put("name", student.getName());
//        paramMap.put("age", student.getAge());
//        paramMap.put("sex", student.getSex());
//        paramMap.put("isOk", student.getIsOk());
//        paramMap.put("birthday", student.getBirthday());
//        jdbcOperations.update(SQL_INTER, paramMap);
    }

    @Override
    public Student findOne(long id) {
        return jdbcOperations.queryForObject(SQL_SELECT_BY_ID, (ResultSet resultSet, int rowNum) -> {
            return new Student(resultSet.getInt("id"),
                    resultSet.getInt("student_id"),
                    resultSet.getString("name"),
                    resultSet.getInt("age"),
                    resultSet.getString("sex"),
                    resultSet.getDate("isOk"),
                    resultSet.getDate("birthday"),
                    resultSet.getDate("modifiry_tm"));
        }, id);
    }
}
