package edu.eci.cvds.userManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * UserManagementApplication is the main entry point for the User Management
 * application. It bootstraps the application using Spring Boot's autoconfiguration.
 */
@SpringBootApplication
@EnableJpaRepositories("edu.eci.cvds.userManagement.repositories")
@EntityScan("edu.eci.cvds.UserManagement.model")
public class UserManagementApplication {

	/**
	 * Main method that launches the Spring Boot application.
	 *
	 * @param args command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}

}
