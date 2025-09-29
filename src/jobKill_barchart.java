/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP PAVILION
 */
import java.util.ArrayList;
import javax.swing.JFrame;  
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;  
import org.jfree.data.category.DefaultCategoryDataset;

public class jobKill_barchart extends JFrame{
    public ArrayList<Integer> Sum1;
    public ArrayList<String> month_name;
    private static final long serialVersionUID = 1L;
    
    public jobKill_barchart(ArrayList<String> month_name, ArrayList<Integer> Sum1){
        this.month_name = month_name;
        this.Sum1 = Sum1;
        
        //Create dataset
        CategoryDataset dataset = createDataset();
        
        //Create chart        
        JFreeChart chart=ChartFactory.createBarChart(  
        "Number of jobs killed in all months", //Chart Title  
        "All months", // Category axis  
        "Frequency", // Value axis  
        dataset,  
        PlotOrientation.VERTICAL,  
        true,true,false  
       );  
  
    ChartPanel panel=new ChartPanel(chart);  
    setContentPane(panel);  
  }  
  
  private CategoryDataset createDataset() {  
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
    
    for(int i=0; i<Sum1.size(); i++){
        dataset.addValue(Sum1.get(i),"Number of jobs killed", month_name.get(i));   
    }  
    return dataset;  
  }  
}
