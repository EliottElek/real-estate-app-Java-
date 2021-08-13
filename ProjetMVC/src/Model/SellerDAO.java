/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jasypt.util.password.StrongPasswordEncryptor;

/**
 *
 * @author eliot
 */
public class SellerDAO {

    StrongPasswordEncryptor passwordEncryptor;

    private final Connection myConn;

    public SellerDAO() throws Exception {

        // get db properties
        Properties props = new Properties();
		///props.load(new FileInputStream("DAOproperties.txt"));

        //String user = props.getProperty("user");
        //String password = props.getProperty("password");
        //String url = props.getProperty("url");
        String user = "root";
        String password = "elektra1";
        String url = "jdbc:mysql://localhost:3306/realestatedatabase?useSSL=false";
        // connect to database
        myConn = DriverManager.getConnection(url, user, password);

        System.out.println("DB connection successful to: " + url);
        passwordEncryptor = new StrongPasswordEncryptor();
    }

    public ArrayList<Seller> getAllSellers() throws Exception {
        ArrayList<Seller> list = new ArrayList<>();

        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("SELECT * from sellers");

            while (myRs.next()) {
                Seller temp = convertRowToSeller(myRs);
                temp.showInfos();
                list.add(temp);
            }

            return list;
        } finally {
            close(myStmt, myRs);
        }
    }

    public void addSellerToDataBase(Seller seller) throws SQLException {
        try (Statement stmt = myConn.createStatement()) {
            String fstname = seller.getFirstName();
            String lstname = seller.getLastName();
            int d = seller.getD();
            int m = seller.getM();
            int y = seller.getY();
            String adress = seller.getAdress();
            String mail = seller.getEmail();
            String pass = seller.getPassword();
            //we encrypt the password everytime we want to add a user
            //we encrypt it using the jasypt librairy  passwordEncryptor = new StrongPasswordEncryptor();
            String encryptedPassword = passwordEncryptor.encryptPassword(pass);

            String dbop = "INSERT INTO `sellers` (`identifier`,`firstname`, `lastname`, `birthdate`, `adress`, `mail`, `password`) VALUES (NULL, '" + fstname + "', '" + lstname + "', '" + y + "-" + m + "-" + d + "','" + adress + "','" + mail + "','" + encryptedPassword + "');";
            stmt.execute(dbop);
        }
    }

    public void removeSeller(User user) throws SQLException {
        int reponse = JOptionPane.showConfirmDialog(null,
                "Do you really want to delete this seller ?",
                "Delete seller",
                JOptionPane.YES_NO_CANCEL_OPTION);
        if (reponse == JOptionPane.YES_OPTION) {
            int id = user.getID();
            try (PreparedStatement ps = myConn.prepareStatement("DELETE FROM `sellers` WHERE `identifier` like  ? ;")) {
                ps.setInt(1, id);
                ps.execute();
            }
        }
    }

    public ArrayList<Seller> searchSellers(String lastName) throws Exception {
        ArrayList<Seller> list = new ArrayList<>();

        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            lastName += "%";
            myStmt = myConn.prepareStatement("SELECT * from sellers where lastname like ?");

            myStmt.setString(1, lastName);

            myRs = myStmt.executeQuery();

            while (myRs.next()) {
                Seller temp = convertRowToSeller(myRs);
                temp.showInfos();
                list.add(temp);
            }

            return list;
        } finally {
            close(myStmt, myRs);
        }
    }

    public Seller getSellerAccount(String mail, String password) throws Exception {
        Seller seller = null;
        PreparedStatement stmt = myConn.prepareStatement("SELECT * FROM `sellers` WHERE `mail` like ? ");
        stmt.setString(1, mail);
        ResultSet myRs = stmt.executeQuery();
        if (myRs.next()) {
            String thismail = myRs.getString("mail");
            System.out.println("thismail :" + thismail);
            String thispassord = myRs.getString("password");
            System.out.println("crypted pass :" + thispassord);
            if ((mail.equals(thismail)) && (passwordEncryptor.checkPassword(password, thispassord))) {
                seller = convertRowToSeller(myRs);
                if (seller == null) {
                    System.out.println("fail ");
                } else {
                    System.out.println("success ");
                }
            }
        }

        return seller;
    }
    public Seller getSellerAccount(String mail) throws Exception {
        Seller seller = null;
        PreparedStatement stmt = myConn.prepareStatement("SELECT * FROM `sellers` WHERE `mail` like ? ");
        stmt.setString(1, mail);
        ResultSet myRs = stmt.executeQuery();
        while (myRs.next()) {
            seller = convertRowToSeller(myRs);
            seller.showInfos();
        }

        return seller;
    }

    public void modifyPassword(User seller, String newpass) throws SQLException {
        int id = seller.getID();
        String encryptedPassword = passwordEncryptor.encryptPassword(newpass);
        try (PreparedStatement myStmt = myConn.prepareStatement("UPDATE `sellers` SET `password`=? WHERE `identifier` like ?")) {
            myStmt.setString(1, encryptedPassword);
            myStmt.setInt(2, id);
            myStmt.execute();
            myStmt.close();
        }
    }

    public boolean mailCheck(String mail) throws SQLException {
        boolean mailok = false;
        try (Statement stmt = myConn.createStatement()) {
            String qry = "SELECT `mail` FROM `sellers` WHERE 1; ";
            ResultSet rs = stmt.executeQuery(qry);
            System.out.println(rs.toString());
            if (!rs.isBeforeFirst()) {
                mailok = true;
            } else {
                while (rs.next()) {
                    String thismail = rs.getString("mail");
                    if (mail.equals(thismail)) {
                        JOptionPane.showMessageDialog(null, "This mail adress is already used. Please select another one.", "Already existing mail adress.", JOptionPane.WARNING_MESSAGE);
                        mailok = false;
                        break;
                    } else {
                        System.out.println("true tonton");
                        mailok = true;
                    }

                }
            }

            stmt.close();
        }
        return mailok;
    }

    public boolean profileCheck(String mail, String password) {
        boolean checked = false;
        try {
            Statement stmt = myConn.createStatement();
            String qry = "SELECT `mail`, `password` FROM `sellers` WHERE 1; ";
            ResultSet rs = stmt.executeQuery(qry);
            while (rs.next()) {
                String thismail = rs.getString("mail");
                String thispassord = rs.getString("password");
                if ((mail.equals(thismail)) && (passwordEncryptor.checkPassword(password, thispassord))) {
                    System.out.println("t'es checké mate");
                    checked = true;
                    break;
                } else {
                    checked = false;
                }
            }
            if (!checked) {
                JOptionPane.showMessageDialog(null, "Cannot find account.", "fail", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SellerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return checked;
    }

    public void modifySellerProfile(User newseller) throws SQLException {
        int id = newseller.getID();
        try (PreparedStatement myStmt = myConn.prepareStatement("UPDATE `sellers` SET `identifier`=?,`firstname`=?,`lastname`=?,`birthdate`=?,`adress`=?,`mail`=? WHERE `identifier` like ?")) {
            myStmt.setInt(1, id);
            myStmt.setString(2, newseller.getFirstName());
            myStmt.setString(3, newseller.getLastName());
            myStmt.setDate(4, newseller.getDate());

            myStmt.setString(5, newseller.getAdress());
            myStmt.setString(6, newseller.getEmail());
            myStmt.setInt(7, id);
            myStmt.execute();
            myStmt.close();
            JOptionPane.showMessageDialog(null, "New infos have been saved.", "succes", JOptionPane.INFORMATION_MESSAGE);
            //System.out.println("YUP");
        }

    }

    private Seller convertRowToSeller(ResultSet myRs) throws SQLException {
        Seller temp = null;
        int id = myRs.getInt("identifier");
        String lastName = myRs.getString("lastname");
        String firstName = myRs.getString("firstname");
        String email = myRs.getString("mail");
        Date date = myRs.getDate("birthdate");
        String adress = myRs.getString("adress");

        temp = new Seller(id, firstName, lastName, date, adress, email);
        return temp;
    }

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
