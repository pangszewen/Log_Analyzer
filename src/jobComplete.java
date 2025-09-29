
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
public class jobComplete extends mySuper{    
    public ArrayList<Integer> error = new ArrayList<Integer>();
    public ArrayList<Integer> noerror = new ArrayList<Integer>();
    
    
    void jobComplete_monthtable(int choice_table){
        try{
            Scanner in = new Scanner(new FileInputStream("job_completed.txt"));
            error.clear();
            noerror.clear();
            System.out.println(String.format("%-10s", "| Month") + String.format("%-30s", "| Number of jobs completed") + "|");
            System.out.println(String.format("%-10s", "|") + String.format("%-10s","| no error")+String.format("%-10s","| error")+String.format("%-10s","| total") + "|");
            System.out.println("+---------------------------------------+");
            int sum=0, total=0, sum_error=0, sum_noerror=0;
            String str = in.nextLine();
            String temp_str = str;
            str = in.nextLine();
            String [] str_arr = str.split(" ");
            int month = job_month(str_arr);
            while(in.hasNextLine()){
                int temp_month = month;
                while(temp_month==month){
                    if(str.contains("done")&&temp_str.contains("WEXITSTATUS")){
                        String[] temp_arr = temp_str.split(" ");
                        if(Integer.parseInt(temp_arr[4])==0){
                            sum_noerror++;
                            sum++;
                        }else{
                            sum_error++;
                            sum++;
                        }
                    }else if(str.contains("done")){
                        sum_error++;
                        sum++;
                    }
                    if(in.hasNextLine()){
                        temp_str = str;
                        str = in.nextLine();
                        str_arr = str.split(" ");
                        month = job_month(str_arr);
                    }else
                        break;
                }
                total += sum;
                error.add(sum_error);
                noerror.add(sum_noerror);
                System.out.println(String.format("%-10s", "| " + month(temp_month)) + String.format("%-10s","| " + sum_noerror) + String.format("%-10s","| " + sum_error) + String.format("%-10s","| " + sum) + "|");
                sum=0;
                sum_noerror=0;
                sum_error=0;
            } 
            System.out.println("+---------------------------------------+");
            System.out.println(String.format("%-10s", "| Total") + String.format("%-30s","| " + total) + "|");
            this.error = error;
            this.noerror = noerror;
            in.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    void jobComplete_datetable(int choice_table){
        try{
            Scanner in = new Scanner(new FileInputStream("job_completed.txt"));
            System.out.println(month(choice_table) + ":");
            System.out.println(String.format("%-10s","| Date") + String.format("%-30s","| Number of jobs completed") + "|");
            System.out.println(String.format("%-10s", "|") + String.format("%-10s","| no error") + String.format("%-10s","| error") + String.format("%-10s","| total") + "|");
            System.out.println("+---------------------------------------+");
            int sum=0, total=0, sum_error=0, sum_noerror=0;
            String str = in.nextLine();
            String temp_str = str;
            str = in.nextLine();
            String [] str_arr = str.split(" ");
            int month = job_month(str_arr);
            int day = job_day(str_arr);
            while(in.hasNextLine()){
                while(month==choice_table){
                    int temp_day = day;
                    while(temp_day==day){
                        if(str.contains("done")&&temp_str.contains("WEXITSTATUS")){
                            String[] temp_arr = temp_str.split(" ");
                            if(Integer.parseInt(temp_arr[4])==0){
                                sum_noerror++;
                                sum++;
                            }else{
                                sum_error++;
                                sum++;
                            }
                        }else if(str.contains("done")){
                            sum_error++;
                            sum++;
                        }
                        if(in.hasNextLine()){
                            temp_str = str;
                            str = in.nextLine();
                            str_arr = str.split(" ");
                            month = job_month(str_arr);
                            day = job_day(str_arr);
                            }else{
                                month=0;    //end while loop
                                break;
                            }
                        }
                    total += sum;
                    System.out.println(String.format("%-10s", "| " + temp_day) + String.format("%-10s","| " + sum_noerror) + String.format("%-10s","| " + sum_error) + String.format("%-10s","| " + sum) + "|");
                    sum=0;
                    sum_noerror=0;
                    sum_error=0;
                }
                if(in.hasNextLine()){
                    temp_str = str;
                    str = in.nextLine();
                    str_arr = str.split(" ");
                    month = job_month(str_arr);
                    day = job_day(str_arr);
                }else{
                    break;
                }
                if(month>choice_table)
                    break;
            }
            System.out.println("+---------------------------------------+");
            System.out.println(String.format("%-10s", "| Total") + String.format("%-30s","| " + total) + "|");
            in.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    void jobComplete(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter time range by month(eg.06(Jun), 07(July)...):");
            int month1 = sc.nextInt();
            int month2 = sc.nextInt();
            try{
                Scanner in = new Scanner(new FileInputStream("job_completed.txt"));
                int sum=0, sum_error=0, sum_noerror=0;
                String str = in.nextLine();
                String temp_str = str;
                str = in.nextLine();
                String [] str_arr = str.split(" ");
                int time = job_month(str_arr);
                while(str!="null"){
                    if((time>=month1 && time<=month2)){
                        if(str.contains("done")&&temp_str.contains("WEXITSTATUS")){
                            String[] temp_arr = temp_str.split(" ");
                            if(Integer.parseInt(temp_arr[4])==0){
                                sum_noerror++;
                                sum++;
                            }else{
                                sum_error++;
                                sum++;
                            }
                        }else if(str.contains("done")){
                            sum_error++;
                            sum++;
                        }
                    }
                    if(in.hasNextLine()){
                        temp_str = str;
                        str = in.nextLine();
                        str_arr = str.split(" ");
                        time = job_month(str_arr);
                    }else
                        break;
                }               
                System.out.println("Number of jobs completed from " + month(month1) + " to " + month(month2) + ": ");
                System.out.println("Without errors: " + sum_noerror);
                System.out.println("With errors: " + sum_error);
                System.out.println("Total: " + sum);
                in.close();
            }catch(IOException e ){
                e.printStackTrace();
            }
    }
}
    
