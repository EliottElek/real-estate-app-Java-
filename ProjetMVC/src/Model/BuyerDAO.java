package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jasypt.util.password.StrongPasswordEncryptor;

/**
Every function in the BuyerDAO class is similar to employeeDAO, sellerDAO 
 */
public class BuyerDAO {

    StrongPasswordEncryptor passwordEncryptor;
    private final Connection myConn;

    public BuyerDAO() throws Exception {

        /*
         creating coonection with database
         */
        String user = "root";
        String password = "elektra1";
        String url = "jdbc:mysql://localhost:3306/realestatedatabase?useSSL=false";
        // connect to database
        myConn = DriverManager.getConnection(url, user, password);

        System.out.println("DB connection successful to: " + url);
        passwordEncryptor = new StrongPasswordEncryptor();
    }

    /*
     returns the list of buyers from the database
     */
    public ArrayList<Buyer> getAllBuyers() throws Exception {
        ArrayList<Buyer> list = new ArrayList<>();

        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("SELECT * from buyers");

            while (myRs.next()) {
                Buyer temp = convertRowToBuyer(myRs);
                temp.showInfos();
                list.add(temp);
            }

            return list;
        } finally {
            close(myStmt, myRs);
        }
    }

    /*
     adds a buyer to the database
     */
    public void addBuyerToDataBase(Buyer buyer) throws SQLException {
        try (Statement stmt = myConn.createStatement()) {
            String fstname = buyer.getFirstName();
            String lstname = buyer.getLastName();
            int d = buyer.getD();
            int m = buyer.getM();
            int y = buyer.getY();
            String adress = buyer.getAdress();
            String mail = buyer.getEmail();
            String pass = buyer.getPassword();
            String encryptedPassword = passwordEncryptor.encryptPassword(pass);
            String dbop = "INSERT INTO `buyers` (`identifier`,`firstname`, `lastname`, `birthdate`, `adress`, `mail`, `password`) VALUES (NULL, '" + fstname + "', '" + lstname + "', '" + y + "-" + m + "-" + d + "','" + adress + "','" + mail + "','" + encryptedPassword + "');";
            stmt.execute(dbop);
            stmt.close();
        }
    }

    /*
    removes a buyer form the database
    */
    public void removeBuyer(User user) throws SQLException {
        int reponse = JOptionPane.showConfirmDialog(null,
                "Do you really want to delete this buyer ?",
                "Delete buyer",
                JOptionPane.YES_NO_CANCEL_OPTION);
        if (reponse == JOptionPane.YES_OPTION) {
            int id = user.getID();
            try (PreparedStatement ps = myConn.prepareStatement("DELETE FROM `buyers` WHERE `identifier` like  ? ;")) {
                ps.setInt(1, id);
                ps.execute();
            }
        }
    }

    /*
    modifies the password of a buyer
    */
    public void modifyPassword(User newbuyer, String newpass) throws SQLException {
        int id = newbuyer.getID();
        String encryptedPassword = passwordEncryptor.encryptPassword(newpass);
        try (PreparedStatement myStmt = myConn.prepareStatement("UPDATE `buyers` SET `password`=? WHERE `identifier` like ?")) {
            myStmt.setString(1, encryptedPassword);
            myStmt.setInt(2, id);
            myStmt.execute();
            myStmt.close();
        }
    }

    public Buyer getBuyerAccount(String mail, String password) throws Exception {
        Buyer buyer = null;
        PreparedStatement stmt = myConn.prepareStatement("SELECT * FROM `buyers` WHERE `mail` like ? ");
        stmt.setString(1, mail);
        ResultSet myRs = stmt.executeQuery();
        if (myRs.next()) {
            String thismail = myRs.getString("mail");
            System.out.println("thismail :" + thismail);
            String thispassord = myRs.getString("password");
            System.out.println("crypted pass :" + thispassord);
            if ((mail.equals(thismail)) && (passwordEncryptor.checkPassword(password, thispassord))) {
                buyer = convertRowToBuyer(myRs);
                System.out.println("success ");
            } else {
                System.out.println("fail");
            }
        }

        return buyer;
    }

    public Buyer getBuyerAccount(String mail) throws Exception {
        Buyer buyer = null;
        PreparedStatement stmt = myConn.prepareStatement("SELECT * FROM `buyers` WHERE `mail` like ? ");
        stmt.setString(1, mail);
        ResultSet myRs = stmt.executeQuery();
        while (myRs.next()) {
            buyer = convertRowToBuyer(myRs);
            buyer.showInfos();
        }

        return buyer;
    }

    public boolean mailCheck(String mail) throws SQLException {
        boolean mailok = false;
        try (Statement stmt = myConn.createStatement()) {
            String qry = "SELECT `mail` FROM `buyers` WHERE 1; ";
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
            System.out.println(mail + password);
            try (Statement stmt = myConn.createStatement()) {
                String qry = "SELECT `mail`, `password` FROM `buyers` WHERE 1; ";
                ResultSet rs = stmt.executeQuery(qry);
                while (rs.next()) {
                    String thismail = rs.getString("mail");
                    String thispassword = rs.getString("password");
                    System.out.println("CRYPTED : " + thispassword);
                    if ((mail.equals(thismail)) && (passwordEncryptor.checkPassword(password, thispassword))) {
                        checked = true;
                        break;
                    } else {
                        checked = false;
                    }
                }
                if (!checked) {
                    JOptionPane.showMessageDialog(null, "Cannot find account.", "fail", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BuyerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return checked;
    }

    public void modifyBuyerProfile(User newbuyer) throws SQLException {
        int id = newbuyer.getID();
        try (PreparedStatement myStmt = myConn.prepareStatement("UPDATE `buyers` SET `identifier`=?,`firstname`=?,`lastname`=?,`birthdate`=?,`adress`=?,`mail`=? WHERE `identifier` like ?")) {
            myStmt.setInt(1, id);
            myStmt.setString(2, newbuyer.getFirstName());
            myStmt.setString(3, newbuyer.getLastName());
            myStmt.setDate(4, newbuyer.getDate());
            myStmt.setString(5, newbuyer.getAdress());
            myStmt.setString(6, newbuyer.getEmail());
            myStmt.setInt(7, id);
            myStmt.execute();
            JOptionPane.showMessageDialog(null, "New infos have been saved.", "succes", JOptionPane.INFORMATION_MESSAGE);
            myStmt.close();
        }

    }

    private Buyer convertRowToBuyer(ResultSet myRs) throws SQLException {
        Buyer temp = null;
        int id = myRs.getInt("identifier");
        String lastName = myRs.getString("lastname");
        String firstName = myRs.getString("firstname");
        String email = myRs.getString("mail");
        Date date = myRs.getDate("birthdate");
        String adress = myRs.getString("adress");

        temp = new Buyer(id, firstName, lastName, date, adress, email);
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
