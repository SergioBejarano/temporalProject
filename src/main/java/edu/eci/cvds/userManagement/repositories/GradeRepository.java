package edu.eci.cvds.userManagement.repositories;

import edu.eci.cvds.userManagement.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, String> {
    /**
     * Finds a Grade entity by its name.
     *
     * @param name The name of the grade to find.
     * @return The Grade entity matching the given name, or null if none is found.
     */
    Grade findByName(String name);
}
