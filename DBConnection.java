package com.hms.util;

import java.sql.*;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/hmsdb";
    private static final String USER = "root";      // your MySQL username
    private static final String PASS = "Uma";  // your MySQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
