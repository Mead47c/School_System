package com.exmple.school.scool.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection connect() {
        String url = "jdbc:mysql://localhost:4200/useres";
        String user = "root";
        String password = "11223344Mm";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println(" Connected to database!");
            return conn;
        } catch (SQLException e) {
            System.out.println(" Failed to connect to database!");
            e.printStackTrace();
            return null;
        }
    }
}