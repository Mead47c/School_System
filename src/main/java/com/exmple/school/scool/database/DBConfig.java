package com.exmple.school.scool.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBConfig {
    private static String dbUrl;
    private static String dbUser;
    private static String dbPass;

    static {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("config.properties")); 
            dbUrl = props.getProperty("db.url");
            dbUser = props.getProperty("db.username");
            dbPass = props.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getDbUrl() {
        return dbUrl;
    }

    public static String getDbUser() {
        return dbUser;
    }

    public static String getDbPass() {
        return dbPass; // ← تم إصلاح المشكلة هنا
    }
}
