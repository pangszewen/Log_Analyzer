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

public class jobByPartition_barchart extends JFrame{
    public ArrayList<Integer> Sum1;
    public ArrayList<Integer> Sum2;
    public ArrayList<Integer> Sum3;
    public ArrayList<Integer> Sum4;
    public ArrayList<Integer> Sum5;
    public ArrayList<Integer> Sum6;
    public ArrayList<String> month_name;
    public String[] Partition;
    
    private static final long serialVersionUID = 1L;
    
    public jobByPartition_barchart(ArrayList<String> month_name, ArrayList<Integer> Sum1, ArrayList<Integer> Sum2, ArrayList<Integer> Sum3, ArrayList<Integer> Sum4, ArrayList<Integer> Sum5, ArrayList<Integer> Sum6, String[] Partition){
        this.month_name = month_name;
        this.Sum1 = Sum1;
        this.Sum2 = Sum2;
        this.Sum3 = Sum3;
        this.Sum4 = Sum4;
        this.Sum5 = Sum5;
        this.Sum6 = Sum6;
        this.Partition = Partition;
                
        //Create dataset
        CategoryDataset dataset = createDataset();
        
        //Create chart        
        JFreeChart chart=ChartFactory.createBarChart(  
        "Number of jobs by partition in all months", //Chart Title  
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
        dataset.addValue(Sum1.get(i), Partition[0], month_name.get(i));  
        dataset.addValue(Sum2.get(i), Partition[1], month_name.get(i));
        dataset.addValue(Sum3.get(i), Partition[2], month_name.get(i));
        dataset.addValue(Sum4.get(i), Partition[3], month_name.get(i));
        dataset.addValue(Sum5.get(i), Partition[4], month_name.get(i));
        dataset.addValue(Sum6.get(i), Partition[5], month_name.get(i));
    }  
    return dataset;  
  }  
}
