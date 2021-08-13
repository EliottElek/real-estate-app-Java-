package Model;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public abstract class Estate implements SellerConstInterface {

    private final Blob image1;
    private final Blob image2;
    private final Blob image3;
    private final Blob[] images;
    private String location;
    private String adress;
    private int price;
    private int area;
    private int nbrooms;
    private int nbbthrooms;
    private String description;
    private final int id;
    private final ArrayList<Booking> bookings;
    public JPanel infos;
    public JPanel imgs;
    public JPanel buyersbuttons;
    public JButton modify;
    public JButton save;
    public JButton delete;
    public JButton booking;
    public JButton book;
    public JButton makeanoffer;
    public JComboBox datebox;
    public JLabel[] imagefin = new JLabel[3];
    public JScrollPane imagesScroller;
    public JComboBox newlocation;
    public JComboBox newnbrooms;
    public JComboBox newnbbthrooms;
    public JTextField newarea;
    public JTextField newdescription;
    public JTextField newprice;
    public JTextField newadress;
    public JLabel thislocation;
    public JLabel thisadress;
    public JLabel thisprice;
    public JLabel thisarea;
    public JLabel thisdescription;
    public JLabel thisnbrooms;
    public JLabel thisnbbthrooms;
    private JOptionPane jop;
    private final Font font;

    public Estate(int id, String location, String adress, int price, int area, String description, int nbrooms, int nbbthrooms, Blob image1, Blob image2, Blob image3, int factor) throws IOException {
        font = new Font("Times New Roman", Font.PLAIN, 25);
        this.id = id;
        this.nbrooms = nbrooms;
        this.nbbthrooms = nbbthrooms;
        this.location = location;
        this.adress = adress;
        this.price = price;
        this.area = area;
        this.description = description;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        images = new Blob[3];
        bookings = new ArrayList();
        images[0] = this.image1;
        images[1] = this.image2;
        images[2] = this.image3;
        infos = new JPanel();
        imgs = new JPanel();
        buyersbuttons = new JPanel();
        buyersbuttons.setLayout(new GridLayout(1, 2));
        modify = new JButton("modify");
        save = new JButton("save");
        delete = new JButton("delete");
        booking = new JButton("BOOK");
        makeanoffer = new JButton("make an offer");
        modify.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        save.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        booking.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        makeanoffer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        modify.setFont(font);
        save.setFont(font);
        delete.setFont(font);
        booking.setFont(font);
        makeanoffer.setFont(font);
        delete.setBackground(new Color(240, 60, 60));
        booking.setVisible(false);
        //this.setInfos();
        modify.setFocusable(false);
        booking.setFocusable(false);
        delete.setFocusable(false);
        save.setFocusable(false);
        modify.setVisible(false);
        delete.setVisible(false);
        this.book = new JButton("book");
    }

    /*
    add infos on the panel of which the property appears
    */
    public void setInfos() {
        delete.setBackground(Color.red);
        infos.removeAll();
        infos.setLayout(new GridLayout(12, 1));
        Font font = new Font("Times New Roman", Font.PLAIN, 25);
        JLabel thislocation = new JLabel("Location : " + this.location);
        JLabel thisadress = new JLabel("address : " + this.adress);
        JLabel thisprice = new JLabel("price : " + this.price + "$");
        JLabel thisarea = new JLabel("area : " + this.area + "m²");
        JTextArea thisdescription = new JTextArea("description : " + this.description);
        JLabel thisnbrooms = new JLabel("number of bedrooms : " + this.nbrooms);
        JLabel thisnbbthrooms = new JLabel("number of bathrooms : " + this.nbbthrooms);
        thisdescription.setLineWrap(true);
        thisdescription.setWrapStyleWord(true);
        modify.setFont(font);
        delete.setFont(font);
        thislocation.setFont(font);
        thisadress.setFont(font);
        thisprice.setFont(font);
        thisarea.setFont(font);
        thisnbbthrooms.setFont(font);
        thisnbrooms.setFont(font);
        thisdescription.setFont(font);
        infos.add(thislocation);
        infos.add(thisadress);
        infos.add(thisprice);
        infos.add(thisarea);
        infos.add(thisnbrooms);
        infos.add(thisnbbthrooms);
        infos.add(thisdescription);
        infos.add(new JLabel());
        buyersbuttons.add(booking);
        //buyersbuttons.add(makeanoffer);
        infos.add(buyersbuttons);
        infos.add(delete);
        infos.add(modify);
    }

    
    /*
    shows the booking frame with the combobox with the dates
    */
    public void showBookingFrame(java.sql.Date[] dates, ActionEvent ae) {
        this.datebox = new JComboBox(dates);
        Object[] options = new Object[]{};
        jop = new JOptionPane("Please Select",
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                null, options, null);

        //add combos to JOptionPane
        jop.add(this.datebox);
        jop.add(this.book);

        //create a JDialog and add JOptionPane to it 
        JDialog diag = new JDialog();
        diag.getContentPane().add(jop);
        diag.pack();
        diag.setVisible(true);
        diag.setLocationRelativeTo(null);
    }

    public void closeBookingFrame() {
        JOptionPane.getRootFrame().dispose();
    }

    /*
    function that replaces labels with textfields for infos modifications
    */
    public void setModifyingInfos() {
        infos.removeAll();
        newadress = new JTextField(this.adress);
        newarea = new JTextField(Integer.toString(this.area));
        newdescription = new JTextField(this.description);
        newprice = new JTextField(Integer.toString(this.price));
        newlocation = new JComboBox(typesoflocations);
        newlocation.setSelectedItem((String)this.location);
        newnbrooms = new JComboBox(maxrooms);
        newnbrooms.setSelectedItem((Integer.toString(this.nbrooms)));
        newnbbthrooms = new JComboBox(maxrooms);
        newnbbthrooms.setSelectedItem((Integer.toString(this.nbbthrooms)));
        newlocation.setSelectedItem(this.location);
        infos.setLayout(new GridLayout(10, 2));
        //Font font2 = new Font("Times New Roman", Font.PLAIN, 19);
        thislocation = new JLabel("Location : ");
        thisadress = new JLabel("address : ");
        thisprice = new JLabel("price : ");
        thisarea = new JLabel("area : ");
        thisdescription = new JLabel("description : ");
        thisnbrooms = new JLabel("number of bedrooms : ");
        thisnbbthrooms = new JLabel("number of bathrooms : ");
        modify.setFont(font);
        save.setFont(font);
        newadress.setFont(font);
        newarea.setFont(font);
        newdescription.setFont(font);
        newprice.setFont(font);
        newlocation.setFont(font);
        thislocation.setFont(font);
        thisadress.setFont(font);
        thisprice.setFont(font);
        thisarea.setFont(font);
        thisnbrooms.setFont(font);
        thisnbbthrooms.setFont(font);
        thisdescription.setFont(font);
        newnbrooms.setFont(font);
        newnbbthrooms.setFont(font);
        infos.add(thislocation);
        infos.add(newlocation);
        infos.add(thisadress);
        infos.add(newadress);
        infos.add(thisprice);
        infos.add(newprice);
        infos.add(thisarea);
        infos.add(newarea);
        infos.add(thisnbrooms);
        infos.add(newnbrooms);
        infos.add(thisnbbthrooms);
        infos.add(newnbbthrooms);
        infos.add(thisdescription);
        infos.add(newdescription);
        infos.add(booking);
        infos.add(delete);
        infos.add(save);
        infos.add(new JLabel());
    }

    /*
    add infos on panel
    */
    public void addInfosOnPanel(JPanel panel, boolean modif) throws IOException {
        if (!modif) {
            this.setInfos();
        } else if (modif) {
            this.setModifyingInfos();
        }
        imgs.setLayout(new GridLayout(1, 3));
        for (int i = 0; i < 3; i++) {
            byte[] imagebytes = null;
            try {
                imagebytes = images[i].getBytes(1, (int) images[i].length());
            } catch (SQLException ex) {
                Logger.getLogger(Estate.class.getName()).log(Level.SEVERE, null, ex);
            }
            BufferedImage theImage = ImageIO.read(new ByteArrayInputStream(imagebytes));
            imagefin[i] = new JLabel(new ImageIcon(theImage));
            imgs.add(imagefin[i]);
        }
        imagesScroller = new JScrollPane(imgs);
        imagesScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        imagesScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        panel.add(imagesScroller);
        panel.add(infos);

    }

    
    /*
    getters and setters
    */
    public void removeInfos(JPanel panel) {

        panel.removeAll();
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getID() {
        return this.id;
    }

    public String getAdress() {
        return this.adress;
    }

    public String getLocation() {
        return this.location;
    }

    public int getArea() {
        return this.area;
    }

    public int getPrice() {
        return this.price;
    }

    String getDescription() {
        return this.description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setSize(int size) {
        this.area = size;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public JButton getModify() {
        return this.modify;
    }

    public void setVisibleButton(boolean bool) {
        modify.setVisible(bool);
    }

    public void addBooking(Booking booking) {
        this.bookings.add(booking);
    }

    public void removeBooking(Booking booking) {
        this.bookings.remove(booking);
    }

    public ArrayList<Booking> getAllBookings() {
        return this.bookings;
    }

    public void setNbRooms(int newnbrooms) {
       this.nbrooms = newnbrooms;
    }

    public void setNbBthRooms(int newnbthrooms) {
        this.nbbthrooms = newnbthrooms;
    }

    int getNbBthRooms() {
        return this.nbbthrooms;
    }

    int getNbRooms() {
       return this.nbrooms;
    }
}
