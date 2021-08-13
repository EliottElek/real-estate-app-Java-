/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.BookingDAO;
import Model.Buyer;
import Model.BuyerDAO;
import Model.ChartDAO;
import Model.Employee;
import Model.EmployeeDAO;
import Model.Estate;
import Model.EstateDAO;
import Model.Seller;
import Model.SellerDAO;
import Model.User;
import View.EmployeeInterface;
import View.MyInterface;
import java.awt.Cursor;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author eliot
 */
public class Controller implements ActionListener, ChangeListener {

    SellerDAO sellerdao;
    BuyerDAO buyerdao;
    EmployeeDAO employeedao;
    EstateDAO estatedao;
    BookingDAO bookingdao;
    User actualuser;
    ChartDAO chartdao;
    MyInterface maininterface;
    boolean actionput = false;
    ArrayList<Estate> propertysample;
    EmployeeInterface employeeinterface;
    Vector<Seller> listOfSellers = new Vector();
    Vector<Buyer> listOfBuyers = new Vector();
    Vector<Employee> listOfEmployees = new Vector();

    public Controller() throws Exception {

        Font normalFont = new Font("Times New Roman", Font.PLAIN, 22);
        Font boldFont = normalFont.deriveFont(Font.BOLD);
        UIManager.put("OptionPane.font", normalFont);
        UIManager.put("OptionPane.messageFont", normalFont);
        UIManager.put("OptionPane.buttonFont", normalFont);
        UIManager.put("TextField.font", normalFont);
        UIManager.put("InternalFrame.titleFont", boldFont);
        sellerdao = new SellerDAO();
        buyerdao = new BuyerDAO();
        employeedao = new EmployeeDAO();
        estatedao = new EstateDAO();
        bookingdao = new BookingDAO();
        chartdao = new ChartDAO();
        maininterface = new MyInterface(1000, 800, sellerdao, buyerdao, employeedao, estatedao, bookingdao, chartdao);
        this.addActionListeners();

    }

