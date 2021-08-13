/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.BookingDAO;
import Model.BuyerConstInterface;
import static Model.BuyerConstInterface.gardentype;
import static Model.BuyerConstInterface.maxrooms;
import static Model.BuyerConstInterface.minrooms;
import static Model.BuyerConstInterface.propertystate;
import static Model.BuyerConstInterface.rentorbuychoice;
import static Model.BuyerConstInterface.typesoflocations;
import static Model.BuyerConstInterface.typesofproperties;
import static Model.BuyerConstInterface.typesofproximities;
import static Model.BuyerConstInterface.typesrenovation;
import Model.EstateDAO;
import Model.Estate;
import Model.SendEmail2;
import Model.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;

/**
Interface of the buyer. Mostly buttons and panels
 */
public class BuyerInterface extends JPanel implements BuyerConstInterface {

    private final EstateDAO estatedao;
    private final BookingDAO bookingdao;
    public JPanel PANEL1;
    public JPanel PANEL2;
    public JPanel PANEL3;
    public JPanel PANEL4;
    public JPanel PANEL5;
    public JPanel PANEL6;
    public JPanel PANEL7;
    public JPanel PANEL8;
    public JPanel PANEL9;
    public JPanel PANEL10;
    public JPanel PANEL11;
    public JScrollPane SCROLLER;
    public JScrollPane SCROLLER2;
    public JLabel location;
    public JLabel typeofproperty;
    public JLabel pricerange;
    public JLabel pricerangecursor;
    public JLabel proximity;
    public JLabel locateorbuy;
    public JLabel nbrooms;
    public JLabel nbbthrooms;
    public JLabel renovate;
    public JLabel stateofproperty;
    public JLabel minareacursor;
    public JLabel maxareacursor;
    public JLabel area;
    public JLabel garden;
    public JLabel gardenarea;
    public JComboBox typechosen;
    public JComboBox proximitychosen;
    public JComboBox nbminroomschosen;
    public JComboBox nbmaxroomschosen;
    public JComboBox nbminbthroomschosen;
    public JComboBox nbmaxbthroomschosen;
    public JComboBox renovation;
    public JComboBox state;
    public JComboBox locate;
    public JComboBox gardenchoice;
    public JComboBox locatebuybox;
    public JSlider price;
    public JSlider minarea;
    public JSlider maxarea;
    public JSlider gardenareachosen;
    public ButtonGroup group;
    public JButton search;
    public JButton logout;
    public JButton myprofile;
    public JButton back;
    public JButton latestadds;
    public JButton viewall;
    private ArrayList<Estate> properties;

