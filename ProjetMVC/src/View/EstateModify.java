package View;

import Model.EstateDAO;
import Model.Estate;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.JPanel;

public class EstateModify extends JPanel {

    Estate actualestate;
    EstateDAO estatedao;
    public EstateModify(Estate estate) throws IOException {

        this.setBackground(Color.WHITE);
        this.setLayout(new GridLayout(1,1));
        this.actualestate = estate;
        this.actualestate.addInfosOnPanel(this,true);
        this.setVisible(true);

    }

}