package com.example.trainbooking;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/trainBooking";
    private static final String user = "root";
    private static final String password = "@Dharsh2806";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(url,user,password);
    }
}
