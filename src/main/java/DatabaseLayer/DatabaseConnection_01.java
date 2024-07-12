package DatabaseLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection_01 {
    private static final String URL = "jdbc:mysql://localhost:3306/CarCare";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final Logger LOGGER = Logger.getLogger(DatabaseConnection_01.class.getName());
    private Connection connection;
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to establish a database connection", e);
            throw new RuntimeException("Failed to establish a database connection", e);
        }
    }

    public static String getDatabaseUserName(){
        return USERNAME;
    }
    public static String getDatabaseURL(){
        return URL;
    }
    public static String getDatabasePassword(){
        return PASSWORD;
    }
    public void connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error connecting to the database.", e);
        }
    }


    public void disconnect() throws SQLException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error disconnecting from the database.", e);
        }
    }
}