    public BuyerInterface(ArrayList<Estate> properties, EstateDAO estatedao, BookingDAO bookingdao) throws Exception {

        final ImageIcon icon = new ImageIcon("angleLogo.png");
        JLabel LOGO = new JLabel(icon);
        this.properties = properties;
        this.estatedao = estatedao;
        this.bookingdao = bookingdao;
        Font font = new Font("Times New Roman", Font.PLAIN, 22);
        //setting the layout
        this.setLayout(new BorderLayout(20, 20));
        //setting the size
        this.setSize(1000, 600);
        //creating the PANELS
        PANEL1 = new JPanel();
        PANEL2 = new JPanel();
        PANEL3 = new JPanel();
        PANEL4 = new JPanel();
        PANEL5 = new JPanel();
        PANEL6 = new JPanel();
        PANEL7 = new JPanel();
        PANEL8 = new JPanel();
        PANEL9 = new JPanel();
        PANEL10 = new JPanel();
        PANEL11 = new JPanel();
        //setting the sizes of PANELS
        PANEL1.setPreferredSize(new Dimension(200, 150));
        PANEL2.setPreferredSize(new Dimension(200, 30));
        PANEL3.setPreferredSize(new Dimension(200, 100));
        PANEL4.setPreferredSize(new Dimension(200, 100));
        PANEL5.setPreferredSize(new Dimension(200, 100));
        PANEL10.setPreferredSize(new Dimension(250, 100));
        PANEL11.setPreferredSize(new Dimension(200, 800 * (this.properties.size() + 1)));
        //setting PANELS colors 
        Color col = Color.white;
        Color col2 = Color.white;
        this.setBackground(Color.white);
        PANEL1.setBackground(col);
        PANEL2.setBackground(col);
        PANEL3.setBackground(col);
        PANEL4.setBackground(col);
        PANEL5.setBackground(col);
        PANEL6.setBackground(col2);
        PANEL7.setBackground(col2);
        PANEL8.setBackground(col2);
        PANEL9.setBackground(col2);
        PANEL11.setBackground(col2);
        PANEL10.setBackground(col2);
        //PANEL5 is the center panel. We mant a gridLayout in it
        PANEL1.setLayout(new BorderLayout(2, 2));
        PANEL10.setLayout(new BorderLayout(2, 2));
        //PANEL11.setLayout(new GridLayout(this.properties.size(), 2));
        PANEL5.setLayout(new GridLayout(1, 2, 2, 2));
        PANEL6.setLayout(new GridLayout(2, 1, 2, 2));
        PANEL7.setLayout(new GridLayout(16, 1, 2, 2));
        PANEL8.setLayout(new GridLayout(8, 1, 2, 2));
        PANEL9.setLayout(new GridLayout(8, 2, 2, 2));
        PANEL3.setLayout(new GridLayout(7, 1, 2, 2));
        //creating labels
        location = new JLabel("Location");
        typeofproperty = new JLabel("property type");
        pricerange = new JLabel("max price");
        proximity = new JLabel("transportation proximity");
        locateorbuy = new JLabel("want to");
        nbrooms = new JLabel("rooms");
        nbbthrooms = new JLabel("bathrooms");
        renovate = new JLabel("renovation");
        stateofproperty = new JLabel("state of property");
        minareacursor = new JLabel("min m²");
        maxareacursor = new JLabel("max m²");
        area = new JLabel("area");
        garden = new JLabel("garden");
        gardenarea = new JLabel();
        pricerangecursor = new JLabel();
        //creating combo boxes
        locatebuybox = new JComboBox(rentorbuychoice);
        locate = new JComboBox(typesoflocations);
        typechosen = new JComboBox(typesofproperties);
        proximitychosen = new JComboBox(typesofproximities);
        nbminroomschosen = new JComboBox(minrooms);
        nbmaxroomschosen = new JComboBox(maxrooms);
        nbminbthroomschosen = new JComboBox(minrooms);
        nbmaxbthroomschosen = new JComboBox(maxrooms);
        renovation = new JComboBox(typesrenovation);
        state = new JComboBox(propertystate);
        gardenchoice = new JComboBox(gardentype);
        locatebuybox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        locate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        typechosen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        proximitychosen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        nbminroomschosen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        nbmaxroomschosen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        nbminbthroomschosen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        nbmaxbthroomschosen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        renovation.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        state.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gardenchoice.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //creating buttons
        logout = new JButton("log out");
        search = new JButton("SEARCH");
        myprofile = new JButton("My profile");
        back = new JButton("back");
        latestadds = new JButton("latest additions");
        viewall = new JButton("view all");
        //creating sliders
        price = new JSlider(5, 80, 20);
        minarea = new JSlider(9, 500, 10);
        maxarea = new JSlider(20, 1000, 800);
        gardenareachosen = new JSlider(0, 500, 60);
        //setting buttons to not focudable
        search.setFocusable(false);
        logout.setFocusable(false);
        myprofile.setFocusable(false);
        back.setFocusable(false);
        latestadds.setFocusable(false);
        back.setVisible(false);
        viewall.setFocusable(false);
        //creating the button group
        group = new ButtonGroup();
        //initializing sliders
        price.setPaintTicks(true);
        price.setPaintTrack(true);
        price.setPaintLabels(true);
        //setting initial values to sliders
        pricerangecursor.setText("   " + Integer.toString(price.getValue() * 10000) + "$");
        maxareacursor.setText("   max area : " + Integer.toString(maxarea.getValue()) + "m²");
        minareacursor.setText("   min area : " + Integer.toString(minarea.getValue()) + "m²");
        gardenarea.setText("   garden area : " + Integer.toString(gardenareachosen.getValue()) + "m²");
        //setting all fonts
        pricerangecursor.setFont(font);
        maxareacursor.setFont(font);
        minareacursor.setFont(font);
        locate.setFont(font);
        location.setFont(font);
        typeofproperty.setFont(font);
        pricerange.setFont(font);
        proximity.setFont(font);
        locatebuybox.setFont(font);
        locateorbuy.setFont(font);
        nbbthrooms.setFont(font);
        nbrooms.setFont(font);
        typechosen.setFont(font);
        proximitychosen.setFont(font);
        nbminroomschosen.setFont(font);
        nbmaxroomschosen.setFont(font);
        nbminbthroomschosen.setFont(font);
        nbmaxbthroomschosen.setFont(font);
        price.setFont(font);
        viewall.setFont(font);
        pricerangecursor.setFont(font);
        renovate.setFont(font);
        renovation.setFont(font);
        state.setFont(font);
        stateofproperty.setFont(font);
        search.setFont(font);
        area.setFont(font);
        garden.setFont(font);
        gardenchoice.setFont(font);
        gardenareachosen.setFont(font);
        gardenarea.setFont(font);
        back.setFont(font);
        logout.setFont(font);
        myprofile.setFont(font);
        //adding every PANELS
        this.add(PANEL1, BorderLayout.NORTH);
        this.add(PANEL2, BorderLayout.SOUTH);
        this.add(PANEL3, BorderLayout.WEST);
        this.add(PANEL4, BorderLayout.EAST);
        this.add(PANEL5, BorderLayout.CENTER);
        PANEL1.add(LOGO, BorderLayout.WEST);
        PANEL1.add(PANEL10, BorderLayout.EAST);
        PANEL10.add(myprofile, BorderLayout.CENTER);
        PANEL10.add(logout, BorderLayout.NORTH);
        //adding PANEL6 and PANEL7 to PANEL5
        PANEL5.add(PANEL6);
        // SCROLLER2 = new JScrollPane(PANEL7);
        //SCROLLER2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //SCROLLER2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        PANEL5.add(PANEL7);
        //adding PANELS 8 and 9 to PANEL6
        //add everything to the PANELS
        PANEL6.add(PANEL8);
        PANEL6.add(PANEL9);

        PANEL8.add(location);
        PANEL8.add(locate);
        PANEL8.add(typeofproperty);
        PANEL8.add(typechosen);
        PANEL8.add(renovate);
        PANEL8.add(renovation);
        PANEL8.add(locateorbuy);
        PANEL8.add(locatebuybox);
        PANEL9.add(nbrooms);
        PANEL9.add(new JLabel());
        PANEL9.add(nbminroomschosen);
        PANEL9.add(nbmaxroomschosen);
        PANEL9.add(nbbthrooms);
        PANEL9.add(new JLabel());
        PANEL9.add(nbminbthroomschosen);
        PANEL9.add(nbmaxbthroomschosen);
        PANEL9.add(pricerange);
        PANEL9.add(new JLabel());
        PANEL9.add(price);
        PANEL9.add(pricerangecursor);
        PANEL7.add(proximity);
        PANEL7.add(proximitychosen);
        PANEL7.add(area);
        PANEL7.add(minarea);
        PANEL7.add(minareacursor);
        PANEL7.add(maxarea);
        PANEL7.add(maxareacursor);
        PANEL7.add(garden);
        PANEL7.add(gardenchoice);
        PANEL7.add(gardenareachosen);
        PANEL7.add(gardenarea);
        PANEL7.add(new JLabel());
        PANEL7.add(new JLabel());
        PANEL7.add(new JLabel());
        PANEL7.add(new JLabel());
        PANEL7.add(search);
        PANEL3.add(latestadds, BorderLayout.CENTER);
        PANEL3.add(viewall, BorderLayout.SOUTH);
        PANEL3.add(back);
        latestadds.setVisible(false);
        PANEL3.add(latestadds);

    }

