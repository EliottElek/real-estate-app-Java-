package Model;

import View.MyProfileModify;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public abstract class User {

    protected String firstname;
    protected String lastname;
    protected Date birthdate;
    protected String adress;
    protected String mail;
    protected String password;
    protected int id;
    protected ArrayList<Booking> bookings;
    public JPanel PANELINFOS;
    public MyProfileModify PANELMODIFY;
    public JLabel labelfirstname;
    public JLabel labellastname;
    public JLabel labeladress;
    public JLabel labelemail;
    public JLabel labeldate;
    Font font;

    public User(int id, String firstname, String lastname, Date birthdate, String adress, String mail) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.adress = adress;
        this.mail = mail;
        font = new Font("Times New Roman", Font.PLAIN, 25);
        PANELINFOS = new JPanel();
        PANELMODIFY = new MyProfileModify(this);
        bookings = new ArrayList();
        PANELINFOS.setPreferredSize(new Dimension(100,1200));
        showInfosMyProfile();
    }

    public int getID() {
        return this.id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstName() {
        return this.firstname;
    }

    public String getLastName() {
        return this.lastname;
    }

    public String getAdress() {
        return this.adress;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String mail) {
        this.mail = mail;
    }

    public String getEmail() {
        return this.mail;
    }

    public String getPassword() {
        return this.password;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setBirthDate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getDate() {
        return this.birthdate;
    }

    public int getD() {
        return this.birthdate.getDate();
    }

    public int getM() {
        return this.birthdate.getMonth();
    }

    public int getY() {
        return this.birthdate.getYear();
    }

    public abstract void showInfos();

    private void showInfosMyProfile() {
        labelfirstname = new JLabel(this.firstname);
        labellastname = new JLabel(this.lastname);
        labeladress = new JLabel(this.adress);
        labelemail = new JLabel(this.mail);
        labeldate = new JLabel(this.birthdate.toString());
        labelfirstname.setFont(font);
        labellastname.setFont(font);
        labeladress.setFont(font);
        labelemail.setFont(font);
        labeldate.setFont(font);
        PANELINFOS.setLayout(new GridLayout(5, 2));
        JLabel label1 = new JLabel("FIRSTNAME :    ", SwingConstants.RIGHT);
        JLabel label2 = new JLabel("LASTNAME :    ", SwingConstants.RIGHT);
        JLabel label3 = new JLabel("ADDRESS :    ", SwingConstants.RIGHT);
        JLabel label4 = new JLabel("E-MAIL :    ", SwingConstants.RIGHT);
        JLabel label5 = new JLabel("BIRTHDATE :    ", SwingConstants.RIGHT);
        label1.setFont(font);
        label2.setFont(font);
        label3.setFont(font);
        label4.setFont(font);
        label5.setFont(font);
        PANELINFOS.setLayout(new GridLayout(5, 2));

        PANELINFOS.add(label1);
        PANELINFOS.add(labelfirstname);
        PANELINFOS.add(label2);
        PANELINFOS.add(labellastname);
        PANELINFOS.add(label3);
        PANELINFOS.add(labeladress);
        PANELINFOS.add(label4);
        PANELINFOS.add(labelemail);
        PANELINFOS.add(label5);
        PANELINFOS.add(labeldate);

    }

    public void updateInfosMyProfile() {

        labelfirstname.setText(this.firstname);
        labellastname.setText(this.lastname);
        labeladress.setText(this.adress);
        labeldate.setText(this.birthdate.toString());
        labelemail.setText(this.mail);
        labelfirstname.setFont(font);
        labellastname.setFont(font);
        labeladress.setFont(font);
        labeldate.setFont(font);
        labelemail.setFont(font);
    }

    public void addBooking(Booking booking) {
        this.bookings.add(booking);
    }

    public ArrayList getAllBookings() {
        return this.bookings;
    }

    public void removeBooking(Booking booking) {
        this.bookings.remove(booking);
    }
}
