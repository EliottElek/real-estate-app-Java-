/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.awt.Color;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;



/**
 *
 * @author eliot
 */
public class Chart extends JPanel{
    DefaultPieDataset pieDataset;
    PiePlot pieplot;
    int[] values;
    String[] labels;
    String title;
    public Chart(int[] values, String[] labels, String title)
    {
        this.setBackground(Color.white);
        this.title = title;
        pieDataset = new DefaultPieDataset();
        this.values = values;
        this.labels = labels;
        for (int i = 0; i<this.values.length;i++)
        {
           pieDataset.setValue(this.labels[i],this.values[i]); 
        }
        JFreeChart chart = ChartFactory.createPieChart(this.title, pieDataset, true, true, true);
         pieplot = (PiePlot)chart.getPlot();
        ChartPanel c;
        c = new ChartPanel(chart);
        this.add(c);
        
    }
    
}
