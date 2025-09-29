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
  

public class jobComplete_barchart extends JFrame{
    public ArrayList<Integer> errorAL;
    public ArrayList<Integer> noerrorAL;
    public ArrayList<String> month_name;
    private static final long serialVersionUID = 1L;
    
    public jobComplete_barchart(ArrayList<String> month_name, ArrayList<Integer> errorAL, ArrayList<Integer> noerrorAL){
        this.month_name = month_name;
        this.errorAL = errorAL;
        this.noerrorAL = noerrorAL;
        
        //Create dataset
        CategoryDataset dataset = createDataset();
        
        //Create chart        
        JFreeChart chart=ChartFactory.createBarChart(  
        "Number of jobs completed in all months", //Chart Title  
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
    
    for(int i=0; i<errorAL.size(); i++){
        dataset.addValue(noerrorAL.get(i), "No error", month_name.get(i));  
        dataset.addValue(errorAL.get(i), "Error", month_name.get(i));  
    }  
    return dataset;  
  }  
}
    
