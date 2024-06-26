package com.example.courseproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Singleton {
    private static Singleton INSTANCE;
    private static Connection connection;

    private Singleton() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/restaurant",
                    "valerie", "kvenn72!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Singleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton();
        }
        return INSTANCE;
    }
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public  Connection getConnection(){
        return connection;
    }


}