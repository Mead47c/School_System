package com.exmple.school.scool.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(
                    DBConfig.getDbUrl(),
                    DBConfig.getDbUser(),
                    DBConfig.getDbPass()
            );
            System.out.println("Connected to database!");
            return conn;
        } catch (SQLException e) {
            System.out.println("Failed to connect to database!");
            e.printStackTrace();
            return null;
        }
    }
}
