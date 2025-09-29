
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
public class jobSched extends mySuper{
    public ArrayList<Integer> Sum1 = new ArrayList<Integer>();
    
    void jobSched_monthtable(int choice_table){
        try{
            Scanner in = new Scanner(new FileInputStream("job_sched.txt"));
            System.out.println(String.format("%-10s","| Month") + String.format("%-25s", "|Number of jobs allocated" + "|"));
            System.out.println("+----------------------------------+");
            int sum=0, total=0;
            String str = in.nextLine();
            String [] arr = str.split(" ");
            int month = job_month(arr); //use job_month method to determine the month 
            while(in.hasNextLine()){
                int temp_month = month;
                while(temp_month==month){
                    if(!(str.contains("null")||str.contains("reservation")))
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
            System.out.println(String.format("%-10s", "| Total") + String.format("%-25s", "| " + total) + "|");
            in.close();
            this.Sum1=Sum1;
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    void jobSched_datetable(int choice_table){
        try{
            Scanner in = new Scanner(new FileInputStream("job_sched.txt"));
            System.out.println(month(choice_table) + ":");
            System.out.println(String.format("%-10s","| Date") + String.format("%-25s", "|Number of jobs allocated" + "|"));
            System.out.println("+----------------------------------+");
            int sum=0, total=0;
            String str = in.nextLine();
            String [] arr = str.split(" ");
            int month = job_month(arr);
            int day = job_day(arr);
            while(in.hasNextLine()){
                int temp_month = month;
                while(month==choice_table){
                    int temp_day = day;
                    while(temp_day==day){
                        if(!(str.contains("null")||str.contains("reservation")))
                             sum++;
                        if(in.hasNextLine()){
                            str = in.nextLine();
                            arr = str.split(" ");
                            month = job_month(arr);
                            day = job_day(arr);
                        }else{
                            month=0;
                            break;
                        }
                    }
                    total += sum;
                    System.out.println(String.format("%-10s", "| " + temp_day) + String.format("%-25s", "| " + sum) + "|");
                    sum=0;
                }
                if(in.hasNextLine()){
                    str = in.nextLine();
                    arr = str.split(" ");
                    month = job_month(arr);
                    day = job_day(arr);
                }else
                    break;
                if(month>choice_table)
                    break;
            }
            System.out.println("+----------------------------------+");
            System.out.println(String.format("%-10s", "| Total") + String.format("%-25s","| " + total) + "|");
            in.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    void jobSched(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter time range by month(eg.06(Jun), 07(July)...):");
        int month1 = sc.nextInt();
        int month2 = sc.nextInt();
        try{
            Scanner in = new Scanner(new FileInputStream("job_sched.txt"));
            int sum = 0;
            while(in.hasNextLine()){
                String str = in.nextLine();
                String [] arr = str.split(" ");
                int time = job_month(arr);
                if((time>=month1 && time<=month2) && (!(str.contains("null"))) && (!(str.contains("reservation"))))
                    sum++;
            }             
            System.out.println("Number of jobs allocated from " + month(month1) + " to " + month(month2) + ": " + sum);
            in.close();
        }catch(IOException e ){
            e.printStackTrace();
        }
    }
}

