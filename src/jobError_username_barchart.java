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
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;  
import org.jfree.data.category.DefaultCategoryDataset;


public class jobError_username_barchart extends JFrame{
     public ArrayList<Integer> Sum1;
    public ArrayList<String> username;
    private static final long serialVersionUID = 1L;
    
    public jobError_username_barchart(ArrayList<Integer> Sum1, ArrayList<String> username){
        this.Sum1 = Sum1;
        this.username = username;
        
        //Create dataset
        CategoryDataset dataset = createDataset();
        
        //Create chart        
        JFreeChart chart=ChartFactory.createBarChart(  
        "Number of jobs error by each user", //Chart Title  
        "All users", // Category axis  
        "Frequency", // Value axis  
        dataset,  
        PlotOrientation.VERTICAL,  
        true,true,false  
       );  
         
        chart.getCategoryPlot().getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);
        ChartPanel panel=new ChartPanel(chart);  
        
        setContentPane(panel);  
    }  

    private CategoryDataset createDataset() {  
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();  

        for(int i=0; i<Sum1.size(); i++){
            dataset.addValue(Sum1.get(i),"Number of jobs error", username.get(i));   
        }  
        return dataset;  
    }  
}
