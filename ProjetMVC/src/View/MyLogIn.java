 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.EmployeeDAO;
import Model.BuyerDAO;
import Model.SellerDAO;
import Model.SendEmail2;
import Model.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author eliot
 */
public class MyLogIn extends JPanel implements MyLogInConstant {

    public User actualuser;
    private final SellerDAO sellerdao;
    private final BuyerDAO buyerdao;
    private final EmployeeDAO employeedao;
    public JPanel PANEL1;
    public JPanel PANEL2;
    public JPanel PANEL3;
    public JPanel PANEL4;
    public JComboBox user;
    public boolean logedin = false;
    public boolean createnewaccount = false;
    public JButton enter;
    public JButton forgottenpass;
    public JButton newaccount;
    public JButton visitor;
    public JLabel mail;
    public JLabel typeofuser;
    public JLabel password;
    public JLabel employeenb;
    public JTextField usermail;
    public JPasswordField userpassword;
    public JPasswordField useremployeenb;
    public boolean employeeuser = false;
    public boolean buyeruser = false;
    public boolean selleruser = false;
    BufferedImage background;
    Image img;
    Image logo;

    public MyLogIn(SellerDAO sellerdao, BuyerDAO buyerdao, EmployeeDAO employeedao) throws Exception {
        final ImageIcon icon = new ImageIcon("backgroundEstate.png");
        final ImageIcon icon2 = new ImageIcon("logoEstate.png");
        JLabel LOGO = new JLabel(icon2);
        img = icon.getImage();
        logo = icon2.getImage();
        // initialiseur d'instance
        {
            setOpaque(false);
        }

        this.setBackground(Color.white);
        this.buyerdao = buyerdao;
        this.employeedao = employeedao;
        this.sellerdao = sellerdao;
        Font font = new Font("Times New Roman", Font.PLAIN, 25);
        Font font2 = new Font("Times New Roman", Font.PLAIN, 20);
        this.setLayout(new BorderLayout(10, 10));
        this.setSize(1000, 600);
        PANEL1 = new JPanel();
        PANEL2 = new JPanel();
        PANEL3 = new JPanel();
        PANEL4 = new JPanel();
        PANEL1.setBackground(Color.WHITE);
        PANEL2.setBackground(Color.WHITE);
        PANEL3.setBackground(Color.WHITE);
        PANEL4.setBackground(Color.WHITE);
        PANEL1.setPreferredSize(new Dimension(100, 300));
        PANEL2.setPreferredSize(new Dimension(100, 100));
        PANEL3.setPreferredSize(new Dimension(200, 100));
        PANEL4.setPreferredSize(new Dimension(200, 100));
        PANEL1.setLayout(new BorderLayout(5, 5));
        PANEL2.setLayout(new GridLayout(9, 3, 8, 8));
        PANEL3.setLayout(new GridLayout(5, 1, 3, 3));
        user = new JComboBox(usertype);
        typeofuser = new JLabel("type of user :", SwingConstants.RIGHT);
        mail = new JLabel("e-mail :", SwingConstants.RIGHT);
        password = new JLabel("password :", SwingConstants.RIGHT);
        employeenb = new JLabel("employee number :", SwingConstants.RIGHT);
        enter = new JButton("login");
        newaccount = new JButton("new account");
        visitor = new JButton("continue as a visitor");
        forgottenpass = new JButton("forgot password ?");
        usermail = new JTextField();
        userpassword = new JPasswordField();
        useremployeenb = new JPasswordField();
        enter.setEnabled(false);
        userpassword.setFont(font);
        typeofuser.setFont(font);
        usermail.setFont(font);
        mail.setFont(font);
        password.setFont(font);
        employeenb.setFont(font);
        user.setFont(font);
        enter.setFont(font);
        newaccount.setFont(font2);
        visitor.setFont(font2);
        useremployeenb.setFont(font);
        forgottenpass.setFont(font);
        enter.setFocusable(false);
        newaccount.setFocusable(false);
        visitor.setFocusable(false);
        forgottenpass.setFocusable(false);
        forgottenpass.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        forgottenpass.setContentAreaFilled(false);
        this.add(PANEL1, BorderLayout.NORTH);
        this.add(PANEL2, BorderLayout.CENTER);
        PANEL1.add(PANEL3, BorderLayout.EAST);
        PANEL1.add(PANEL4, BorderLayout.WEST);
        PANEL1.add(LOGO, BorderLayout.CENTER);
        PANEL2.add(new JLabel());
        PANEL2.add(new JLabel());
        PANEL2.add(new JLabel());
        PANEL2.add(typeofuser);
        PANEL2.add(user);
        PANEL2.add(new JLabel());
        PANEL2.add(mail);
        PANEL2.add(usermail);
        PANEL2.add(new JLabel());
        PANEL2.add(password);
        PANEL2.add(userpassword);
        PANEL2.add(new JLabel());
        PANEL2.add(employeenb);
        PANEL2.add(useremployeenb);
        PANEL2.add(new JLabel());
        PANEL2.add(new JLabel());
        PANEL2.add(enter);
        PANEL2.add(forgottenpass);
        PANEL2.add(new JLabel());
        PANEL2.add(new JLabel());
        PANEL2.add(forgottenpass);
        PANEL2.add(new JLabel());
        PANEL3.add(new JLabel());
        PANEL3.add(newaccount);
        PANEL3.add(visitor);
        PANEL3.add(new JLabel());
        PANEL3.add(new JLabel());

    }

