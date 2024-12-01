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
import java.util.Optional;


/**
 * StudentRepository is a data access class that manages database interactions for
 * the Student entity, allowing saving of Student records to the database.
 */
@Repository
public class StudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;



    /**
     * Saves a student to the database. If the student exists, it is updated, otherwise a new student is inserted.
     *
     * @param student The student object to be saved.
     * @return An Optional containing the saved student, or empty if not successful.
     */
    public Optional<Student> save(Student student) {
        String checkQuery = "SELECT COUNT(*) FROM students WHERE id = ?";
        int count = jdbcTemplate.queryForObject(checkQuery, Integer.class, student.getId());

        if (count > 0) {
            String updateQuery = "UPDATE students SET name = ?, course_name = ? WHERE id = ?";
            jdbcTemplate.update(updateQuery, student.getName(), student.getCourseName(), student.getId());
        } else {
            String insertQuery = "INSERT INTO students (id, name, course_name) VALUES (?, ?, ?)";
            jdbcTemplate.update(insertQuery, student.getId(), student.getName(), student.getCourseName());
        }

        return Optional.of(student);
    }

    /**
     * Retrieves a paginated list of students.
     *
     * @param pageable The Pageable object containing pagination information.
     * @return A list of students.
     */
    public List<Student> findAll(Pageable pageable) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        int limit = pageable.getPageSize();

        String sql = "SELECT * FROM students LIMIT ? OFFSET ?";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Student.class), limit, offset);
    }

    /**
     * Checks if a student exists by their ID.
     *
     * @param studentId The ID of the student.
     * @return true if the student exists, false otherwise.
     */
    public boolean existsById(String studentId) {
        String sql = "SELECT COUNT(*) FROM students WHERE id = ?";

        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, studentId);

        return count != null && count > 0;
    }

    /**
     * Updates the student's active status.
     *
     * @param studentId The ID of the student.
     * @param status The new status to set (true for active, false for inactive).
     */
    public void updateStudentStatus(String studentId, boolean status) {
        String sql = "UPDATE students SET active = ? WHERE id = ?";
        jdbcTemplate.update(sql, status, studentId);
    }

    /**
     * Retrieves the total count of students.
     *
     * @return The total number of students.
     */
    public long count() {
        String sql = "SELECT COUNT(*) FROM students";

        return jdbcTemplate.queryForObject(sql, Long.class);
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
