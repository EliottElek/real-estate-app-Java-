/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.EmployeeDAO;
import Model.BuyerDAO;
import Model.Employee;
import Model.Buyer;
import Model.Seller;
import Model.SellerDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
New Account interface. Mostly nuttons and panels
 */
public class NewAccount extends JPanel implements MyLogInConstant {

    private final SellerDAO sellerdao;
    private final BuyerDAO buyerdao;
    private final EmployeeDAO employeedao;
    private int i = 0;
    public JPanel PANEL1;
    public JPanel PANEL2;
    public JPanel PANEL3;
    public JPanel PANELDATE;
    public boolean iscreated = false;
    public boolean iscomplete = false;
    public boolean newbuyer = false;
    public boolean newseller = false;
    public boolean newemployee = false;
    public boolean mailok = false;
    public String[] days;
    public String[] months;
    public String[] years;
    public JLabel firstname;
    public JLabel lastname;
    public JLabel mail;
    public JLabel password;
    public JLabel number;
    public JLabel adress;
    public JLabel birthdate;
    public JLabel usermail;
    public JLabel typeofuser;
    public JTextField newfirstname;
    public JTextField newlastname;
    public JTextField newmail;
    public JPasswordField newpassword;
    public JPasswordField newnumber;
    public JTextField newadress;
    public JComboBox birthday;
    public JComboBox birthmonth;
    public JComboBox birthyear;
    public JComboBox user;
    public JButton createaccount;
    public JButton back;

    public NewAccount(SellerDAO sellerdao, BuyerDAO buyerdao, EmployeeDAO employeedao) throws Exception {
        final ImageIcon icon = new ImageIcon("angleLogo2.png");
        JLabel LOGO = new JLabel(icon);
        this.sellerdao = sellerdao;
        this.buyerdao = buyerdao;
        this.employeedao = employeedao;
        this.setSize(1000, 600);
        this.setLayout(new BorderLayout(8, 8));
        PANEL1 = new JPanel();
        PANEL2 = new JPanel();
        PANEL3 = new JPanel();
        PANELDATE = new JPanel();
        PANEL1.setPreferredSize(new Dimension(100, 150));
        PANEL2.setPreferredSize(new Dimension(100, 100));
        PANEL3.setPreferredSize(new Dimension(200, 100));
        Color col = Color.white;
        Color col2 = Color.white;
        PANEL1.setBackground(col);
        PANEL2.setBackground(col2);
        PANEL3.setBackground(col);
        PANEL1.setLayout(new BorderLayout());
        PANEL2.setLayout(new GridLayout(12, 3, 8, 8));
        PANELDATE.setLayout(new GridLayout(1, 3));
        days = new String[31];
        months = new String[13];
        years = new String[101];
        //creation of the date comboboxes 
        for (int e = 0; e < 31; e++) {
            days[e] = Integer.toString(e + 1);
        }
        for (int z = 0; z < 12; z++) {
            months[z] = Integer.toString(z + 1);
        }
        for (int a = 100; a >= 0; a--) {
            years[a] = Integer.toString(2020 - a);
        }
        //creation of the text fields for user's info prompting
        Font font = new Font("Times New Roman", Font.PLAIN, 25);
        newfirstname = new JTextField();
        newlastname = new JTextField();
        newmail = new JTextField();
        newpassword = new JPasswordField();
        newnumber = new JPasswordField();
        newadress = new JTextField();
        user = new JComboBox(usertype);
        user.setFont(font);
        user.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        

        //creation of the labels 
        firstname = new JLabel("firstname :", SwingConstants.RIGHT);
        lastname = new JLabel("lastname :", SwingConstants.RIGHT);
        mail = new JLabel("e-mail :", SwingConstants.RIGHT);
        password = new JLabel("password :", SwingConstants.RIGHT);
        number = new JLabel("employee number :", SwingConstants.RIGHT);
        adress = new JLabel("address :", SwingConstants.RIGHT);
        birthdate = new JLabel("birthdate :", SwingConstants.RIGHT);
        typeofuser = new JLabel("type of user :", SwingConstants.RIGHT);
        createaccount = new JButton("create account");
        back = new JButton("back");
        createaccount.setEnabled(false);
        //setting the fonts
        firstname.setFont(font);
        lastname.setFont(font);
        mail.setFont(font);
        password.setFont(font);
        number.setFont(font);
        adress.setFont(font);
        birthdate.setFont(font);
        createaccount.setFont(font);
        typeofuser.setFont(font);
        back.setFont(font);
        newlastname.setFont(font);
        newfirstname.setFont(font);
        newmail.setFont(font);
        newnumber.setFont(font);
        newpassword.setFont(font);
        createaccount.setFocusable(false);
        back.setFocusable(false);
        newnumber.setEnabled(false);
        //creating comboboxes
        birthday = new JComboBox(days);
        birthmonth = new JComboBox(months);
        birthyear = new JComboBox(years);
        birthday.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        birthmonth.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        birthyear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //setting combo boxes bounds
        birthday.setFont(font);
        birthmonth.setFont(font);
        birthyear.setFont(font);
        newadress.setFont(font);
        //adding all components
        this.add(PANEL1, BorderLayout.NORTH);
        PANEL1.add(LOGO, BorderLayout.EAST);
        this.add(PANEL2, BorderLayout.CENTER);
        //PANEL1.add(PANEL3, BorderLayout.EAST);
        PANELDATE.setBackground(col);
        PANELDATE.add(birthday);
        PANELDATE.add(birthmonth);
        PANELDATE.add(birthyear);
        PANEL2.add(new JLabel());
        PANEL2.add(new JLabel());
        PANEL2.add(new JLabel());
        PANEL2.add(typeofuser);
        PANEL2.add(user);
        PANEL2.add(new JLabel());
        PANEL2.add(firstname);
        PANEL2.add(newfirstname);
        PANEL2.add(new JLabel());
        PANEL2.add(lastname);
        PANEL2.add(newlastname);
        PANEL2.add(new JLabel());
        PANEL2.add(adress);
        PANEL2.add(newadress);
        PANEL2.add(new JLabel());
        PANEL2.add(mail);
        PANEL2.add(newmail);
        PANEL2.add(new JLabel());
        PANEL2.add(password);
        PANEL2.add(newpassword);
        PANEL2.add(new JLabel());
        PANEL2.add(number);
        PANEL2.add(newnumber);
        PANEL2.add(new JLabel());
        PANEL2.add(birthdate);
        PANEL2.add(PANELDATE);
        PANEL2.add(new JLabel());
        PANEL2.add(new JLabel());
        PANEL2.add(createaccount);
        PANEL2.add(new JLabel());
        PANEL2.add(new JLabel());
        PANEL2.add(back);
        PANEL2.add(new JLabel());
        PANEL2.add(new JLabel());
        PANEL2.add(new JLabel());
        PANEL2.add(new JLabel());

    }

