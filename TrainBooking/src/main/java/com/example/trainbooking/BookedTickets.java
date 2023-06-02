package com.example.trainbooking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class BookedTickets{
    String fromstation;
    String tostation;
    LocalDate date;
    int Adults;
    int Kids;

    public static String getTrain(BookedTickets bookedTickets1) throws Exception {
        Connection con = DataBaseConnection.getConnection();
        String query = "select trname from trains where frstation = ? and tostation = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, bookedTickets1.getFromstation());
        ps.setString(2, bookedTickets1.getTostation());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String trname = rs.getString("trname");
            return trname;
        } else {
            throw new Exception("No train found for passenger");
        }
    }

    public static int getadultPrice(BookedTickets bookedTickets1) throws Exception {
        Connection con = DataBaseConnection.getConnection();
        String query = "select adult_price from trains where trname = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, getTrain(bookedTickets1));
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int adult_price = rs.getInt("adult_price");
            return adult_price;
        } else {
            throw new Exception("No train found for passenger");
        }
    }
    public static int getkidPrice(BookedTickets bookedTickets1) throws Exception {
        Connection con = DataBaseConnection.getConnection();
        String query = "select kid_price from trains where trname = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, getTrain(bookedTickets1));
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int kid_price = rs.getInt("kid_price");
            return kid_price;
        } else {
            throw new Exception("No train found for passenger");
        }
    }
    public static String getTime(BookedTickets bookedTickets1) throws Exception {
        Connection con = DataBaseConnection.getConnection();
        String query = "select time from trains where trname = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, getTrain(bookedTickets1));
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String trtime = rs.getString("time");
            return trtime;
        } else {
            throw new Exception("No train found for passenger");
        }
    }

    public String getFromstation() {
        return fromstation;
    }

    public void setFromstation(String fromstation) {
        this.fromstation = fromstation;
    }

    public String getTostation() {
        return tostation;
    }

    public void setTostation(String tostation) {
        this.tostation = tostation;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getAdults() {
        return Adults;
    }

    public void setAdults(int adults) {
        Adults = adults;
    }

    public int getKids() {
        return Kids;
    }

    public void setKids(int kids) {
        Kids = kids;
    }

    public static void addbooking(BookedTickets bookedTickets) throws Exception {
        Connection con = DataBaseConnection.getConnection();
        String query = "insert into booked(fromstation,tostation,adults,kids) values(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1,bookedTickets.getFromstation());
        ps.setString(2,bookedTickets.getTostation());
        LocalDate localDate = bookedTickets.getDate();
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
        ps.setDate(3, sqlDate);
        ps.setInt(3,bookedTickets.getAdults());
        ps.setInt(4,bookedTickets.getKids());
        ps.executeUpdate();
    }
}