    public void showResults(ActionListener al) throws IOException {
        //PANEL11.setLayout(new GridLayout(properties.size(), 2));
        PANEL5.remove(PANEL6);
        PANEL5.remove(PANEL7);
        PANEL11.setLayout(new GridLayout(this.properties.size(), 2));
        PANEL11.setPreferredSize(new Dimension(200, 800 * (this.properties.size())));
        showAllPropertiesImage(PANEL11, al);
        SCROLLER = new JScrollPane(PANEL11);
        SCROLLER.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        SCROLLER.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        PANEL5.add(SCROLLER);
        back.setVisible(true);
        repaint();
    }

    public void backToSearchMenu() {
        cleanProperties();
        PANEL5.remove(PANEL11);
        PANEL5.remove(SCROLLER);
        PANEL5.add(PANEL6);
        PANEL5.add(PANEL7);
        back.setVisible(false);
        repaint();
    }

    public void showAllPropertiesImage(JPanel panel, ActionListener al) throws IOException {
        for (Estate propertie : properties) {
            propertie.addInfosOnPanel(PANEL11, false);
            // propertie.booking.setVisible(true);
            //propertie.booking.addActionListener(al);
        }
    }

    public void cleanProperties() {

        for (Estate propertie : properties) {
            propertie.removeInfos(PANEL11);

        }
        PANEL5.removeAll();
        properties.removeAll(properties);

    }