    /*
     add actionListeners to every objects that need some.
     */
    private void addActionListeners() {
        //adding actionlisteners to all buttons from MyLogIn panel
        maininterface.login.newaccount.addActionListener(this);
        maininterface.login.enter.addActionListener(this);
        maininterface.login.enter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.login.userpassword.addActionListener(this);
        maininterface.login.usermail.addActionListener(this);
        maininterface.login.user.addActionListener(this);
        maininterface.login.user.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.login.visitor.addActionListener(this);
        maininterface.login.forgottenpass.addActionListener(this);
        maininterface.login.forgottenpass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.login.visitor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.login.newaccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //adding actionlisteners to all buttons from NewAccount panel
        maininterface.newaccount.createaccount.addActionListener(this);
        maininterface.newaccount.createaccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.newaccount.newfirstname.addActionListener(this);
        maininterface.newaccount.newlastname.addActionListener(this);
        maininterface.newaccount.newmail.addActionListener(this);
        maininterface.newaccount.newpassword.addActionListener(this);
        maininterface.newaccount.newadress.addActionListener(this);
        maininterface.newaccount.birthday.addActionListener(this);
        maininterface.newaccount.birthmonth.addActionListener(this);
        maininterface.newaccount.birthyear.addActionListener(this);
        maininterface.newaccount.back.addActionListener(this);
        maininterface.newaccount.user.addActionListener(this);
        maininterface.buyerinterface.search.addActionListener(this);
        maininterface.buyerinterface.search.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.newaccount.back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.newaccount.birthday.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.newaccount.birthyear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.newaccount.birthmonth.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //adding actionlisteners to all button from BuyerInterface panel
        maininterface.buyerinterface.price.addChangeListener(this);
        maininterface.buyerinterface.minarea.addChangeListener(this);
        maininterface.buyerinterface.maxarea.addChangeListener(this);
        maininterface.buyerinterface.gardenareachosen.addChangeListener(this);
        maininterface.buyerinterface.gardenchoice.addActionListener(this);
        maininterface.buyerinterface.back.addActionListener(this);
        maininterface.buyerinterface.logout.addActionListener(this);
        maininterface.buyerinterface.viewall.addActionListener(this);
        maininterface.buyerinterface.myprofile.addActionListener(this);

        maininterface.buyerinterface.price.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.buyerinterface.minarea.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.buyerinterface.maxarea.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.buyerinterface.gardenareachosen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.buyerinterface.gardenchoice.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.buyerinterface.back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.buyerinterface.logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.buyerinterface.viewall.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.buyerinterface.myprofile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        maininterface.employeeinterface.profile.addActionListener(this);
        maininterface.employeeinterface.logout.addActionListener(this);
        maininterface.employeeinterface.profile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.employeeinterface.logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.employeeinterface.viewprofiles.addActionListener(this);
        maininterface.employeeinterface.viewprofiles.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.employeeinterface.back.addActionListener(this);
        maininterface.employeeinterface.back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.employeeinterface.viewproperties.addActionListener(this);
        maininterface.employeeinterface.viewproperties.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.employeeinterface.viewallbookings.addActionListener(this);
        maininterface.employeeinterface.viewallbookings.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.employeeinterface.viewstatistics.addActionListener(this);
        maininterface.employeeinterface.viewstatistics.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //addibg actionlisteners to all button from SellerInterface panel
        maininterface.sellerinterface.add.addActionListener(this);
        maininterface.sellerinterface.add.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.sellerinterface.locate.addActionListener(this);
        maininterface.sellerinterface.proximitychosen.addActionListener(this);
        maininterface.sellerinterface.nbroomschosen.addActionListener(this);
        maininterface.sellerinterface.nbbthroomschosen.addActionListener(this);
        maininterface.sellerinterface.back.addActionListener(this);
        maininterface.sellerinterface.logout.addActionListener(this);
        maininterface.sellerinterface.addpicture1.addActionListener(this);
        maininterface.sellerinterface.addpicture2.addActionListener(this);
        maininterface.sellerinterface.addpicture3.addActionListener(this);
        maininterface.sellerinterface.gardenyes.addActionListener(this);
        maininterface.sellerinterface.gardenno.addActionListener(this);
        maininterface.sellerinterface.rent.addActionListener(this);
        maininterface.sellerinterface.sell.addActionListener(this);
        maininterface.sellerinterface.myprofile.addActionListener(this);

        maininterface.sellerinterface.add.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.sellerinterface.locate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.sellerinterface.proximitychosen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.sellerinterface.nbroomschosen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.sellerinterface.nbbthroomschosen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.sellerinterface.back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.sellerinterface.logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.sellerinterface.addpicture1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.sellerinterface.addpicture2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.sellerinterface.addpicture3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.sellerinterface.gardenyes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.sellerinterface.gardenno.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.sellerinterface.rent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.sellerinterface.sell.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.sellerinterface.myprofile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        maininterface.myprofile.backbutton.addActionListener(this);
        maininterface.myprofile.modify.addActionListener(this);
        maininterface.myprofile.save.addActionListener(this);
        maininterface.myprofile.delete.addActionListener(this);
        maininterface.myprofile.seebookings.addActionListener(this);
        maininterface.myprofile.changepassword.addActionListener(this);
        maininterface.myprofile.newpass1.addActionListener(this);
        maininterface.myprofile.newpass2.addActionListener(this);
        maininterface.visitorinterface.logout.addActionListener(this);
        maininterface.myprofile.change.addActionListener(this);
        maininterface.myprofile.cancel.addActionListener(this);

        maininterface.myprofile.backbutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.myprofile.modify.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.myprofile.save.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.myprofile.delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.myprofile.seebookings.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.myprofile.changepassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.visitorinterface.logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.myprofile.change.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        maininterface.myprofile.cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    }

