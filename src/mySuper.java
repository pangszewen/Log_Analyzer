
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
public class mySuper {
    Scanner sc = new Scanner(System.in);
    public ArrayList<String> month_name = new ArrayList<String>();
    
    public mySuper(){
        for(int i=6; i<=12; i++){
            month_name.add(month(i));
        }
        this.month_name = month_name;
    }
    
    void monthsForTable(){  //first known as choice_table
        System.out.println("Table for: ");
        System.out.println("6 - Jun");
        System.out.println("7 - July");
        System.out.println("8 - Aug");
        System.out.println("9 - Sep");
        System.out.println("10 - Oct");
        System.out.println("11 - Nov");
        System.out.println("12 - Dec");
        System.out.println("13 - All months");
    }
    
    void graph(){
        System.out.println("Press ENTER to display graph");
        sc.nextLine(); 
    }
    
    int job_month(String[] job_arr){
        String [] time_arr = job_arr[0].split("-");
        int month = Integer.parseInt(time_arr[1]);
        return month;
    }
    
    int job_day(String[] job_arr){
        String [] time_arr = job_arr[0].split("-");
        String [] day_arr = time_arr[2].split("T");
        int day = Integer.parseInt(day_arr[0]);
        return day;
    }

    String month(int month){
        String month_name="";
        switch(month){
                    case 6:
                        month_name = "Jun";
                        break;
                    case 7:
                        month_name = "July";
                        break;
                    case 8:
                        month_name = "Aug";
                        break;
                    case 9:
                        month_name = "Sep";
                        break;
                    case 10:
                        month_name = "Oct";
                        break;
                    case 11:
                        month_name = "Nov";
                        break;
                    case 12:
                        month_name = "Dec";
                        break;
                }
        return month_name;
    }
}

