package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String user = "SYS as SYSDBA";
        String password = "euforia1";

        return DriverManager.getConnection(url, user, password);
    }
}
