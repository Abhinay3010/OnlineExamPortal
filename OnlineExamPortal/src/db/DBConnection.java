package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/exam_portal";
        String user = "root"; // your MySQL username
        String password = "Abhinay@30"; // your MySQL password (blank if using XAMPP)

        return DriverManager.getConnection(url, user, password);
    }
}