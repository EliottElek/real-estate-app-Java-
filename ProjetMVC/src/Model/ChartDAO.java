package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * In this class,, we area going to get all the interesting datas from the
 * database, in order to create our different chartts. this includes : -number
 * of properties in the different locations -number of properties needing
 * renovation -number of properties -number of properties pdepending on their
 * type (farm, hosue, appartment,...) -number of properties to buy against to
 * rent
 */
public class ChartDAO implements ChartConstInterface {

    private Connection myConn;

    public ChartDAO() throws Exception {
        /*
         // get db properties
         Properties props = new Properties();
         props.load(new FileInputStream("demo.properties"));
		
         String user = props.getProperty("user");
         String password = props.getProperty("password");
         String dburl = props.getProperty("dburl");
         */
        String user = "root";
        String password = "elektra1";
        String url = "jdbc:mysql://localhost:3306/realestatedatabase?useSSL=false";
        // connect to database
        myConn = DriverManager.getConnection(url, user, password);

        System.out.println("DB connection successful to: " + url);
    }

    public Chart getProximityChart() throws SQLException {
        Chart chart;
        int nbveryclose;
        int nbclose;
        int nbfar;
        int nbinter;
        int index = 0;
        int[] datas = new int[4];
        PreparedStatement myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `proximity` = 'Far'");
        ResultSet rs = myStmt.executeQuery();
        while (rs.next()) {
            nbfar = rs.getInt("num_rows");
            datas[index] = nbfar;
            index += 1;

        }
        myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `proximity` = 'Intermediate'");
        rs = myStmt.executeQuery();
        while (rs.next()) {
            nbinter = rs.getInt("num_rows");
            datas[index] = nbinter;
            index += 1;
        }
        myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `proximity` = 'Close'");
        rs = myStmt.executeQuery();
        while (rs.next()) {
            nbclose = rs.getInt("num_rows");
            datas[index] = nbclose;
            index += 1;
        }
        myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `proximity` = 'Very close'");
        rs = myStmt.executeQuery();
        while (rs.next()) {
            nbveryclose = rs.getInt("num_rows");
            datas[index] = nbveryclose;
            index += 1;
        }
        chart = new Chart(datas, typesofproximities, "types of proximities");
        return chart;

    }

    public Chart getLocationsChart() throws SQLException {
        Chart chart;
        int nbtown;
        int nbsub;
        int nbcity;
        int nbcountry;
        int nbseaside;
        int nbmountain;
        int index = 0;
        int[] datas = new int[6];
        PreparedStatement myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `location` = 'City'");
        ResultSet rs = myStmt.executeQuery();
        while (rs.next()) {
            nbcity = rs.getInt("num_rows");
            datas[index] = nbcity;
            index += 1;

        }
        myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE   `location` = 'Town'");
        rs = myStmt.executeQuery();
        while (rs.next()) {
            nbtown = rs.getInt("num_rows");
            datas[index] = nbtown;
            index += 1;
        }
        myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `location` = 'Suburbs'");
        rs = myStmt.executeQuery();
        while (rs.next()) {
            nbsub = rs.getInt("num_rows");
            datas[index] = nbsub;
            index += 1;
        }
        myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `location` = 'Country'");
        rs = myStmt.executeQuery();
        while (rs.next()) {
            nbcountry = rs.getInt("num_rows");
            datas[index] = nbcountry;
            index += 1;
        }
        myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `location` = 'Sea side'");
        rs = myStmt.executeQuery();
        while (rs.next()) {
            nbseaside = rs.getInt("num_rows");
            datas[index] = nbseaside;
            index += 1;
        }
        myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `location` = 'Mountain'");
        rs = myStmt.executeQuery();
        while (rs.next()) {
            nbmountain = rs.getInt("num_rows");
            datas[index] = nbmountain;
            index += 1;
        }
        chart = new Chart(datas, typesoflocations, "types of locations");
        return chart;

    }