    public void BuyerResearch(ChangeEvent ce) {
        pricerangecursor.setText(Integer.toString(price.getValue() * 10000) + "$");
        if (price.getValue() > 79) {
            pricerangecursor.setText("   " + Integer.toString(price.getValue() * 10000) + "+$");
        } else {
            pricerangecursor.setText("   " + Integer.toString(price.getValue() * 10000) + "$");
        }
        if (minarea.getValue() > 499) {
            minareacursor.setText("   min area : " + Integer.toString(minarea.getValue()) + "+m²");
        } else {
            minareacursor.setText("   min area : " + Integer.toString(minarea.getValue()) + "m²");
        }
        if (maxarea.getValue() > 999) {
            maxareacursor.setText("   max area : " + Integer.toString(maxarea.getValue()) + "+m²");
        } else {
            maxareacursor.setText("   max area : " + Integer.toString(maxarea.getValue()) + "m²");
        }
        if (gardenareachosen.getValue() > 499) {
            gardenarea.setText("   garden area : " + Integer.toString(gardenareachosen.getValue()) + "+m²");
        } else {
            gardenarea.setText("   garden area : " + Integer.toString(gardenareachosen.getValue()) + "m²");
        }
        if (gardenchoice.getSelectedItem().equals("No garden")) {
            gardenareachosen.setValue(0);
            gardenareachosen.setEnabled(false);
        } else {
            gardenareachosen.setEnabled(true);
        }

    }

