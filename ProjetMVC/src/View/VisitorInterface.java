/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.BuyerConstInterface;
import Model.EstateDAO;
import Model.Estate;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
interface Interface. Mostly buttons and panels
 */
public class VisitorInterface extends JPanel implements BuyerConstInterface {

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
    private ArrayList<Estate> properties;
    private final EstateDAO estatedao;
    public JButton logout;
    public JButton myprofile;

    public VisitorInterface(ArrayList<Estate> properties, EstateDAO estatedao) throws Exception {

        final ImageIcon icon = new ImageIcon("angleLogo.png");
        JLabel LOGO = new JLabel(icon);
        this.properties = properties;
        this.estatedao = estatedao;
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

        logout = new JButton("log out");
        myprofile = new JButton("My profile");
        logout.setFont(font);
        myprofile.setFont(font);
        logout.setFocusable(false);
        myprofile.setFocusable(false);
        this.add(PANEL1, BorderLayout.NORTH);
        this.add(PANEL2, BorderLayout.SOUTH);
        this.add(PANEL3, BorderLayout.WEST);
        this.add(PANEL4, BorderLayout.EAST);
        this.add(PANEL5, BorderLayout.CENTER);
        PANEL1.add(LOGO, BorderLayout.WEST);
        PANEL1.add(PANEL10, BorderLayout.EAST);
        PANEL10.add(myprofile, BorderLayout.CENTER);
        PANEL10.add(logout, BorderLayout.NORTH);
        PANEL10.setBackground(col);
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

    }

    public void showResults(ActionListener al) throws IOException, Exception {
        cleanProperties();
        showAllResults(al);
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
        repaint();
    }

    public void showAllPropertiesImage(JPanel panel, ActionListener al) throws IOException {
        for (Estate propertie : properties) {
            propertie.addInfosOnPanel(PANEL11, false);
            propertie.booking.setVisible(false);
            propertie.makeanoffer.setVisible(false);
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