    public void createAccount(ActionEvent ae, Vector<Seller> listOfSellers, Vector<Buyer> listOfBuyers, Vector<Employee> listOfEmployees) throws SQLException {
        createaccount.setEnabled(false);
        if (user.getSelectedItem().equals("Employee")) {
            if (i == 0) {
                JOptionPane.showMessageDialog(null, "You must have an employee number to create an account. This number is given by your superior.", "Employee account", JOptionPane.INFORMATION_MESSAGE);
            }
            i = 1;
            newnumber.setEnabled(true);
            newemployee = true;
            newbuyer = false;
            newseller = false;
        } else if (user.getSelectedItem().equals("Buyer")) {
            newnumber.setEnabled(false);
            newbuyer = true;
            newseller = false;
            newemployee = false;
        } else if (user.getSelectedItem().equals("Seller")) {
            newnumber.setEnabled(false);
            newseller = true;
            newemployee = false;
            newbuyer = false;

        }
        String fstname = newfirstname.getText();
        String lstname = newlastname.getText();
        String mailfinal = newmail.getText();
        String pass = newpassword.getText();
        String adressfinal = newadress.getText();
        int d = 0;
        int m = 0;
        int y = 0;
        if (ae.getSource() == birthday) {
            String day = (String) birthday.getSelectedItem();
            System.out.println("day :" + day);
            d = Integer.parseInt(day);
        }
        if (ae.getSource() == birthmonth) {
            String month = (String) birthmonth.getSelectedItem();
            System.out.println("month :" + month);
            m = Integer.parseInt(month);
        }
        if (ae.getSource() == birthyear) {
            String year = (String) birthyear.getSelectedItem();
            System.out.println("year :" + year);
            y = Integer.parseInt(year);
        }

        if (!newemployee && (newseller || newbuyer) && ((!fstname.equals(""))
                && (!lstname.equals(""))
                && (!pass.equals(""))
                && (!adressfinal.equals(""))
                && (!mailfinal.equals("")))) {
            iscomplete = true;
        } else {
            iscomplete = false;
        }
        if (newemployee) {
            String employeenb = newnumber.getText();
            createaccount.setEnabled(false);
            if (newemployee && (!fstname.equals(""))
                    && (!lstname.equals(""))
                    && (!mailfinal.equals(""))
                    && (!adressfinal.equals(""))
                    && (!pass.equals(""))
                    && (!employeenb.equals(""))) {
                iscomplete = true;
                repaint();
            } else {
                iscomplete = false;
            }

        }
        if (iscomplete) {
            createaccount.setEnabled(true);
            repaint();
        } else {
            createaccount.setEnabled(false);
            repaint();
        }
        if (ae.getSource() == createaccount) {
            if (newseller) {
                try {
                    if (!sellerdao.mailCheck(mailfinal)) {
                        newmail.setText("");
                        mailok = false;
                        iscreated = false;
                    } else {
                        mailok = true;
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Could not create your account.", "Account creation failed", JOptionPane.WARNING_MESSAGE);
                    Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                }
                String day = (String) birthday.getSelectedItem();
                d = Integer.parseInt(day);
                String month = (String) birthmonth.getSelectedItem();
                m = Integer.parseInt(month);
                String year = (String) birthyear.getSelectedItem();
                y = Integer.parseInt(year);
                Date date = new Date(y, m, d);
                date.setDate(d);
                date.setMonth(m);
                date.setYear(y);
                int id = 1;
                System.out.println("new seller :");
                Seller seller = new Seller(id, fstname, lstname, date, adressfinal, mailfinal);
                seller.setPassword(pass);
                seller.showInfos();
                listOfSellers.add(seller);
                if (mailok) {
                    sellerdao.addSellerToDataBase(seller);
                    iscreated = true;
                }
            } else if (newbuyer) {
                try {
                    if (!buyerdao.mailCheck(mailfinal)) {
                        newmail.setText("");
                        mailok = false;
                        iscreated = false;
                    } else {
                        mailok = true;
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Could not create your account.", "Account creation failed", JOptionPane.WARNING_MESSAGE);
                    Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                }
                String day = (String) birthday.getSelectedItem();
                d = Integer.parseInt(day);
                String month = (String) birthmonth.getSelectedItem();
                m = Integer.parseInt(month);
                String year = (String) birthyear.getSelectedItem();
                y = Integer.parseInt(year);
                Date date = new Date(y, m, d);
                date.setDate(d);
                date.setMonth(m);
                date.setYear(y);
                int id = 1;
                System.out.println("new buyer :");
                Buyer buyer = new Buyer(id, fstname, lstname, date, adressfinal, mailfinal);
                buyer.setPassword(pass);
                buyer.showInfos();
                listOfBuyers.add(buyer);
                if (mailok) {
                    try {
                        buyerdao.addBuyerToDataBase(buyer);
                        iscreated = true;
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Could not create your account.", "Account creation failed", JOptionPane.WARNING_MESSAGE);
                        Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);

                    }
                }
            } else if (newemployee) {
                try {
                    if (!employeedao.mailCheck(mailfinal)) {
                        newmail.setText("");
                        mailok = false;
                        iscreated = false;
                    } else {
                        mailok = true;
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Could not create your account.", "Account creation failed", JOptionPane.WARNING_MESSAGE);
                    Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                }
                String day = (String) birthday.getSelectedItem();
                d = Integer.parseInt(day);
                String month = (String) birthmonth.getSelectedItem();
                m = Integer.parseInt(month);
                String year = (String) birthyear.getSelectedItem();
                y = Integer.parseInt(year);
                Date date = new Date(y, m, d);
                date.setDate(d);
                date.setMonth(m);
                date.setYear(y);
                int id = 1;
                System.out.println("new employee :");
                int employeenb = Integer.parseInt(newnumber.getText());
                Employee employee = new Employee(id, fstname, lstname, date, adressfinal, mailfinal);
                employee.setPassword(pass);
                employee.setEmployeeNb(employeenb);
                listOfEmployees.add(employee);
                employee.showInfos();
                if (mailok) {
                    try {
                        employeedao.addEmployeeToDataBase(employee);
                        iscreated = true;
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Could not create your account.", "Account creation failed", JOptionPane.WARNING_MESSAGE);
                        Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);

                    }
                }
            }
        }
        if (iscreated) {
            JOptionPane.showMessageDialog(null, "your account has successfully been created ", "Account created", JOptionPane.PLAIN_MESSAGE);
            iscreated = false;
            clearAll();
        }

    }

    public void clearAll() {
        this.newfirstname.setText("");
        this.newlastname.setText("");
        this.newmail.setText("");
        this.newpassword.setText("");
        this.newnumber.setText("");
        this.newadress.setText("");
        birthday.setSelectedIndex(0);
        birthmonth.setSelectedIndex(0);
        birthyear.setSelectedIndex(0);
        user.setSelectedIndex(0);
    }

    public boolean accountCreated() {
        return iscreated;
    }
}
