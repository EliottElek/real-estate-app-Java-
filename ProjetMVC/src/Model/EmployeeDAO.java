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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jasypt.util.password.StrongPasswordEncryptor;

/**
 *
 * @author eliot
 */
public class EmployeeDAO {

    private final Connection myConn;
    StrongPasswordEncryptor passwordEncryptor;

    public EmployeeDAO() throws Exception {
        /*
         // get db properties
         Properties props = new Properties();
         props.load(new FileInputStream("demo.properties"));
         String user = props.getProperty("user");
         String password = props.getProperty("password");
         String dburl = props.getProperty("dburl");
         */
        // connect to database
        String user = "root";
        String password = "elektra1";
        String url = "jdbc:mysql://localhost:3306/realestatedatabase?useSSL=false";
        // connect to database
        myConn = DriverManager.getConnection(url, user, password);

        System.out.println("DB connection successful to: " + url);
        passwordEncryptor = new StrongPasswordEncryptor();
    }

    public ArrayList<Employee> getAllEmployees() throws Exception {
        ArrayList<Employee> list = new ArrayList<>();

        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("SELECT * from employees");

            while (myRs.next()) {
                Employee tempEmployee = convertRowToEmployee(myRs);
                tempEmployee.showInfos();
                list.add(tempEmployee);
            }

            return list;
        } finally {
            close(myStmt, myRs);
        }
    }

    public void removeEmployee(User user) throws SQLException {
        int reponse = JOptionPane.showConfirmDialog(null,
                "Do you really want to delete this employee ?",
                "Delete employee",
                JOptionPane.YES_NO_CANCEL_OPTION);
        if (reponse == JOptionPane.YES_OPTION) {
            int id = user.getID();
            try (PreparedStatement ps = myConn.prepareStatement("DELETE FROM `employees` WHERE `identifier` like  ? ;")) {
                ps.setInt(1, id);
                ps.execute();
            }
        }
    }

    public boolean profileCheck(String mail, String password, int number) {
        boolean checked = false;
        try {
            Statement stmt = myConn.createStatement();
            String qry = "SELECT `mail`, `password`, `number` FROM `employees` WHERE 1; ";
            ResultSet rs = stmt.executeQuery(qry);
            System.out.println(qry);
            while (rs.next()) {
                String thismail = rs.getString("mail");
                String thispassord = rs.getString("password");
                int thisnumber = rs.getInt("number");
                if ((mail.equals(thismail)) && (passwordEncryptor.checkPassword(password, thispassord)) && (thisnumber == number)) {
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

    public Employee getEmployeeAccount(String mail, String password, int number) throws Exception {
        Employee employee = null;
        PreparedStatement stmt = myConn.prepareStatement("SELECT * FROM `employees` WHERE `mail` like ? ");
        stmt.setString(1, mail);
        ResultSet myRs = stmt.executeQuery();
        if (myRs.next()) {
            String thismail = myRs.getString("mail");
            String thispassord = myRs.getString("password");
            int thisnb = myRs.getInt("number");
            if ((mail.equals(thismail)) && (passwordEncryptor.checkPassword(password, thispassord)) && (thisnb == number)) {
                employee = convertRowToEmployee(myRs);
                if (employee == null) {
                    System.out.println("fail ");
                } else {
                    System.out.println("success ");
                }
            }
        }

        return employee;
    }

    public ArrayList<Employee> searchEmployees(String lastName) throws Exception {
        ArrayList<Employee> list = new ArrayList<>();

        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            lastName += "%";
            myStmt = myConn.prepareStatement("SELECT * from employees where lastname like ?");

            myStmt.setString(1, lastName);

            myRs = myStmt.executeQuery();

            while (myRs.next()) {
                Employee tempEmployee = convertRowToEmployee(myRs);
                list.add(tempEmployee);
            }

            return list;
        } finally {
            close(myStmt, myRs);
        }
    }

    public void addEmployeeToDataBase(Employee employee) throws SQLException {
        try (Statement stmt = myConn.createStatement()) {
            String fstname = employee.getFirstName();
            String lstname = employee.getLastName();
            int d = employee.getD();
            int m = employee.getM();
            int y = employee.getY();
            String adress = employee.getAdress();
            String mail = employee.getEmail();
            String pass = employee.getPassword();
            int number = employee.getEmployeeNb();
            String encryptedPassword = passwordEncryptor.encryptPassword(pass);
            String dbop = "INSERT INTO `employees` (`identifier`,`firstname`, `lastname`, `birthdate`, `adress`, `mail`, `password`,`number`) VALUES (NULL, '" + fstname + "', '" + lstname + "', '" + y + "-" + m + "-" + d + "','" + adress + "','" + mail + "','" + encryptedPassword + "','" + number + "');";
            stmt.execute(dbop);
        }
    }

    public boolean mailCheck(String mail) throws SQLException {
        boolean mailok = false;
        try (Statement stmt = myConn.createStatement()) {
            String qry = "SELECT `mail` FROM `employees` WHERE 1; ";
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

    public void modifyEmployeeProfile(User newemployee) throws SQLException {
        int id = newemployee.getID();
        PreparedStatement myStmt;
        myStmt = myConn.prepareStatement("UPDATE `employees` SET `identifier`=?,`firstname`=?,`lastname`=?,`birthdate`=?,`adress`=?,`mail`=? WHERE `identifier` like ?");
        myStmt.setInt(1, id);
        myStmt.setString(2, newemployee.getFirstName());
        myStmt.setString(3, newemployee.getLastName());
        myStmt.setDate(4, newemployee.getDate());
        myStmt.setString(5, newemployee.getAdress());
        myStmt.setString(6, newemployee.getEmail());
        myStmt.setInt(7, id);
        myStmt.execute();
        myStmt.close();
        JOptionPane.showMessageDialog(null, "New infos have been saved.", "succes", JOptionPane.INFORMATION_MESSAGE);
    }
      public void modifyPassword(User employee, String newpass) throws SQLException {
        int id = employee.getID();
        String encryptedPassword = passwordEncryptor.encryptPassword(newpass);
        try (PreparedStatement myStmt = myConn.prepareStatement("UPDATE `employees` SET `password`=? WHERE `identifier` like ?")) {
            myStmt.setString(1, encryptedPassword);
            myStmt.setInt(2, id);
            myStmt.execute();
            myStmt.close();
        }
    }
     public Employee getEmployeeAccount(String mail) throws Exception {
        Employee employee = null;
        PreparedStatement stmt = myConn.prepareStatement("SELECT * FROM `employees` WHERE `mail` like ? ");
        stmt.setString(1, mail);
        ResultSet myRs = stmt.executeQuery();
        while (myRs.next()) {
            employee = convertRowToEmployee(myRs);
            employee.showInfos();
        }

        return employee;
    }

    private Employee convertRowToEmployee(ResultSet myRs) throws SQLException {
        Employee temp = null;
        int id = myRs.getInt("identifier");
        String lastName = myRs.getString("lastname");
        String firstName = myRs.getString("firstname");
        String email = myRs.getString("mail");
        Date date = myRs.getDate("birthdate");
        int number = myRs.getInt("number");
        String adress = myRs.getString("adress");
        temp = new Employee(id, firstName, lastName, date, adress, email);
        temp.setEmployeeNb(number);
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