    public Chart getTypesOfPropertiesChart() throws SQLException {

        Chart chart;
        int nbfamilyhouse;
        int nbhouse;
        int nbappartment;
        int nbcommestate;
        int nbindusestate;
        int nbland;
        int nbagriland;
        int nbbuildland;
        int nbcottage;
        int nbcastle;
        int nbmansion;
        int nbmas;
        int nbstud;
        int nbranch;
        int nbfarm;
        int nbbarge;
        int nbbastide;
        int nbwindmill;
        int nbbarn;
        int index = 0;
        int[] datas = new int[19];
        PreparedStatement myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `type` = 'Single family detached house'");
        ResultSet rs = myStmt.executeQuery();
        while (rs.next()) {
            nbfamilyhouse = rs.getInt("num_rows");
            datas[index] = nbfamilyhouse;
            index += 1;

        }
        myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `type` = 'House'");
        rs = myStmt.executeQuery();
        while (rs.next()) {
            nbhouse = rs.getInt("num_rows");
            datas[index] = nbhouse;
            index += 1;
        }
        myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `type` = 'Appartment'");
        rs = myStmt.executeQuery();
        while (rs.next()) {
            nbappartment = rs.getInt("num_rows");
            datas[index] = nbappartment;
            index += 1;
        }
        myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `type` = 'Commercial Estate'");
        rs = myStmt.executeQuery();
        while (rs.next()) {
            nbcommestate = rs.getInt("num_rows");
            datas[index] = nbcommestate;
            index += 1;
        }
        myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `type` = 'Industrial Estate'");
        rs = myStmt.executeQuery();
        while (rs.next()) {
            nbindusestate = rs.getInt("num_rows");
            datas[index] = nbindusestate;
            index += 1;
        }
        myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `type` = 'land'");
        rs = myStmt.executeQuery();
        while (rs.next()) {
            nbland = rs.getInt("num_rows");
            datas[index] = nbland;
            index += 1;
        }
        myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `type` = 'Agricol Land'");
        rs = myStmt.executeQuery();
        while (rs.next()) {
            nbagriland = rs.getInt("num_rows");
            datas[index] = nbagriland;
            index += 1;
        }
        myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `type` = 'Building Land'");
        rs = myStmt.executeQuery();
        while (rs.next()) {
            nbbuildland = rs.getInt("num_rows");
            datas[index] = nbbuildland;
            index += 1;
        }
        myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `type` = 'Cottage'");
        rs = myStmt.executeQuery();
        while (rs.next()) {
            nbcottage = rs.getInt("num_rows");
            datas[index] = nbcottage;
            index += 1;
        }
        myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `type` = 'Castle'");
        rs = myStmt.executeQuery();
        while (rs.next()) {
            nbcastle = rs.getInt("num_rows");
            datas[index] = nbcastle;
            index += 1;
        }
        myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `type` = 'Mansion'");
        rs = myStmt.executeQuery();
        while (rs.next()) {
            nbmansion = rs.getInt("num_rows");
            datas[index] = nbmansion;
            index += 1;
        }
        myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `type` = 'Mas'");
        rs = myStmt.executeQuery();
        while (rs.next()) {
            nbmas = rs.getInt("num_rows");
            datas[index] = nbmas;
            index += 1;
        }
        myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `type` = 'Ranch'");
        rs = myStmt.executeQuery();
        while (rs.next()) {
            nbranch = rs.getInt("num_rows");
            datas[index] = nbranch;
            index += 1;
        }
        myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `type` = 'Farm'");
        rs = myStmt.executeQuery();
        while (rs.next()) {
            nbfarm = rs.getInt("num_rows");
            datas[index] = nbfarm;
            index += 1;
        }
        myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `type` = 'Stud'");
        rs = myStmt.executeQuery();
        while (rs.next()) {
            nbstud = rs.getInt("num_rows");
            datas[index] = nbstud;
            index += 1;
        }
        myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `type` = 'Barge'");
        rs = myStmt.executeQuery();
        while (rs.next()) {
            nbbarge = rs.getInt("num_rows");
            datas[index] = nbbarge;
            index += 1;
        }
        myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `type` = 'Bastide'");
        rs = myStmt.executeQuery();
        while (rs.next()) {
            nbbastide = rs.getInt("num_rows");
            datas[index] = nbbastide;
            index += 1;
        }
        myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `type` = 'Windmill'");
        rs = myStmt.executeQuery();
        while (rs.next()) {
            nbwindmill = rs.getInt("num_rows");
            datas[index] = nbwindmill;
            index += 1;
        }
        myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `type` = 'Barn'");
        rs = myStmt.executeQuery();
        while (rs.next()) {
            nbbarn = rs.getInt("num_rows");
            datas[index] = nbbarn;
            index += 1;
        }

        chart = new Chart(datas, typesofproperties, "types of properties");
        return chart;

    }
    public Chart getRenovationChart() throws SQLException {
        Chart chart;
        int nbnoren;
        int nblittleren;
        int nbsomeren;
        int importantren;
        int completeren;
        int index = 0;
        int[] datas = new int[5];
        PreparedStatement myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `renovation` = 'No renovation'");
        ResultSet rs = myStmt.executeQuery();
        while (rs.next()) {
            nbnoren = rs.getInt("num_rows");
            datas[index] = nbnoren;
            index += 1;

        }
        myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `renovation` = 'Very little renovation'");
        rs = myStmt.executeQuery();
        while (rs.next()) {
            nblittleren = rs.getInt("num_rows");
            datas[index] = nblittleren;
            index += 1;
        }
        myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `renovation` = 'Some renovation'");
        rs = myStmt.executeQuery();
        while (rs.next()) {
            nbsomeren = rs.getInt("num_rows");
            datas[index] = nbsomeren;
            index += 1;
        }
        myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `renovation` = 'Important renovation'");
        rs = myStmt.executeQuery();
        while (rs.next()) {
            importantren = rs.getInt("num_rows");
            datas[index] = importantren;
            index += 1;
        }
         myStmt = myConn.prepareStatement("SELECT COUNT(*) AS num_rows FROM `estate` WHERE  `renovation` = 'Complete renovation'");
        rs = myStmt.executeQuery();
        while (rs.next()) {
            completeren = rs.getInt("num_rows");
            datas[index] = completeren;
            index += 1;
        }
        chart = new Chart(datas, typesrenovation, "types of renovations");
        return chart;

    }

}
