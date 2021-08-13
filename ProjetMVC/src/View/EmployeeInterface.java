package View;

import Model.EstateDAO;
import Model.EmployeeDAO;
import Model.BuyerDAO;
import Model.Estate;
import Model.Employee;
import Model.Buyer;
import Model.Booking;
import Model.BookingDAO;
import Model.ChartDAO;
import Model.Seller;
import Model.SellerDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


/*
 interface of the employee. Mostly buttons and panels
 */
public class EmployeeInterface extends JPanel {

    public JPanel PANEL1;
    public JPanel PANEL2;
    public JPanel PANEL3;
    public JPanel PANEL4;
    public JPanel PANEL5;
    public JPanel PANEL6;
    public JPanel PANEL7;
    public JPanel ShowPANEL;
    public JPanel ShowPANEL2;
    public JPanel ShowPANEL3;
    public JButton logout;
    public JButton profile;
    public JButton viewprofiles;
    public JButton searchprofile;
    public JButton back;
    public JButton viewstatistics;
    public JButton viewallbookings;
    public JButton viewproperties;
    public JComboBox menuderoulant;
    public EstateDAO estatedao;
    public EmployeeDAO employeedao;
    public BuyerDAO buyerdao;
    public SellerDAO sellerdao;
    public BookingDAO bookingdao;
    public ChartDAO chartdao;
    public JPanel panelsellers;
    public JPanel panelbuyers;
    public JPanel panelemployees;
    public JScrollPane SCROLLER;
    public JScrollPane SCROLLER2;
    public JScrollPane SCROLLER3;
    public JScrollPane SCROLLER4;
    private MyProfile[] buyersProfiles;
    private MyProfile[] sellersProfiles;
    private MyProfile[] employeesProfiles;
    private JPanel[] bufferBuyersProfiles;
    private JPanel[] bufferSellersProfiles;
    private JPanel[] bufferEmployeesProfiles;
    private boolean allprop = false;
    private boolean allbooks = false;
    private ArrayList<Estate> properties;
    private ArrayList<Booking> bookings;
    private ArrayList<Buyer> buyerslist;
    private ArrayList<Seller> sellerslist;
    private ArrayList<Employee> employeeslist;
    private Estate actualestate;
    private final JLabel logoBuyers;
    private final JLabel LOGO;
    private final JLabel logoSellers;
    private final JLabel logoEmployees;
    private final Font font;
    private final Font font2;

