package View;

import Model.Booking;
import Model.BookingDAO;
import Model.EmployeeDAO;
import Model.BuyerDAO;
import Model.SellerDAO;
import Model.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class MyProfile extends JPanel {

    private boolean deletebool = false;
    public User user;
    public JPanel PANEL1;
    public JPanel PANEL2;
    public JPanel PANEL3;
    public JPanel PANEL4;
    public JPanel PANEL5;
    public JPanel COMMANDPANEL;
    public JPanel BOOKINGS;
    public JPanel back;
    public JButton modify;
    public JButton delete;
    public JButton seebookings;
    public JButton backbutton;
    public JButton save;
    public JButton logout;
    public JButton changepassword;
    public JButton change;
    public JButton cancel;
    public JOptionPane jop;
    private final JLabel pass1;
    private final JLabel pass2;
    public JPasswordField newpass1;
    public JPasswordField newpass2;
    ArrayList<Booking> bookings;
    public boolean modif = false;
    private final SellerDAO sellerdao;
    private final BuyerDAO buyerdao;
    private final EmployeeDAO employeedao;
    private final BookingDAO bookingdao;
    private final Font font;
    JScrollPane SCROLLER;

    public MyProfile(User user, SellerDAO sellerdao, BuyerDAO buyerdao, EmployeeDAO employeedao, BookingDAO bookingdao) throws Exception {
        final ImageIcon icon = new ImageIcon("angleLogo2.png");
        JLabel LOGO = new JLabel(icon);
        font = new Font("Times New Roman", Font.PLAIN, 25);
        Color red = new Color(240, 60, 60);
        this.pass1 = new JLabel("new password : ", SwingConstants.RIGHT);
        this.pass2 = new JLabel("verify new password : ", SwingConstants.RIGHT);
        this.change = new JButton("save");
        this.cancel = new JButton("cancel");
        this.user = user;
        this.sellerdao = sellerdao;
        this.buyerdao = buyerdao;
        this.employeedao = employeedao;
        this.bookingdao = bookingdao;
        newpass1 = new JPasswordField();
        newpass2 = new JPasswordField();
        this.setVisible(true);
        PANEL1 = new JPanel();
        PANEL2 = new JPanel();
        PANEL3 = new JPanel();
        PANEL4 = new JPanel();
        PANEL5 = new JPanel();
        COMMANDPANEL = new JPanel();
        BOOKINGS = new JPanel();
        back = new JPanel();
        backbutton = new JButton("<- back");
        changepassword = new JButton("change my password");
        modify = new JButton("modify profile");
        delete = new JButton("delete my account");
        seebookings = new JButton("see my bookings");
        logout = new JButton("log out");
        save = new JButton("save");
        
        backbutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        changepassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        modify.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        seebookings.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        save.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        save.setVisible(false);
        delete.setBackground(Color.red);
        this.setLayout(new BorderLayout(10, 10));
        PANEL1.setLayout(new BorderLayout(10, 10));
        back.setLayout(new GridLayout(3, 1));
        PANEL5.setLayout(new GridLayout(2, 1));
        COMMANDPANEL.setPreferredSize(new Dimension(200, 1200));
        COMMANDPANEL.setLayout(new GridLayout(4, 7));
        backbutton.setFont(font);
        modify.setFont(font);
        delete.setFont(font);
        delete.setBackground(red);
        seebookings.setFont(font);
        this.change.setFont(font);
        this.cancel.setFont(font);
        logout.setFont(font);
        save.setFont(font);
        pass1.setFont(font);
        pass2.setFont(font);
        newpass1.setFont(font);
        newpass2.setFont(font);
        changepassword.setFont(font);
        backbutton.setFocusable(false);
        modify.setFocusable(false);
        delete.setFocusable(false);
        seebookings.setFocusable(false);
        logout.setFocusable(false);
        save.setFocusable(false);
        change.setFocusable(false);
        cancel.setFocusable(false);
        changepassword.setFocusable(false);
        //setting the sizes of PANELS
        PANEL1.setPreferredSize(new Dimension(200, 100));
        PANEL2.setPreferredSize(new Dimension(200, 100));
        PANEL3.setPreferredSize(new Dimension(200, 100));
        PANEL4.setPreferredSize(new Dimension(200, 100));
        PANEL5.setPreferredSize(new Dimension(200, 100));
        back.setPreferredSize(new Dimension(200, 100));
        //setting PANELS colors 
        Color col = Color.white;
        PANEL1.setBackground(col);
        PANEL2.setBackground(col);
        PANEL3.setBackground(col);
        PANEL4.setBackground(col);
        PANEL5.setBackground(col);
        back.setBackground(col);
        this.add(PANEL1, BorderLayout.NORTH);
        this.add(PANEL2, BorderLayout.SOUTH);
        this.add(PANEL3, BorderLayout.WEST);
        this.add(PANEL4, BorderLayout.EAST);
        this.add(PANEL5, BorderLayout.CENTER);
        COMMANDPANEL.add(new JLabel());
        COMMANDPANEL.add(changepassword);
        COMMANDPANEL.add(modify);
        COMMANDPANEL.add(seebookings);
        //COMMANDPANEL.add(logout);
        COMMANDPANEL.add(delete);
        COMMANDPANEL.add(save);
        COMMANDPANEL.add(new JLabel());
        back.add(new JLabel());
        back.add(backbutton);
        bookings = new ArrayList();
        PANEL1.add(back, BorderLayout.WEST);
        PANEL1.add(LOGO, BorderLayout.EAST);
        for (int i = 0; i < 12; i++) {
            COMMANDPANEL.add(new JLabel());
        }
    }

    public void ShowInfosOfProfile(User user) {
        user.updateInfosMyProfile();
        save.setVisible(false);
        delete.setVisible(true);
        seebookings.setVisible(true);
        modify.setVisible(true);
        logout.setVisible(true);
        changepassword.setVisible(true);
        this.user = user;
        modif = false;
        PANEL5.add(user.PANELINFOS);
        PANEL5.add(COMMANDPANEL, BorderLayout.NORTH);
        repaint();
        invalidate();
        validate();
    }

    public void showPasswordFrame() throws SQLException {

        Object[] options = new Object[]{};
        jop = new JOptionPane("Please enter new password",
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                null, options, null);

        JPanel panel = new JPanel();
        jop.setLayout(new BorderLayout());
        panel.setLayout(new GridLayout(5, 4));
        jop.add(panel, BorderLayout.CENTER);
        //add combos to JOptionPane
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(this.pass1);
        panel.add(this.newpass1);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(this.pass2);
        panel.add(this.newpass2);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(change);
        panel.add(cancel);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        jop.add(panel, BorderLayout.CENTER);
        JDialog diag = new JDialog();
        diag.getContentPane().add(jop);
        diag.pack();
        diag.setVisible(true);
        diag.setLocationRelativeTo(null);
    }

    public void passChange(ActionEvent ae, boolean buyer, boolean seller, boolean employee) throws SQLException {
        if ((ae.getSource() == change)&&(newpass1.getText()!=null)&&(newpass1.getText().length() > 0)&&(newpass2.getText()!=null)&&(newpass2.getText().length() > 0)) {
            if (newpass1.getText().equals((newpass2.getText()))) {
                if (buyer) {
                    buyerdao.modifyPassword(user, (String) newpass1.getText());
                } else if (seller) {
                    sellerdao.modifyPassword(user, (String) newpass1.getText());
                } else if (employee) {
                    employeedao.modifyPassword(user, (String) newpass1.getText());
                }
                JOptionPane.showMessageDialog(null, "Your password has been modified with success.", "Password modified with success", JOptionPane.INFORMATION_MESSAGE);
                newpass1.setText("");
                newpass2.setText("");
                JOptionPane.getRootFrame().dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Passwords do not match.", "fail", JOptionPane.WARNING_MESSAGE);
            }
        } else if (ae.getSource() == cancel) {
            JOptionPane.getRootFrame().dispose();
        }
    }

    public void modifyProfile(User user) {
        //this.user = user;
        save.setVisible(true);
        delete.setVisible(false);
        seebookings.setVisible(false);
        modify.setVisible(false);
        logout.setVisible(false);
        changepassword.setVisible(false);
        modif = true;
        removeInfosOfProfile(user);
        PANEL5.add(user.PANELMODIFY);
        PANEL5.add(COMMANDPANEL, BorderLayout.NORTH);
        repaint();
        invalidate();
        validate();
    }

    public void removeInfosOfProfile(User user) {
        PANEL5.removeAll();
        modif = false;
        repaint();
        invalidate();
        validate();
    }

    public void BuyerModifiedProfile() throws SQLException {
        String firstname = user.PANELMODIFY.firstname.getText();
        String lastname = user.PANELMODIFY.lastname.getText();
        String adress = user.PANELMODIFY.adress.getText();
        String mail = user.PANELMODIFY.mail.getText();
        Date date = new Date(Integer.parseInt((String) user.PANELMODIFY.year.getSelectedItem()) - 1900, Integer.parseInt((String) user.PANELMODIFY.month.getSelectedItem()) - 1, Integer.parseInt((String) user.PANELMODIFY.day.getSelectedItem()));
        this.user.setFirstName(firstname);
        this.user.setLastName(lastname);
        this.user.setAdress(adress);
        this.user.setBirthDate(date);
        this.user.setEmail(mail);
        System.out.println("date buyer:" + date);
        buyerdao.modifyBuyerProfile(this.user);
    }

    public void SellerModifiedProfile() throws SQLException {
        String firstname = user.PANELMODIFY.firstname.getText();
        String lastname = user.PANELMODIFY.lastname.getText();
        String adress = user.PANELMODIFY.adress.getText();
        String mail = user.PANELMODIFY.mail.getText();
        Date date = new Date(Integer.parseInt((String) user.PANELMODIFY.year.getSelectedItem()) - 1900, Integer.parseInt((String) user.PANELMODIFY.month.getSelectedItem()) - 1, Integer.parseInt((String) user.PANELMODIFY.day.getSelectedItem()));
        this.user.setFirstName(firstname);
        this.user.setLastName(lastname);
        this.user.setAdress(adress);
        this.user.setBirthDate(date);
        this.user.setEmail(mail);
        System.out.println("date buyer:" + date);
        sellerdao.modifySellerProfile(this.user);
    }

    public void EmployeeModifiedProfile() throws SQLException {
        String firstname = user.PANELMODIFY.firstname.getText();
        String lastname = user.PANELMODIFY.lastname.getText();
        String adress = user.PANELMODIFY.adress.getText();
        String mail = user.PANELMODIFY.mail.getText();
        Date date = new Date(Integer.parseInt((String) user.PANELMODIFY.year.getSelectedItem()) - 1900, Integer.parseInt((String) user.PANELMODIFY.month.getSelectedItem()) - 1, Integer.parseInt((String) user.PANELMODIFY.day.getSelectedItem()));
        this.user.setFirstName(firstname);
        this.user.setLastName(lastname);
        this.user.setAdress(adress);
        this.user.setBirthDate(date);
        this.user.setEmail(mail);
        System.out.println("date buyer:" + date);
        employeedao.modifyEmployeeProfile(this.user);
    }

    public boolean deleteBooking(ActionEvent ae, User actualuser, MyInterface maininterface) throws SQLException {
        for (Booking booking : bookings) {
            if (ae.getSource() == booking.delete) {
                deletebool = true;
                bookingdao.removeBooking(booking);
                removeInfosOfProfile(actualuser);
                maininterface.displayBuyerInterface();
                maininterface.myprofile.setHasDeleted(false);
                System.out.println("//////////////////////////////////////////////////////////////////");
                maininterface.validAndInvalid();

            } else {
                deletebool = false;
            }
        }
        return deletebool;
    }

    public void showMyBookings(User actualuser, ActionListener al) throws SQLException {

        PANEL5.removeAll();
        bookings = bookingdao.returnBuyersBookings(actualuser);
        if (!bookings.isEmpty()) {
            BOOKINGS.setLayout(new GridLayout(bookings.size(), 1));
            for (Booking booking : bookings) {
                BOOKINGS.add(booking.bookingPANEL);
                booking.delete.addActionListener(al);
            }
            SCROLLER = new JScrollPane(BOOKINGS);
            SCROLLER.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            SCROLLER.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            PANEL5.add(SCROLLER);
            back.setVisible(true);
            save.setVisible(false);
            delete.setVisible(true);
            seebookings.setVisible(true);
            repaint();
            invalidate();
            validate();
        } else {
            JOptionPane.showMessageDialog(null, "You don't have any booking yet.", "no booking", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void cleanBookings() {
        BOOKINGS.removeAll();
    }

    public void setHasDeleted(boolean b) {
        this.deletebool = b;
    }

}
