package com.example.trainbooking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegisterDAO {
    public static void addUser(Passengers passenger) throws Exception {
        Connection con = DataBaseConnection.getConnection();
        String query = "insert into passengers(mobile,name,gender,password) values(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setLong(1,passenger.getMobile());
        ps.setString(2,passenger.getName());
        ps.setString(3,passenger.getGender());
        ps.setString(4,passenger.getPassword());
        ps.executeUpdate();
    }

    public static void addMoney(double money) throws Exception {
        Connection con = DataBaseConnection.getConnection();
        String query = "insert into passengers(money) values(?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setDouble(1,money);
        ps.executeUpdate();
    }

    public static double balance(Passengers passenger1) throws Exception {
        Connection con = DataBaseConnection.getConnection();
        String query = "select balance from passengers where name = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1,passenger1.getName());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            double balance = rs.getDouble("balance");
            return balance;
        } else {
            throw new Exception("No balance found for passenger: " + passenger1.getName());
        }
    }
}
