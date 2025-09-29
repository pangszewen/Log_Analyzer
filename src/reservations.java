
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP PAVILION
 */
public class reservations extends mySuper{
    public ArrayList<Integer> Sum1 = new ArrayList<Integer>();
    
    void reservation_table(){
        try{
            Scanner in = new Scanner(new FileInputStream("job_sched.txt"));
            Sum1.clear();
            System.out.println(String.format("%-10s","| Month") + String.format("%-25s", "| Number of reservations") + "|");
            System.out.println("+----------------------------------+");
            int sum=0, total=0;
            String str = in.nextLine();
            String [] arr = str.split(" ");
            int month = job_month(arr); //use job_month method to determine the month 
            while(in.hasNextLine()){
                int temp_month = month;
                while(temp_month==month){
                    if(str.contains("Created reservation"))
                        sum++;
                    if(in.hasNextLine()){
                        str = in.nextLine();
                        arr = str.split(" ");
                        month = job_month(arr);
                    }else{
                        break;
                    }
                }
                total+=sum;
                Sum1.add(sum);
                System.out.println(String.format("%-10s", "| " + month(temp_month)) + String.format("%-25s", "| " + sum) + "|");
                sum=0;
            }         
            System.out.println("+----------------------------------+");
            System.out.println(String.format("%-10s", "| Total") + String.format("%-25s","| " + total) + "|");
            in.close();
            this.Sum1=Sum1;
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
