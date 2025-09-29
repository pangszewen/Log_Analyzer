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


public class jobError_node_barchart extends JFrame{
    public ArrayList<Integer> Sum1;
    public ArrayList<String> Node;
    private static final long serialVersionUID = 1L;
    
    public jobError_node_barchart(ArrayList<Integer> Sum1, ArrayList<String> Node){
        this.Sum1 = Sum1;
        this.Node = Node;
        
        //Create dataset
        CategoryDataset dataset = createDataset();
        
        //Create chart        
        JFreeChart chart=ChartFactory.createBarChart(  
        "Number of nodes not responding", //Chart Title  
        "Nodes", // Categorical axis  
        "Frequency", // Numerical axis  
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
            dataset.addValue(Sum1.get(i),"Number of jobs error", Node.get(i));   
        }  
        return dataset;  
    }  

}
