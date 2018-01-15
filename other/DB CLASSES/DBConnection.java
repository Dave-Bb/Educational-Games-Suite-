package tgs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    //variable used to create db connection
    private static final String url = "jdbc:mysql://localhost:3306/TGS";
    private static final String driverName = "com.mysql.jdbc.Driver";
    private static final String username = "root";
    private static final String password = "root";
    private static Connection con;

    public static Connection getConnection() {
        try {
            //specify driver to be used for connection
            Class.forName(driverName);
            try {
                //create connection
                con = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                // log an exception. for example:
                System.out.println("Failed to create the database connection.");
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found.");
        }
        return con;
    }
}
