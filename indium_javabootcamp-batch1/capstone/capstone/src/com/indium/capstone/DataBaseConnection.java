package com.indium.capstone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/skilltracker";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Root@123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
    }
}