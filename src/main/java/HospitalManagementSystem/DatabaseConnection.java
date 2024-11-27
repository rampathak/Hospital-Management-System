package HospitalManagementSystem;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
    public static Connection connectToDatabase() throws SQLException, ClassNotFoundException {

        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306/hospital"; // Replace with your database URL
        String username = "root"; // Replace with your username
        String password = "root"; // Replace with your password

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }

        return connection;

    }

}




