package edu.eci.cvds.userManagement.repositories;
import edu.eci.cvds.userManagement.model.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * StudentRepository is a data access class that manages database interactions for
 * the Student entity, allowing saving of Student records to the database.
 */
@Repository
public class StudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Retrieves a paginated list of students.
     *
     * @param offset The offset for pagination.
     * @param limit  The limit for pagination.
     * @return A list of students.
     */
    public List<Student> findAll(int offset, int limit) {
        String sql = "SELECT * FROM students LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Student.class), limit, offset);
    }

    /**
     * Updates the course of a student by their ID.
     *
     * @param id         The ID of the student.
     * @param courseName The new course name.
     */
    public void updateStudentCourse(String id, String courseName) {
        String sql = "UPDATE students SET course_name = ? WHERE id = ?";
        jdbcTemplate.update(sql, courseName, id);
    }

    /**
     * Retrieves a student by their ID.
     *
     * @param id The ID of the student.
     * @return The student or null if not found.
     */
    public Student findById(String id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Student.class), id);
    }
}