    public EmployeeInterface(SellerDAO sellerdao, BuyerDAO buyerdao, EmployeeDAO employeedao, BookingDAO bookingdao, EstateDAO estatedao, ChartDAO chartdao) throws Exception {

        final ImageIcon icon1 = new ImageIcon("logoBuyers.png");
        final ImageIcon icon2 = new ImageIcon("logoSellers.png");
        final ImageIcon icon3 = new ImageIcon("logoEmployees.png");
        final ImageIcon icon4 = new ImageIcon("angleLogo.png");
        font = new Font("Times New Roman", Font.PLAIN, 22);
        font2 = new Font("Times New Roman", Font.PLAIN, 17);
        logoBuyers = new JLabel(icon1);
        logoSellers = new JLabel(icon2);
        logoEmployees = new JLabel(icon3);
        LOGO = new JLabel(icon4);
        buyerslist = new ArrayList();
        sellerslist = new ArrayList();
        employeeslist = new ArrayList();
        properties = new ArrayList();
        bookings = new ArrayList();
        this.estatedao = estatedao;
        this.bookingdao = bookingdao;
        this.employeedao = employeedao;
        this.buyerdao = buyerdao;
        this.sellerdao = sellerdao;
        this.chartdao = chartdao;
        this.setBackground(Color.white);
        this.setVisible(true);
        this.setLayout(new BorderLayout(8, 8));
        PANEL1 = new JPanel();
        PANEL2 = new JPanel();
        PANEL3 = new JPanel();
        PANEL4 = new JPanel();
        PANEL5 = new JPanel();
        PANEL6 = new JPanel();
        PANEL7 = new JPanel();
        ShowPANEL = new JPanel();
        ShowPANEL2 = new JPanel();
        ShowPANEL3 = new JPanel();
        profile = new JButton("my profile");
        logout = new JButton("log out");
        searchprofile = new JButton("search user");
        viewprofiles = new JButton("view all profiles");
        back = new JButton("back");
        viewproperties = new JButton("view properties");
        viewallbookings = new JButton("view all bookings");
        viewstatistics = new JButton("view statistics");
        profile.setFocusable(false);
        logout.setFocusable(false);
        viewprofiles.setFocusable(false);
        back.setFocusable(false);
        viewallbookings.setFocusable(false);
        viewproperties.setFocusable(false);
        searchprofile.setFocusable(false);
        viewstatistics.setFocusable(false);
        profile.setFont(font);
        logout.setFont(font);
        viewprofiles.setFont(font);
        back.setFont(font);
        viewallbookings.setFont(font);
        viewproperties.setFont(font);
        searchprofile.setFont(font);
        viewstatistics.setFont(font);
        PANEL1.setLayout(new BorderLayout());
        PANEL6.setLayout(new BorderLayout());
        PANEL5.setLayout(new GridLayout(6, 1, 25, 25));
        Color col = Color.white;
        Color col2 = Color.white;
        PANEL1.setBackground(col);
        PANEL2.setBackground(col);
        PANEL3.setBackground(col);
        PANEL4.setBackground(col);
        PANEL5.setBackground(col2);
        PANEL6.setBackground(col2);
        PANEL7.setBackground(col);
        PANEL6.setBackground(col);
        PANEL1.setPreferredSize(new Dimension(100, 150));
        PANEL2.setPreferredSize(new Dimension(100, 100));
        PANEL3.setPreferredSize(new Dimension(200, 100));
        PANEL4.setPreferredSize(new Dimension(200, 100));
        PANEL5.setPreferredSize(new Dimension(100, 100));
        PANEL6.setPreferredSize(new Dimension(250, 100));
        PANEL7.setPreferredSize(new Dimension(200, 1200 * (this.properties.size() + 1)));

        this.add(PANEL1, BorderLayout.NORTH);
        this.add(PANEL2, BorderLayout.SOUTH);
        this.add(PANEL3, BorderLayout.WEST);
        this.add(PANEL4, BorderLayout.EAST);
        this.add(PANEL5, BorderLayout.CENTER);
        PANEL1.add(LOGO, BorderLayout.WEST);
        PANEL1.add(PANEL6, BorderLayout.EAST);
        PANEL6.add(profile, BorderLayout.CENTER);
        PANEL6.add(logout, BorderLayout.NORTH);
        PANEL5.add(new JLabel());
        PANEL5.add(viewprofiles);
        PANEL5.add(viewproperties);
        PANEL5.add(viewallbookings);
        PANEL5.add(viewstatistics);
        PANEL3.add(back);
        back.setVisible(false);
    }

    public void backToMainInterface() {
        back.setVisible(false);
        PANEL5.removeAll();
        PANEL7.removeAll();
        ShowPANEL.removeAll();
        PANEL5.setLayout(new GridLayout(6, 1, 25, 25));
        PANEL5.add(new JLabel());
        PANEL5.add(viewprofiles);
        PANEL5.add(viewproperties);
        PANEL5.add(viewallbookings);
        PANEL5.add(viewstatistics);
        viewprofiles.setVisible(true);
        viewproperties.setVisible(true);
        viewallbookings.setVisible(true);
        viewstatistics.setVisible(true);
        PANEL1.add(LOGO, BorderLayout.WEST);
        //back.setVisible(true);
    }

