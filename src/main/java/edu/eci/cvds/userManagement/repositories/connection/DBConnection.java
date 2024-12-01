package edu.eci.cvds.userManagement.repositories.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The DBConnection class is responsible for managing the connection to the PostgreSQL
 * database used for user management within the system. This class provides a method
 * to obtain a Connection object, which can be used to interact with the database.
 */
public class DBConnection {
    private static final String URL = "jdbc:postgresql://libraryusermanagement.postgres.database.azure.com:5432/usermanagement?sslmode=require";
    private static final String USER = "librarydirector";
    private static final String PASSWORD = "userManagement2024";

    /**
     * Establishes and returns a connection to the PostgreSQL database.
     *
     * @return Connection object for database interaction.
     * @throws SQLException if a database access error occurs or the connection details are incorrect.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
