package com.example.trainbooking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {
    public static boolean isValid(long mobile, String password) throws Exception {
        Connection con = DataBaseConnection.getConnection();
        String query = "select * from passengers where mobile = ? and password = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setLong(1,mobile);
        ps.setString(2,password);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            return true;
        return false;
    }
}
