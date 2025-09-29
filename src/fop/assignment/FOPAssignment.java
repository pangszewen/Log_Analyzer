/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fop.assignment;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author HP PAVILION
 */
public class FOPAssignment {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Information available:");
        System.out.println("1 - Number of jobs completed");
        System.out.println("2 - Number of jobs scheduled");
        System.out.println("3 - Number of jobs error");
        System.out.println("4 - Search by ID");
        System.out.println("5 - Search by partition");
        int choice_of_info = in.nextInt();
        if(choice_of_info == 1){
            System.out.println("Enter time range by month(eg.01(Jan), 02(Feb)...): ");
            int month1 = in.nextInt();
            int month2 = in.nextInt();
            try{
                in = new Scanner(new FileInputStream("job_completed.txt"));
                int sum = 0;
                while(in.hasNextLine()){
                    String job_complete = in.nextLine();
                    String [] job_complete_arr = job_complete.split(" ");
                    String [] time_arr = job_complete_arr[0].split("-");
                    int time = Integer.parseInt(time_arr[1]);
                    if((time>=month1 && time<=month2)&&job_complete_arr[3].equals("done")){
                        sum++;
                    }
                }
               
                System.out.println("Number of jobs completed from " + month1 + " to " + month2 + ": " + sum);
            }catch(IOException e ){
                e.printStackTrace();
            }
            
            
            
        }
         
        
        
        try{
            in = new Scanner(new FileInputStream("C:\\Users\\HP PAVILION\\Documents\\UM Y1 S1\\FOP\\extracted_log"));
            PrintWriter out = new PrintWriter(new FileOutputStream("job_completed.txt"));
            while(in.hasNextLine()){
                String str = in.nextLine();
                String [] arr = str.split(" ");
                if(arr[1].equals("_job_complete:")){
                    out.write(str);
                }
            }
            out.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        
        try{
            in = new Scanner(new FileInputStream("C:\\Users\\HP PAVILION\\Documents\\UM Y1 S1\\FOP\\extracted_log"));
            PrintWriter out = new PrintWriter(new FileOutputStream("job_sched.txt"));
            while(in.hasNextLine()){
                String str = in.nextLine();
                String [] arr = str.split(" ");
                if(arr[1].equals("sched:")){
                    out.write(str);
                }
            }
            out.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        
        try{
            in = new Scanner(new FileInputStream("C:\\Users\\HP PAVILION\\Documents\\UM Y1 S1\\FOP\\extracted_log"));
            PrintWriter out = new PrintWriter(new FileOutputStream("job_sched.txt"));
            while(in.hasNextLine()){
                String str = in.nextLine();
                String [] arr = str.split(" ");
                if(arr[1].equals("sched:")){
                    out.write(str);
                }
            }
            out.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        
        try{
            in = new Scanner(new FileInputStream("C:\\Users\\HP PAVILION\\Documents\\UM Y1 S1\\FOP\\extracted_log"));
            PrintWriter out = new PrintWriter(new FileOutputStream("job_error.txt"));
            while(in.hasNextLine()){
                String str = in.nextLine();
                String [] arr = str.split(" ");
                if(arr[1].equals("error:")){
                    out.write(str);
                }
            }
            out.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        
        
    }
    
}