    /*
     Main action performed method. It sometimes calles other methods that receive ActionEvents in parameters.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        maininterface.sellerinterface.SellerAddProperty(ae);
        try {
            maininterface.myprofile.deleteBooking(ae, maininterface.actualuser, maininterface);

        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            maininterface.employeeinterface.EmployeeInteface(ae, this);
        } catch (SQLException ex) {
            Logger.getLogger(MyInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MyInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            maininterface.login.login(ae);
        } catch (Exception ex) {
            Logger.getLogger(MyInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            maininterface.newaccount.createAccount(ae, listOfSellers, listOfBuyers, listOfEmployees);
        } catch (SQLException ex) {
            Logger.getLogger(MyInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            maininterface.buyerinterface.bookFunction(ae, maininterface.actualuser, this);
        } catch (SQLException ex) {
            Logger.getLogger(MyInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ae.getSource() == maininterface.login.visitor) {
            maininterface.visitorinterface.myprofile.setEnabled(false);
            maininterface.displayVisitorInterface();
            try {
                maininterface.visitorinterface.showResults(this);
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            maininterface.validAndInvalid();
        }
        if (ae.getSource() == maininterface.employeeinterface.logout) {
            maininterface.removeActualUser();
            maininterface.displayLogInInterface();
            maininterface.validAndInvalid();
        }
        if (ae.getSource() == maininterface.login.newaccount) {
            maininterface.displayNewAccountInterface();
            maininterface.validAndInvalid();
        } else if (ae.getSource() == maininterface.newaccount.back) {
            maininterface.displayLogInInterface();
            maininterface.validAndInvalid();
        } else if ((ae.getSource() == maininterface.login.enter) && (maininterface.login.logedin)) {
            maininterface.actualuser = maininterface.login.actualuser;
            if (maininterface.login.buyeruser) {
                System.out.println("buyer from controller : ");
                System.out.println("ACTUAL USER");
                maininterface.actualuser.showInfos();
                maininterface.buyerinterface.myprofile.setEnabled(true);
                maininterface.displayBuyerInterface();
                maininterface.validAndInvalid();
                JOptionPane.showMessageDialog(null, "You are connected as a buyer.\n You can now view all properties, search for a property using the different tools, and book for viewings.\n You can check your profile and log out at any moment.", "Buyer interface", JOptionPane.INFORMATION_MESSAGE);
                maininterface.validAndInvalid();
            } else if (maininterface.login.selleruser) {
                System.out.println("ACTUAL USER");
                maininterface.actualuser.showInfos();
                maininterface.sellerinterface.myprofile.setEnabled(true);
                maininterface.displaySellerInterface();
                maininterface.validAndInvalid();
                JOptionPane.showMessageDialog(null, "You are connected as a seller.\n You can now add a property : add it's different caracteristics and pictures.\n You can check your profile and log out at any moment.", "Seller interface", JOptionPane.INFORMATION_MESSAGE);
            } else if (maininterface.login.employeeuser) {
                System.out.println("ACTUAL USER");
                maininterface.actualuser.showInfos();
                maininterface.employeeinterface.profile.setEnabled(true);
                maininterface.displayEmployeeInterface();
                maininterface.validAndInvalid();
                JOptionPane.showMessageDialog(null, "You are connected as an employee.\n You can now : \n-view, modify and delete properties\n-view, modify and delete users profiles\n-view and delete users bookings\n-view statistics about the properties\nYou can check your profile and log out at any moment.", "Seller interface", JOptionPane.INFORMATION_MESSAGE);
                maininterface.validAndInvalid();
            }
            System.out.println("actual ID : " + maininterface.actualuser.getID());
            if (!maininterface.actionput) {
                maininterface.actionput = false;
            }
            maininterface.validAndInvalid();
        }
        {
            if (ae.getSource() == maininterface.buyerinterface.search) {
                maininterface.buyerinterface.cleanProperties();
                propertysample = maininterface.buyerinterface.getEstates();
                maininterface.buyerinterface.viewall.setVisible(false);
                try {
                    try {
                        maininterface.buyerinterface.showResearchResults(ae, this);
                    } catch (Exception ex) {
                        Logger.getLogger(MyInterface.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    maininterface.buyerinterface.showResults(this);
                    maininterface.validAndInvalid();
                } catch (IOException ex) {
                    Logger.getLogger(MyInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ae.getSource() == maininterface.myprofile.backbutton) {
                maininterface.myprofile.removeInfosOfProfile(maininterface.actualuser);
                if (maininterface.login.buyeruser) {
                    maininterface.displayBuyerInterface();
                } else if (maininterface.login.selleruser) {
                    maininterface.displaySellerInterface();
                } else if (maininterface.login.employeeuser) {
                    maininterface.displayEmployeeInterface();
                }
                maininterface.validAndInvalid();
            }
            if (ae.getSource() == maininterface.myprofile.seebookings) {
                try {
                    maininterface.myprofile.cleanBookings();
                    maininterface.myprofile.showMyBookings(maininterface.actualuser, this);
                } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                maininterface.validAndInvalid();

            }
            if (ae.getSource() == maininterface.myprofile.changepassword) {
                try {
                    maininterface.myprofile.showPasswordFrame();
                } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                maininterface.validAndInvalid();

            }
            try {
                maininterface.myprofile.passChange(ae, maininterface.login.buyeruser, maininterface.login.selleruser, maininterface.login.employeeuser);
            } catch (SQLException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            if ((ae.getSource() == maininterface.buyerinterface.myprofile)) {
                maininterface.myprofile.modif = false;
                maininterface.myprofile.ShowInfosOfProfile(maininterface.actualuser);
                maininterface.displayMyProfileInterface();
                maininterface.validAndInvalid();
            } else if ((ae.getSource() == maininterface.sellerinterface.myprofile) || (ae.getSource() == maininterface.employeeinterface.profile)) {
                maininterface.myprofile.modif = false;
                maininterface.myprofile.ShowInfosOfProfile(maininterface.actualuser);
                maininterface.displayMyProfileInterface();
                maininterface.myprofile.seebookings.setVisible(false);
                maininterface.validAndInvalid();
            }
            if (ae.getSource() == maininterface.myprofile.modify) {
                maininterface.myprofile.modif = true;
                maininterface.myprofile.removeInfosOfProfile(maininterface.actualuser);
                maininterface.myprofile.modifyProfile(maininterface.actualuser);
                maininterface.validAndInvalid();
            }
            if (ae.getSource() == maininterface.myprofile.save) {
                maininterface.myprofile.modif = true;
                if (maininterface.login.selleruser) {
                    try {
                        maininterface.myprofile.SellerModifiedProfile();
                        maininterface.actualuser = maininterface.myprofile.user;
                    } catch (SQLException ex) {
                        Logger.getLogger(MyInterface.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    maininterface.myprofile.removeInfosOfProfile(maininterface.actualuser);

                    if (maininterface.login.buyeruser) {
                        maininterface.displayBuyerInterface();
                    } else if (maininterface.login.selleruser) {
                        maininterface.displaySellerInterface();
                    } else if (maininterface.login.employeeuser) {
                        maininterface.displayEmployeeInterface();
                    }

                } else if (maininterface.login.buyeruser) {
                    try {
                        maininterface.myprofile.BuyerModifiedProfile();
                        maininterface.actualuser = maininterface.myprofile.user;
                    } catch (SQLException ex) {
                        Logger.getLogger(MyInterface.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    maininterface.myprofile.removeInfosOfProfile(maininterface.actualuser);

                    if (maininterface.login.buyeruser) {
                        maininterface.displayBuyerInterface();
                    } else if (maininterface.login.selleruser) {
                        maininterface.displaySellerInterface();
                    } else if (maininterface.login.employeeuser) {
                        maininterface.displayEmployeeInterface();
                    }

                } else if (maininterface.login.employeeuser) {
                    try {
                        maininterface.myprofile.EmployeeModifiedProfile();
                        maininterface.actualuser = maininterface.myprofile.user;
                    } catch (SQLException ex) {
                        Logger.getLogger(MyInterface.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    maininterface.myprofile.removeInfosOfProfile(maininterface.actualuser);

                    if (maininterface.login.buyeruser) {
                        maininterface.displayBuyerInterface();
                    } else if (maininterface.login.selleruser) {
                        maininterface.displaySellerInterface();
                    } else if (maininterface.login.employeeuser) {
                        maininterface.displayEmployeeInterface();
                    }

                }
                maininterface.validAndInvalid();
            } else if (ae.getSource() == maininterface.myprofile.delete) {
                if (maininterface.login.buyeruser) {
                    try {
                        buyerdao.removeBuyer(maininterface.actualuser);
                        maininterface.myprofile.removeInfosOfProfile(maininterface.actualuser);
                    } catch (SQLException ex) {
                        Logger.getLogger(MyInterface.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (maininterface.login.selleruser) {
                    try {
                        sellerdao.removeSeller(maininterface.actualuser);
                        maininterface.myprofile.removeInfosOfProfile(maininterface.actualuser);
                    } catch (SQLException ex) {
                        Logger.getLogger(MyInterface.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (maininterface.login.employeeuser) {
                    try {
                        employeedao.removeEmployee(maininterface.actualuser);
                        maininterface.myprofile.removeInfosOfProfile(maininterface.actualuser);
                    } catch (SQLException ex) {
                        Logger.getLogger(MyInterface.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                maininterface.login.logedin = false;
                maininterface.actualuser = null;
                maininterface.displayLogInInterface();
                maininterface.validAndInvalid();
            }

            if (ae.getSource() == maininterface.buyerinterface.viewall) {
                maininterface.buyerinterface.cleanProperties();
                maininterface.buyerinterface.viewall.setVisible(false);
                try {
                    try {
                        maininterface.buyerinterface.showAllResults(this);
                    } catch (Exception ex) {
                        Logger.getLogger(MyInterface.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    maininterface.buyerinterface.showResults(this);
                    maininterface.validAndInvalid();
                } catch (IOException ex) {
                    Logger.getLogger(MyInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (ae.getSource() == maininterface.buyerinterface.back) {
                maininterface.buyerinterface.viewall.setVisible(true);
                maininterface.buyerinterface.backToSearchMenu();
                maininterface.validAndInvalid();
            } else if ((ae.getSource() == maininterface.buyerinterface.logout) || (ae.getSource() == maininterface.sellerinterface.logout) || (ae.getSource() == maininterface.employeeinterface.logout) || (ae.getSource() == maininterface.visitorinterface.logout)) {
                maininterface.login.logedin = false;
                maininterface.actualuser = null;
                maininterface.displayLogInInterface();
                maininterface.validAndInvalid();
            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        maininterface.buyerinterface.BuyerResearch(ce);
    }

}