    @Override
    public void paintComponent(Graphics graphics) {
        graphics.drawImage(img, 0, 0, this);
        super.paintComponent(graphics);
    }

    //can be used by the main frame to know if the user is loged or not
    public boolean isLoged() {
        return this.logedin;
    }

    public boolean createNewAccount() {
        return this.createnewaccount;
    }

    public void clearTextFields() {
        userpassword.setText("");
        usermail.setText("");
        useremployeenb.setText("");
        user.setSelectedIndex(0);
    }

    public void login(ActionEvent ae) throws Exception {
        if (!logedin) {
            if (user.getSelectedItem().equals("Employee")) {
                useremployeenb.setEnabled(true);
                employeeuser = true;
                buyeruser = false;
                selleruser = false;
                enter.setEnabled(true);
                repaint();
            } else if (user.getSelectedItem().equals("Buyer")) {
                useremployeenb.setEnabled(false);
                buyeruser = true;
                selleruser = false;
                employeeuser = false;
                enter.setEnabled(true);
                repaint();
            } else if (user.getSelectedItem().equals("Seller")) {
                useremployeenb.setEnabled(false);
                selleruser = true;
                employeeuser = false;
                buyeruser = false;
                enter.setEnabled(true);
                repaint();
            } else {
                enter.setEnabled(false);
            }
            if (ae.getSource() == enter) {
                if (employeeuser) {

                    if (!useremployeenb.getText().equals("")) {
                        if (employeedao.profileCheck(usermail.getText(), (String) userpassword.getText(), Integer.parseInt(useremployeenb.getText()))) {
                            this.actualuser = employeedao.getEmployeeAccount(usermail.getText(), (String) userpassword.getText(), Integer.parseInt(useremployeenb.getText()));
                            this.logedin = true;
                        } else {
                            this.logedin = false;
                        }
                    }

                } else if (selleruser) {
                    if (sellerdao.profileCheck(usermail.getText(), (String) userpassword.getText())) {
                        this.actualuser = sellerdao.getSellerAccount(usermail.getText(), (String) userpassword.getText());
                        this.logedin = true;
                    } else {
                        this.logedin = false;
                    }

                } else if (buyeruser) {
                    if (buyerdao.profileCheck(usermail.getText(), (String) userpassword.getText())) {
                        this.actualuser = buyerdao.getBuyerAccount(usermail.getText(), (String) userpassword.getText());
                        System.out.println("buyer from login : ");
                        this.actualuser.showInfos();
                        this.logedin = true;
                    } else {
                        this.logedin = false;
                    }
                } else if (ae.getSource() == newaccount) {
                    this.createnewaccount = true;
                }

            } else if (ae.getSource() == forgottenpass) {
                if (user.getSelectedItem().equals(" ")) {
                    JOptionPane.showMessageDialog(null, "Please select the type of user you are.", "Password changed.", JOptionPane.WARNING_MESSAGE);
                } else {
                    String mail = JOptionPane.showInputDialog(null, "Please type your address : ", "Enter adress.", JOptionPane.INFORMATION_MESSAGE);
                    if ((mail != null) && (mail.length() > 0)) {
                        String newpass = Integer.toString(ThreadLocalRandom.current().nextInt(10000, 90000 + 1));
                        System.out.println("mail : " + mail);
                        System.out.println("new password : " + newpass);
                        String content = "Your HouseClip password has been updated. Here is your new password : " + newpass + "\nThis email is automatically generated. Please do not answer back.";
                        String subject = "HouseClip-Password changed";
                        if (buyeruser) {
                            this.actualuser = buyerdao.getBuyerAccount(mail);
                            buyerdao.modifyPassword(this.actualuser, newpass);
                            new SendEmail2((String) mail, subject, content);
                        } else if (selleruser) {
                            this.actualuser = sellerdao.getSellerAccount(mail);
                            sellerdao.modifyPassword(this.actualuser, newpass);
                            new SendEmail2((String) mail, subject, content);
                        } else if (employeeuser) {
                            this.actualuser = employeedao.getEmployeeAccount(mail);
                            employeedao.modifyPassword(this.actualuser, newpass);
                            new SendEmail2((String) mail, subject, content);

                        }
                        JOptionPane.showMessageDialog(null, "We have sent a new password to the following email address : \n" + mail + "\nThis email is automatic. Please do not answer it.", "Email sent", JOptionPane.INFORMATION_MESSAGE);

                    }

                }
            }
        }
    }

    public User getUser() {
        return this.actualuser;
    }
}
