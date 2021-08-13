/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author eliott
 */
public class Booking {

    private int ID;
    private Date date;
    private int buyerID;
    private int estateID;
    private String lastname;
    private String adress;
    public JPanel bookingPANEL;
    public JPanel deletePANEL;
    public JButton delete;
    /*
    constructor
    */

    public Booking(int id, Date date, int buyerID, int estateID, String adress, String lastname) {
        this.ID = id;
        this.date = date;
        this.buyerID = buyerID;
        this.estateID = estateID;
        this.adress = adress;
        this.lastname = lastname;
        bookingPANEL = new JPanel();
        deletePANEL = new JPanel();
        delete = new JButton("delete booking");
        delete.setFocusable(false);
        delete.setBackground(Color.red);
        this.createBookingPanel();
        bookingPANEL.setLayout(new GridLayout(4, 1));

    }
    
    /*
    creates the panel that pops when a buyer clicks on the booking button of a property.
    allows a user to choose a date for it's future booking. An email is sent to the user for confirmation.
    
    */
    private void createBookingPanel() {
        Font font = new Font("Times New Roman", Font.PLAIN, 22);
        JLabel thisadress = new JLabel("adress : " + this.adress, SwingConstants.CENTER);
        JLabel thislastname = new JLabel("lastname : " + this.lastname, SwingConstants.CENTER);
        JLabel thisdate = new JLabel("date : " + this.date.toString(), SwingConstants.CENTER);
        thisadress.setFont(font);
        thislastname.setFont(font);
        thisdate.setFont(font);
        bookingPANEL.add(thisadress);
        bookingPANEL.add(thislastname);
        bookingPANEL.add(thisdate);
        deletePANEL.setLayout(new GridLayout(1, 11));
        for (int i = 0; i < 5; i++) {
            deletePANEL.add(new JLabel());
        }
        deletePANEL.add(delete);
        for (int i = 0; i < 5; i++) {
            deletePANEL.add(new JLabel());
        }
        bookingPANEL.add(deletePANEL);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setbuyerID(int buyerID) {
        this.buyerID = buyerID;
    }

    public void setestateID(int estateID) {
        this.estateID = estateID;
    }

    public int getEstateID() {
        return this.estateID;
    }

    public int getUserID() {
        return this.buyerID;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getAdress() {
        return this.adress;
    }

    public String getLastname() {
        return this.lastname;
    }

    public int getID() {
        return this.ID;
    }
}