    public void bookFunction(ActionEvent ae, User buyer, ActionListener al) throws SQLException, Exception {
        java.sql.Date date;
        for (Estate propertie : properties) {
            if (ae.getSource() == propertie.booking) {
                propertie.showBookingFrame(returnBookingDates(), ae);
            }
            if (ae.getSource() == propertie.book) {
                System.out.println("ouais");
                date = (java.sql.Date) propertie.datebox.getSelectedItem();
                System.out.println("date :" + date.toString());
                bookingdao.addBooking(propertie, buyer, date);
                propertie.closeBookingFrame();
                String object = "HouseClip-Booking confirmation";
                String content = "We confirm that a new viewing has been added to your bookings. \n Address : " + propertie.getAdress() + "\nDate : " + date.toString() + "\nThis email is automatically generated. Please do not answer back.";
                new SendEmail2(buyer.getEmail(), object, content);
                JOptionPane.showMessageDialog(null, "A confirmation email has been sent to your address : \n" + buyer.getEmail() + "\nThis email is automatic. Please do not answer it.", "Email sent", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }
    }

    public java.sql.Date[] returnBookingDates() {
        java.sql.Date utilDate = new java.sql.Date(1, 1, 1);
        java.sql.Date FinalDates[] = new java.sql.Date[30];
        java.util.Date dates[] = new java.util.Date[30];
        for (int i = 0; i < 30; i++) {
            utilDate.setDate(utilDate.getDate() + 1);
            dates[i] = utilDate;
            FinalDates[i] = new java.sql.Date(dates[i].getYear() + 120, dates[i].getMonth(), dates[i].getDate());
        }

        return FinalDates;
    }

    public void showResearchResults(ActionEvent ae, ActionListener al) throws Exception {
        String thislocate;
        String thistype;
        String thisrenovation;
        String thisrent;
        String thisproximity;
        int thisminroom = 0;
        int thismaxroom = 20;
        int thisminbthroom = 0;
        int thismaxbthroom = 20;
        int thisprice = 1000000000;
        int thisminarea = 0;
        int thismaxarea = 1000000;
        String thisgarden;
        thisminarea = minarea.getValue();
        int thisgardenarea = 1000000;
        if (maxarea.getValue() >= 900) {
            thismaxarea = 1000000;
        } else {
            thismaxarea = (int) maxarea.getValue();
        }
        if (gardenareachosen.getValue() >= 499) {
            thisgardenarea = 1000000;
        } else {
            thisgardenarea = gardenareachosen.getValue();
        }
        if (price.getValue() >= 79) {
            thisprice = 1000000000;
        } else {
            thisprice = price.getValue();
        }

        if (locate.getSelectedItem().equals("All locations")) {
            thislocate = "IS NOT NULL";
        } else {
            thislocate = "='" + (String) locate.getSelectedItem() + "'";
        }
        if (renovation.getSelectedItem().equals("All")) {
            thisrenovation = "IS NOT NULL";
        } else {
            thisrenovation = "='" + (String) renovation.getSelectedItem() + "'";
        }
        if (typechosen.getSelectedItem().equals("All properties")) {
            thistype = "IS NOT NULL";
        } else {
            thistype = "='" + (String) typechosen.getSelectedItem() + "'";
        }
        if (proximitychosen.getSelectedItem().equals("All")) {
            thisproximity = "IS NOT NULL";
        } else {
            thisproximity = "='" + (String) proximitychosen.getSelectedItem() + "'";
        }
        if (gardenchoice.getSelectedItem().equals("No garden")) {
            thisgarden = "='no'";
            thisgardenarea = 0;
        } else if (gardenchoice.getSelectedItem().equals("Garden")) {
            thisgarden = "='yes'";
            if (gardenareachosen.getValue() >= 490) {
                thisgardenarea = 1000000;
            } else {
                thisgardenarea = gardenareachosen.getValue();
            }
        } else {
            thisgarden = "IS NOT NULL";
        }
        if (locatebuybox.getSelectedItem().equals("Buy only")) {
            thisrent = "='no'";
        } else if (locatebuybox.getSelectedItem().equals("Rent only")) {
            thisrent = "='yes'";
        } else {
            thisrent = "IS NOT NULL";
        }
        if (!nbminroomschosen.getSelectedItem().equals("No min")) {
            thisminroom = Integer.parseInt((String) nbminroomschosen.getSelectedItem());
        }
        if (!nbmaxroomschosen.getSelectedItem().equals("No max") && !nbmaxroomschosen.getSelectedItem().equals("6+")) {
            thismaxroom = Integer.parseInt((String) nbmaxroomschosen.getSelectedItem());
        }
        if (!nbminbthroomschosen.getSelectedItem().equals("No min")) {
            thisminbthroom = Integer.parseInt((String) nbminbthroomschosen.getSelectedItem());
        }
        if (!nbmaxbthroomschosen.getSelectedItem().equals("No max") && !nbmaxbthroomschosen.getSelectedItem().equals("6+")) {
            thismaxroom = Integer.parseInt((String) nbmaxbthroomschosen.getSelectedItem());
        }
        properties = estatedao.searchEstate(thislocate,
                thistype,
                thisrenovation,
                thisrent,
                thisproximity,
                thisminroom,
                thismaxroom,
                thisminbthroom,
                thismaxbthroom,
                thisprice,
                thisminarea,
                thismaxarea,
                thisgarden,
                thisgardenarea);
        for (Estate propertie : properties) {
            System.out.println("id " + propertie.getID());
            propertie.booking.setVisible(true);
            propertie.book.addActionListener(al);
            propertie.booking.addActionListener(al);

        }
        System.out.println("garden area : " + thisgardenarea);
        System.out.println("min area : " + thisminarea);
        System.out.println("max area : " + thismaxarea);

    }

    public ArrayList<Estate> getEstates() {
        return this.properties;
    }

    public void showAllResults(ActionListener al) throws Exception {
        properties = estatedao.getAllEstates();
        for (Estate propertie : properties) {
            System.out.println("id " + propertie.getID());
            propertie.booking.setVisible(true);
            propertie.book.addActionListener(al);
            propertie.booking.addActionListener(al);

        }
    }

}
