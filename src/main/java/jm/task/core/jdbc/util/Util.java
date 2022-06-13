package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static Connection connection;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/user_info";

    private Util() {

    };

     public static Connection getConnection () {
         if (connection == null) {
             try {
                 connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             } catch (SQLException e) {
                 System.out.println("Connection error...");
             }
         }

         return connection;
    }
}
