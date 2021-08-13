package View;

import Model.BookingDAO;
import Model.EstateDAO;
import Model.EmployeeDAO;
import Model.BuyerDAO;
import Model.Estate;
import Model.Employee;
import Model.Buyer;
import Model.ChartDAO;
import Model.Seller;
import Model.SellerDAO;
import Model.User;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MyInterface extends JFrame {

    /**
     *
     */
    int i = 0;
    public SellerDAO sellerdao;
    public BuyerDAO buyerdao;
    public EmployeeDAO employeedao;
    public EstateDAO estatedao;
    public BookingDAO bookingdao;
    public User actualuser;
    public MyLogIn login;
    public NewAccount newaccount;
    public BuyerInterface buyerinterface;
    public SellerInterface sellerinterface;
    public MyProfile myprofile;
    public VisitorInterface visitorinterface;
    public boolean actionput = false;
    ArrayList<Estate> propertysample;
    public EmployeeInterface employeeinterface;
    Vector<Seller> listOfSellers = new Vector();
    Vector<Buyer> listOfBuyers = new Vector();
    Vector<Employee> listOfEmployees = new Vector();
    ImageIcon icon;

    public MyInterface(int w, int h, SellerDAO sellerdao, BuyerDAO buyerdao, EmployeeDAO employeedao, EstateDAO estatedao, BookingDAO bookingdao, ChartDAO chartdao) throws IOException, Exception {
        this.setResizable(true);
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        }
        login = new MyLogIn(sellerdao, buyerdao, employeedao);
        newaccount = new NewAccount(sellerdao, buyerdao, employeedao);
        propertysample = new ArrayList();
        sellerinterface = new SellerInterface(estatedao);
        buyerinterface = new BuyerInterface(propertysample, estatedao, bookingdao);
        myprofile = new MyProfile(actualuser, sellerdao, buyerdao, employeedao, bookingdao);
        employeeinterface = new EmployeeInterface(sellerdao, buyerdao, employeedao, bookingdao, estatedao, chartdao);
        visitorinterface = new VisitorInterface(propertysample, estatedao);
        icon = new ImageIcon("iconEstate.png");
        this.setIconImage(icon.getImage());
        this.setSize(w, h);
        this.setTitle("HouseClip");
        this.setBackground(Color.white);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        //adding actionlisteners to all buttons from MyLogIn panel
        this.add(login);
    }

    public void setPropertiesArray(ArrayList<Estate> properties) {
        this.propertysample = properties;
    }

    public void displayBuyerInterface() {
        this.setContentPane(buyerinterface);
    }

    public void displayLogInInterface() {
        login.clearTextFields();
        this.setContentPane(login);
    }

    public void displayNewAccountInterface() {
        this.setContentPane(newaccount);
    }

    public void removeActualUser() {
        this.actualuser = null;
    }

    public void displaySellerInterface() {
        this.setContentPane(sellerinterface);
    }

    public void displayEmployeeInterface() {
        this.setContentPane(employeeinterface);
    }

    public void displayVisitorInterface() {
        this.setContentPane(visitorinterface);
    }

    public void validAndInvalid() {
        this.invalidate();
        this.validate();
    }

    public void displayMyProfileInterface() {
        this.setContentPane(myprofile);
    }
}
