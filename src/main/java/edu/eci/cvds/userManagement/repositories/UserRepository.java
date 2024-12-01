package edu.eci.cvds.userManagement.repositories;


import edu.eci.cvds.userManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
