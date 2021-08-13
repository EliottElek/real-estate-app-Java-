/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author eliot
 */
public class BookingDAO {

    private Connection myConn;

    /*
    connecting to database 
    */
    public BookingDAO() throws Exception {
        String user = "root";
        String password = "elektra1";
        String url = "jdbc:mysql://localhost:3306/realestatedatabase?useSSL=false";
        // connect to database
        myConn = DriverManager.getConnection(url, user, password);

        System.out.println("DB connection successful to: " + url);
    }
    
/*
    returns all bookings from the database
    */
    public ArrayList<Booking> getAllBookings() throws Exception {
        ArrayList<Booking> list = new ArrayList<>();

        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("SELECT * from bookings");

            while (myRs.next()) {
                Booking temp = convertRowToBooking(myRs);
                list.add(temp);
            }

            return list;
        } finally {
            close(myStmt, myRs);
        }
    }

    /*
    methods that takes the datas from a row and creates a booking object from it.
    */
    private Booking convertRowToBooking(ResultSet myRs) throws SQLException {

        int id = myRs.getInt("identifier");
        String lastname = myRs.getString("lastname");
        String adress = myRs.getString("adress");
        int buyerID = myRs.getInt("userID");
        int estateID = myRs.getInt("estateID");
        Date date = myRs.getDate("date");

        Booking temp = new Booking(id,date, buyerID, estateID, adress, lastname);

        return temp;
    }

    /*
    function that add a booking to the database
    */
    public void addBooking(Estate estate, User buyer, Date date) throws SQLException {
        Statement stmt = myConn.createStatement();
        int buyerID = buyer.getID();
        int estateID = estate.getID();
        String adress = estate.getAdress();
        String lastname = buyer.getLastName();
        String dbop = "INSERT INTO `bookings` (`identifier`,`estateID`, `userID`, `date`, `lastname`, `adress`)  VALUES (NULL, '" + estateID + "', '" + buyerID + "','" + date + "','" + lastname + "','" + adress + "');";
        stmt.execute(dbop);
        stmt.close();
        JOptionPane.showMessageDialog(null, "Booking has been saved.", "succes", JOptionPane.INFORMATION_MESSAGE);

    }
    /*
    function that removes a booking from database
    */
    public void removeBooking(Booking booking) throws SQLException {
        int reponse = JOptionPane.showConfirmDialog(null,
                "Do you really want to delete this booking ?",
                "Delete booking",
                JOptionPane.YES_NO_CANCEL_OPTION);
        if (reponse == JOptionPane.YES_OPTION) {
            int id = booking.getID();
            try (PreparedStatement ps = myConn.prepareStatement("DELETE FROM `bookings` WHERE `identifier` like  ? ;")) {
                ps.setInt(1, id);
                ps.execute();
            }
        }
    }
    
    /*
    function that returns all the bookings from a user
    */
     public ArrayList<Booking> returnBuyersBookings(User buyer) throws SQLException
    {
        ArrayList<Booking> bookings = new ArrayList();
          Statement myStmt = null;
        ResultSet myRs = null;
        int id = buyer.getID();

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("SELECT * from `bookings` WHERE `userID` like '"+id+"'");

            while (myRs.next()) {
                Booking temp = convertRowToBooking(myRs);
                bookings.add(temp);
            }

            return bookings;
        } finally {
            close(myStmt, myRs);
        }
        
    }

     /*
     closes the database
     */
    private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
            throws SQLException {

        if (myRs != null) {
            myRs.close();
        }

        if (myStmt != null) {

        }

        if (myConn != null) {
            myConn.close();
        }
    }

    private void close(Statement myStmt, ResultSet myRs) throws SQLException {
        close(null, myStmt, myRs);
    }

}