    public void EmployeeInteface(ActionEvent ae, ActionListener al) throws SQLException, Exception {
        if (ae.getSource() == viewprofiles) {
            ShowPANEL.removeAll();
            PANEL5.removeAll();
            PANEL7.removeAll();
            PANEL5.setLayout(new BorderLayout());
            viewprofiles.setVisible(false);
            viewproperties.setVisible(false);
            back.setVisible(true);
            try {
                showAllProfilesOnPanel(al);
            } catch (Exception ex) {
                Logger.getLogger(EmployeeInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ((ae.getSource() == back) && !allprop) {
            PANEL7.removeAll();
            PANEL5.removeAll();
            backToMainInterface();
            viewprofiles.setVisible(true);
            viewproperties.setVisible(true);
            back.setVisible(false);
            repaint();
            invalidate();
            validate();
        }
        if (ae.getSource() == viewallbookings) {
            getAllBookings();
            if (!bookings.isEmpty()) {
                PANEL5.removeAll();
                PANEL7.removeAll();
                ShowPANEL.removeAll();
                PANEL5.setLayout(new BorderLayout());
                showAllBookings(al);
                allbooks = true;
                repaint();
                invalidate();
                validate();
            } else {
                JOptionPane.showMessageDialog(null, "No booking to show yet.", "no booking", JOptionPane.INFORMATION_MESSAGE);
            }

        } else if ((ae.getSource() == back) && allbooks) {
            {
                cleanBookings();
                PANEL7.removeAll();
                PANEL5.removeAll();
                backToMainInterface();
                viewprofiles.setVisible(true);
                viewproperties.setVisible(true);
                back.setVisible(false);
                repaint();
                invalidate();
                validate();
            }
        }

        if (ae.getSource() == viewproperties) {
            PANEL5.removeAll();
            PANEL7.removeAll();
            ShowPANEL.removeAll();
            PANEL5.setLayout(new BorderLayout());
            viewprofiles.setVisible(false);
            viewproperties.setVisible(false);
            allprop = true;
            cleanProperties();
            try {
                getAllEstates();
                showResults(al);
                repaint();
                invalidate();
                validate();
            } catch (Exception ex) {
                Logger.getLogger(MyInterface.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (ae.getSource() == viewstatistics) {
            PANEL7.removeAll();
            PANEL5.removeAll();
            ShowPANEL.removeAll();
            PANEL5.setLayout(new BorderLayout());
            showStatistices();
            back.setVisible(true);
            repaint();
            invalidate();
            validate();

        } else if ((ae.getSource() == back) && allprop) {
            cleanProperties();
            PANEL7.removeAll();
            PANEL5.removeAll();
            ShowPANEL.removeAll();
            backToMainInterface();
            viewprofiles.setVisible(true);
            viewproperties.setVisible(true);
            back.setVisible(false);
            //backToSearchMenu();
            allprop = false;
            repaint();
            invalidate();
            validate();
        }
        if (!bookings.isEmpty()) {
            for (int i = 0; i < bookings.size(); i++) {
                if (ae.getSource() == bookings.get(i).delete) {
                    bookingdao.removeBooking(bookings.get(i));
                    cleanBookings();
                    PANEL7.removeAll();
                    PANEL5.removeAll();
                    backToMainInterface();
                    viewprofiles.setVisible(true);
                    viewproperties.setVisible(true);
                    back.setVisible(false);
                    repaint();
                    invalidate();
                    validate();
                    break;
                }
            }
        }
        if (!buyerslist.isEmpty()) {
            for (int i = 0; i < buyerslist.size(); i++) {
                if (ae.getSource() == buyersProfiles[i].modify) {
                    buyersProfiles[i].modif = true;
                    buyersProfiles[i].removeInfosOfProfile(buyerslist.get(i));
                    buyersProfiles[i].modifyProfile(buyerslist.get(i));
                    invalidate();
                    validate();
                } else if (ae.getSource() == buyersProfiles[i].save) {
                    buyersProfiles[i].modif = true;
                    buyersProfiles[i].BuyerModifiedProfile();
                    bufferBuyersProfiles[i].removeAll();
                    buyerslist.get(i).updateInfosMyProfile();
                    buyersProfiles[i].removeInfosOfProfile(buyerslist.get(i));
                    buyersProfiles[i].ShowInfosOfProfile(buyerslist.get(i));
                    buyersProfiles[i].logout.setVisible(false);
                    buyersProfiles[i].seebookings.setVisible(false);
                    buyersProfiles[i].backbutton.setVisible(false);
                    buyersProfiles[i].changepassword.setVisible(false);
                    buyersProfiles[i].delete.setText("delete account");
                    buyersProfiles[i].delete.addActionListener(al);
                    buyersProfiles[i].modify.addActionListener(al);
                    buyersProfiles[i].save.addActionListener(al);
                    buyerslist.get(i).PANELMODIFY.day.addActionListener(al);
                    buyerslist.get(i).PANELMODIFY.month.addActionListener(al);
                    buyerslist.get(i).PANELMODIFY.year.addActionListener(al);
                    bufferBuyersProfiles[i].add(buyersProfiles[i]);
                    repaint();
                    invalidate();
                    validate();
                } else if (ae.getSource() == buyersProfiles[i].delete) {
                    buyerdao.removeBuyer(buyerslist.get(i));
                    buyerslist.remove(i);
                    bufferBuyersProfiles[i].removeAll();
                    PANEL7.removeAll();
                    PANEL5.removeAll();
                    backToMainInterface();
                    repaint();
                    invalidate();
                    validate();
                    break;
                }
            }
        }
        if (!sellerslist.isEmpty()) {
            for (int i = 0; i < sellerslist.size(); i++) {
                if (ae.getSource() == sellersProfiles[i].modify) {
                    sellersProfiles[i].modif = true;
                    sellersProfiles[i].removeInfosOfProfile(sellerslist.get(i));
                    sellersProfiles[i].modifyProfile(sellerslist.get(i));
                    invalidate();
                    validate();
                } else if (ae.getSource() == sellersProfiles[i].save) {
                    sellersProfiles[i].modif = true;
                    sellersProfiles[i].SellerModifiedProfile();
                    bufferSellersProfiles[i].removeAll();
                    sellerslist.get(i).updateInfosMyProfile();
                    sellersProfiles[i].removeInfosOfProfile(sellerslist.get(i));
                    sellersProfiles[i].ShowInfosOfProfile(sellerslist.get(i));
                    sellersProfiles[i].logout.setVisible(false);
                    sellersProfiles[i].seebookings.setVisible(false);
                    sellersProfiles[i].backbutton.setVisible(false);
                    sellersProfiles[i].changepassword.setVisible(false);
                    sellersProfiles[i].delete.setText("delete account");
                    sellersProfiles[i].delete.addActionListener(al);
                    sellersProfiles[i].modify.addActionListener(al);
                    sellersProfiles[i].save.addActionListener(al);
                    sellerslist.get(i).PANELMODIFY.day.addActionListener(al);
                    sellerslist.get(i).PANELMODIFY.month.addActionListener(al);
                    sellerslist.get(i).PANELMODIFY.year.addActionListener(al);
                    bufferSellersProfiles[i].add(sellersProfiles[i]);
                    repaint();
                    invalidate();
                    validate();
                } else if (ae.getSource() == sellersProfiles[i].delete) {
                    sellerdao.removeSeller(sellerslist.get(i));
                    sellerslist.remove(i);
                    bufferSellersProfiles[i].removeAll();
                    PANEL7.removeAll();
                    PANEL5.removeAll();
                    backToMainInterface();
                    repaint();
                    invalidate();
                    validate();
                    break;
                }
            }
        }
        if (!employeeslist.isEmpty()) {
            for (int i = 0; i < employeeslist.size(); i++) {
                if (ae.getSource() == employeesProfiles[i].modify) {
                    employeesProfiles[i].modif = true;
                    employeesProfiles[i].removeInfosOfProfile(employeeslist.get(i));
                    employeesProfiles[i].modifyProfile(employeeslist.get(i));
                    invalidate();
                    validate();
                } else if (ae.getSource() == employeesProfiles[i].save) {
                    employeesProfiles[i].modif = true;
                    employeesProfiles[i].EmployeeModifiedProfile();
                    bufferEmployeesProfiles[i].removeAll();
                    employeeslist.get(i).updateInfosMyProfile();
                    employeesProfiles[i].removeInfosOfProfile(employeeslist.get(i));
                    employeesProfiles[i].ShowInfosOfProfile(employeeslist.get(i));
                    employeesProfiles[i].logout.setVisible(false);
                    employeesProfiles[i].seebookings.setVisible(false);
                    employeesProfiles[i].backbutton.setVisible(false);
                    employeesProfiles[i].changepassword.setVisible(false);
                    employeesProfiles[i].delete.setText("delete account");
                    employeesProfiles[i].delete.addActionListener(al);
                    employeesProfiles[i].modify.addActionListener(al);
                    employeesProfiles[i].save.addActionListener(al);
                    employeeslist.get(i).PANELMODIFY.month.addActionListener(al);
                    employeeslist.get(i).PANELMODIFY.year.addActionListener(al);
                    bufferEmployeesProfiles[i].add(employeesProfiles[i]);
                    repaint();
                    invalidate();
                    validate();
                } else if (ae.getSource() == employeesProfiles[i].delete) {
                    employeedao.removeEmployee(employeeslist.get(i));
                    employeeslist.remove(i);
                    bufferEmployeesProfiles[i].removeAll();
                    PANEL7.removeAll();
                    PANEL5.removeAll();
                    backToMainInterface();
                    repaint();
                    invalidate();
                    validate();
                    break;
                }
            }
        }

        for (Estate propertie : properties) {
            if (ae.getSource() == propertie.modify) {
                this.actualestate = propertie;
                PANEL5.removeAll();
                EstateModify em = new EstateModify(propertie);
                PANEL5.setLayout(new GridLayout(1, 1));
                PANEL5.add(em);
                repaint();
                invalidate();
                validate();
            } else if (ae.getSource() == propertie.save) {
                this.actualestate = propertie;
                PANEL7.removeAll();
                modifyEstate(actualestate);
                PANEL5.remove(PANEL7);
                PANEL5.removeAll();
                repaint();
                invalidate();
                validate();
                back.setVisible(false);
                repaint();
                viewprofiles.setVisible(true);
                viewproperties.setVisible(true);
                back.setVisible(false);
                getAllEstates();
                showResults(al);
                repaint();
                invalidate();
                validate();
                break;
            } else if (ae.getSource() == propertie.delete) {
                this.actualestate = propertie;
                estatedao.removeEstate(actualestate);
                PANEL7.removeAll();
                PANEL5.remove(PANEL7);
                PANEL5.removeAll();
                repaint();
                invalidate();
                validate();
                back.setVisible(false);
                repaint();
                viewprofiles.setVisible(true);
                viewproperties.setVisible(true);
                back.setVisible(false);
                getAllEstates();
                this.actualestate = propertie;
                showResults(al);
                repaint();
                invalidate();
                validate();
                break;
            }

        }

    }

    public void modifyEstate(Estate estate) throws SQLException {
        String newlocation = (String) estate.newlocation.getSelectedItem();
        String newadress = estate.newadress.getText();
        String newdescription = estate.newdescription.getText();
        int newprice = Integer.parseInt(estate.newprice.getText());
        int newarea = Integer.parseInt(estate.newarea.getText());
        int newnbrooms = Integer.parseInt((String) estate.newnbrooms.getSelectedItem());
        int newnbthrooms = Integer.parseInt((String) estate.newnbbthrooms.getSelectedItem());
        this.actualestate.setLocation(newlocation);
        this.actualestate.setAdress(newadress);
        this.actualestate.setDescription(newdescription);
        this.actualestate.setPrice(newprice);
        this.actualestate.setArea(newarea);
        this.actualestate.setNbRooms(newnbrooms);
        this.actualestate.setNbBthRooms(newnbthrooms);
        estatedao.modifyEstate(this.actualestate);
    }

    public void showAllProfilesOnPanel(ActionListener ae) throws Exception {
        //ShowPANEL.removeAll();
        buyerslist = buyerdao.getAllBuyers();
        sellerslist = sellerdao.getAllSellers();
        employeeslist = employeedao.getAllEmployees();
        int nbbuyers = buyerslist.size();
        int nbsellers = sellerslist.size();
        int nbemployees = employeeslist.size();
        int total = nbbuyers + nbsellers + nbemployees;
        buyersProfiles = new MyProfile[nbbuyers];
        sellersProfiles = new MyProfile[nbsellers];
        employeesProfiles = new MyProfile[nbemployees];
        bufferBuyersProfiles = new JPanel[nbbuyers];
        bufferSellersProfiles = new JPanel[nbsellers];
        bufferEmployeesProfiles = new JPanel[nbemployees];
        ShowPANEL.setPreferredSize(new Dimension(100, 700 * total));
        ShowPANEL.setLayout(new GridLayout(total + 3, 1));
        ShowPANEL.add(logoBuyers);
        ShowPANEL.setBackground(Color.WHITE);
        for (int i = 0; i < buyerslist.size(); i++) {
            buyersProfiles[i] = new MyProfile(buyerslist.get(i), sellerdao, buyerdao, employeedao, bookingdao);
            buyersProfiles[i].ShowInfosOfProfile(buyerslist.get(i));
            buyersProfiles[i].logout.setVisible(false);
            buyersProfiles[i].seebookings.setVisible(false);
            buyersProfiles[i].backbutton.setVisible(false);
            buyersProfiles[i].changepassword.setVisible(false);
            buyersProfiles[i].delete.setText("delete account");
            buyersProfiles[i].delete.addActionListener(ae);
            buyersProfiles[i].modify.addActionListener(ae);
            buyersProfiles[i].save.addActionListener(ae);
            buyerslist.get(i).PANELMODIFY.day.addActionListener(ae);
            buyerslist.get(i).PANELMODIFY.month.addActionListener(ae);
            buyerslist.get(i).PANELMODIFY.year.addActionListener(ae);
            buyerslist.get(i).PANELMODIFY.adress.setFont(font2);
            buyerslist.get(i).PANELMODIFY.mail.setFont(font2);
            buyerslist.get(i).PANELMODIFY.lastname.setFont(font2);
            buyerslist.get(i).PANELMODIFY.firstname.setFont(font2);
            buyerslist.get(i).PANELMODIFY.adress.setFont(font2);
            bufferBuyersProfiles[i] = new JPanel();
            buyersProfiles[i].remove(PANEL1);
            buyersProfiles[i].remove(PANEL2);
            buyersProfiles[i].remove(PANEL3);
            buyersProfiles[i].remove(PANEL4);
            buyersProfiles[i].setPreferredSize(new Dimension(1000, 2000));
            bufferBuyersProfiles[i].setLayout(new BorderLayout());
            bufferBuyersProfiles[i].setPreferredSize(new Dimension(1000, 1000));
            bufferBuyersProfiles[i].add(buyersProfiles[i], BorderLayout.CENTER);
            ShowPANEL.add(bufferBuyersProfiles[i]);
        }
        ShowPANEL.add(logoSellers);
        for (int i = 0; i < sellerslist.size(); i++) {
            sellersProfiles[i] = new MyProfile(sellerslist.get(i), sellerdao, buyerdao, employeedao, bookingdao);
            sellersProfiles[i].ShowInfosOfProfile(sellerslist.get(i));
            sellersProfiles[i].logout.setVisible(false);
            sellersProfiles[i].seebookings.setVisible(false);
            sellersProfiles[i].backbutton.setVisible(false);
            sellersProfiles[i].changepassword.setVisible(false);
            sellersProfiles[i].delete.setText("delete account");
            sellersProfiles[i].delete.addActionListener(ae);
            sellersProfiles[i].modify.addActionListener(ae);
            sellersProfiles[i].save.addActionListener(ae);
            sellerslist.get(i).PANELMODIFY.month.addActionListener(ae);
            sellerslist.get(i).PANELMODIFY.year.addActionListener(ae);
            sellerslist.get(i).PANELMODIFY.day.addActionListener(ae);
            sellerslist.get(i).PANELMODIFY.month.addActionListener(ae);
            sellerslist.get(i).PANELMODIFY.year.addActionListener(ae);
            sellerslist.get(i).PANELMODIFY.adress.setFont(font2);
            sellerslist.get(i).PANELMODIFY.mail.setFont(font2);
            sellerslist.get(i).PANELMODIFY.lastname.setFont(font2);
            sellerslist.get(i).PANELMODIFY.firstname.setFont(font2);
            sellerslist.get(i).PANELMODIFY.adress.setFont(font2);
            sellersProfiles[i].remove(PANEL1);
            sellersProfiles[i].remove(PANEL2);
            sellersProfiles[i].remove(PANEL3);
            sellersProfiles[i].remove(PANEL4);
            sellersProfiles[i].setPreferredSize(new Dimension(1000, 2000));
            bufferSellersProfiles[i] = new JPanel();
            bufferSellersProfiles[i].setLayout(new BorderLayout());
            bufferSellersProfiles[i].setPreferredSize(new Dimension(1000, 2000));
            bufferSellersProfiles[i].add(sellersProfiles[i]);
            ShowPANEL.add(bufferSellersProfiles[i]);
        }
        ShowPANEL.add(logoEmployees);
        for (int i = 0; i < employeeslist.size(); i++) {
            employeesProfiles[i] = new MyProfile(employeeslist.get(i), sellerdao, buyerdao, employeedao, bookingdao);
            employeesProfiles[i].ShowInfosOfProfile(employeeslist.get(i));
            employeesProfiles[i].logout.setVisible(false);
            employeesProfiles[i].backbutton.setVisible(false);
            employeesProfiles[i].seebookings.setVisible(false);
            employeesProfiles[i].changepassword.setVisible(false);
            employeesProfiles[i].delete.setText("delete account");
            employeesProfiles[i].delete.addActionListener(ae);
            employeesProfiles[i].modify.addActionListener(ae);
            employeesProfiles[i].save.addActionListener(ae);
            employeeslist.get(i).PANELMODIFY.month.addActionListener(ae);
            employeeslist.get(i).PANELMODIFY.year.addActionListener(ae);
            employeeslist.get(i).PANELMODIFY.day.addActionListener(ae);
            employeeslist.get(i).PANELMODIFY.month.addActionListener(ae);
            employeeslist.get(i).PANELMODIFY.year.addActionListener(ae);
            employeeslist.get(i).PANELMODIFY.adress.setFont(font2);
            employeeslist.get(i).PANELMODIFY.mail.setFont(font2);
            employeeslist.get(i).PANELMODIFY.lastname.setFont(font2);
            employeeslist.get(i).PANELMODIFY.firstname.setFont(font2);
            employeeslist.get(i).PANELMODIFY.adress.setFont(font2);
            employeesProfiles[i].remove(PANEL1);
            employeesProfiles[i].remove(PANEL2);
            employeesProfiles[i].remove(PANEL3);
            employeesProfiles[i].remove(PANEL4);
            employeesProfiles[i].setPreferredSize(new Dimension(1000, 2000));
            bufferEmployeesProfiles[i] = new JPanel();
            bufferEmployeesProfiles[i].setLayout(new BorderLayout());
            bufferEmployeesProfiles[i].setPreferredSize(new Dimension(1000, 1000));
            bufferEmployeesProfiles[i].add(employeesProfiles[i]);
            employeesProfiles[i].setPreferredSize(new Dimension(100, 300));
            ShowPANEL.add(bufferEmployeesProfiles[i]);
        }
        SCROLLER2 = new JScrollPane(ShowPANEL);
        SCROLLER2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        SCROLLER2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        PANEL5.add(SCROLLER2);

        repaint();
        invalidate();
        validate();

    }

    public void showAllBookings(ActionListener al) throws Exception {
        ShowPANEL2.setLayout(new GridLayout(this.bookings.size(), 1));
        for (Booking booking : bookings) {
            ShowPANEL2.add(booking.bookingPANEL);
            booking.delete.addActionListener(al);
        }
        SCROLLER3 = new JScrollPane(ShowPANEL2);
        SCROLLER3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        SCROLLER3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        PANEL5.add(SCROLLER3);
        back.setVisible(true);
        repaint();
        invalidate();
        validate();

    }

    void cleanProperties() {

        for (Estate propertie : properties) {
            propertie.removeInfos(ShowPANEL);
        }

        properties.removeAll(properties);

    }

    public void cleanBookings() {
        for (Booking booking : bookings) {
            ShowPANEL2.remove(booking.bookingPANEL);
        }

        properties.removeAll(properties);
        ShowPANEL2.removeAll();
    }

    void getAllEstates() throws Exception {
        cleanProperties();
        properties = estatedao.getAllEstates();
    }

    void getAllBookings() throws Exception {
        bookings = bookingdao.getAllBookings();
    }

    public void showResults(ActionListener al) throws IOException {
        PANEL5.removeAll();
        PANEL5.remove(ShowPANEL);
        PANEL7.setLayout(new GridLayout(this.properties.size(), 2));
        PANEL7.setPreferredSize(new Dimension(200, 800 * (this.properties.size())));
        showAllPropertiesImage(PANEL7, al);
        SCROLLER = new JScrollPane(PANEL7);
        SCROLLER.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        SCROLLER.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        PANEL5.add(SCROLLER);
        back.setVisible(true);
        repaint();
    }

    void showAllPropertiesImage(JPanel panel, ActionListener al) throws IOException {
        for (Estate propertie : properties) {
            propertie.setVisibleButton(true);
            propertie.delete.setVisible(true);
            propertie.booking.setVisible(false);
            propertie.makeanoffer.setVisible(false);
            propertie.addInfosOnPanel(PANEL7, false);
            propertie.modify.addActionListener(al);
            propertie.save.addActionListener(al);
            propertie.delete.addActionListener(al);

        }
    }

    public void backToSearchMenu() {
        cleanProperties();
        PANEL5.remove(PANEL7);
        PANEL5.remove(SCROLLER);
        PANEL5.add(ShowPANEL);
        back.setVisible(false);
        repaint();
    }

    private void showStatistices() throws SQLException {
        ShowPANEL3.removeAll();
        ShowPANEL3.setBackground(Color.white);
        ShowPANEL3.setLayout(new GridLayout(4, 1));
        ShowPANEL3.add(chartdao.getProximityChart());
        ShowPANEL3.add(chartdao.getLocationsChart());
        ShowPANEL3.add(chartdao.getTypesOfPropertiesChart());
        ShowPANEL3.add(chartdao.getRenovationChart());
        SCROLLER3 = new JScrollPane(ShowPANEL3);
        SCROLLER3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        SCROLLER3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        PANEL5.add(SCROLLER3);
        repaint();
    }
}
