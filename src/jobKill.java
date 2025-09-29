
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
public class jobKill extends mySuper {
    public ArrayList<Integer> Sum1 = new ArrayList<Integer>();
    
    void jobkill_IDtable(){
        try{
            Scanner in = new Scanner(new FileInputStream("job_kill.txt"));
            System.out.println(String.format("%-15s","| User ID") + String.format("%-25s", "| Number of jobs killed") + "|");
            System.out.println("+---------------------------------------+");
            ArrayList<Integer> sum = new ArrayList<Integer>();
            ArrayList<Integer> userid = new ArrayList<Integer>();
            int total=0;
            boolean status = true;
            String temp_id = "none";
            while(in.hasNextLine()){
                String str = in.nextLine();
                String [] arr = str.split(" ");
                String jobid = arr[3];
                if(!jobid.equals(temp_id)){
                    if(sum.size()==0){
                        userid.add(Integer.parseInt(arr[5]));
                        int value = 1;
                        sum.add(value);
                        total++;
                        temp_id = jobid;
                    }else{
                        for(int i=0; i<sum.size(); i++){
                            if(Integer.parseInt(arr[5])==(userid.get(i))){
                                int value = sum.get(i);
                                value++;
                                sum.set(i, value);
                                status = false;
                                total++;
                                break;
                            }
                        }
                        if(status){
                            int value=1;
                            userid.add(Integer.parseInt(arr[5]));
                            sum.add(value);
                            total++;
                        }
                        status = true;
                        temp_id = jobid;
                    }
                }
            }
            for(int i=1; i<sum.size(); i++){
                for(int j=0; j<i; j++){
                    if(userid.get(j)>userid.get(i)){
                        int temp1 = userid.get(i);
                        userid.set(i,userid.get(j));
                        userid.set(j,temp1);
                        int temp2 = sum.get(i);
                        sum.set(i,sum.get(j));
                        sum.set(j,temp2);
                    }
                }
            }
            for(int i=0; i<sum.size(); i++){
                System.out.println(String.format("%-15s", "| " + userid.get(i)) + String.format("%-25s","| " + sum.get(i)) + "|");
            }             
            System.out.println("+---------------------------------------+");
            System.out.println(String.format("%-15s","| Total") + String.format("%-25s","| " + total) + "|");
            in.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    void jobkill_userID(int user_id){
        try{
            Scanner in = new Scanner(new FileInputStream("job_kill.txt"));
            int sum = 0;
            while(in.hasNextLine()){
                String str = in.nextLine();
                String [] arr = str.split(" ");
                int userid = Integer.parseInt(arr[5]);
                if(user_id==(userid)){
                    sum++;
                }
            }
            System.out.println("Number of jobs killed by user ID - " + user_id + ": " + sum);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    void jobKilled_monthtable(int choice_table){
        try{
            Scanner in = new Scanner(new FileInputStream("job_kill.txt"));
            Sum1.clear();
            System.out.println("| " + String.format("%-5s","Month") + " | " + String.format("%-24s", "Number of jobs killed" + " |"));
            System.out.println("+-------------------------------+");
            int sum=0, total=0;
            String str = in.nextLine();
            String[] arr = str.split(" ");
            int month = job_month(arr);
            String temp_jobid="none";
            String jobid = arr[3];
            while(in.hasNextLine()){
                int temp_month = month;
                while(temp_month==month){
                        if(!temp_jobid.equals(jobid))
                            sum++;
                        temp_jobid = jobid;
                        if(in.hasNextLine()){
                            str = in.nextLine();
                            arr = str.split(" ");
                            month = job_month(arr);
                            jobid = arr[3];
                        }else{
                            break;
                        }
                    }
                    total += sum;
                    Sum1.add(sum);
                    System.out.println("| " + String.format("%-5s", month(temp_month)) + " | " + String.format("%-21s",sum) + " |");
                    sum=0;
            }
            System.out.println("+-------------------------------+");
            System.out.println("| " + String.format("%-5s", "Total") + " | " + String.format("%-21s",total) + " |");
            this.Sum1 = Sum1;
            in.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    void jobKilled_datetable(int choice_table){
        try{
            Scanner in = new Scanner(new FileInputStream("job_kill.txt"));
            System.out.println(month(choice_table) + ":");
            System.out.println("| " + String.format("%-5s","Date") + " | " + String.format("%-24s", "Number of jobs killed" + " |"));
            System.out.println("+-------------------------------+");
            int sum=0, total=0;
            String str = in.nextLine();
            String[] arr = str.split(" ");
            int month = job_month(arr);
            int day = job_day(arr);
            String temp_jobid="none";
            String jobid = arr[3];
            while(in.hasNextLine()){
                while(month==choice_table){
                    int temp_day = day;
                    while(temp_day==day){
                        if(!temp_jobid.equals(jobid))
                            sum++;
                        temp_jobid = jobid;
                        if(in.hasNextLine()){
                            str = in.nextLine();
                            arr = str.split(" ");
                            month = job_month(arr);
                            day = job_day(arr);
                            jobid = arr[3];
                        }else{
                            month=0;
                            break;
                        }
                    }
                    total += sum;
                    System.out.println("| " + String.format("%-5s", temp_day) + " | " + String.format("%-21s",sum) + " |");
                    sum=0;
                }
                if(in.hasNextLine()){
                    str = in.nextLine();
                    arr = str.split(" ");
                    month = job_month(arr);
                    day = job_day(arr);
                    jobid = arr[3];
                }else
                    break;
                if(month>choice_table)
                    break;
            }
            System.out.println("+-------------------------------+");
            System.out.println("| " + String.format("%-5s", "Total") + " | " + String.format("%-21s",total) + " |");
            in.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
     void jobKilled(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter time range by month(eg.06(Jun), 07(July)...):");
            int month1 = sc.nextInt();
            int month2 = sc.nextInt();
            try{
                Scanner in = new Scanner(new FileInputStream("job_kill.txt"));
                String temp_jobid="none";
                int sum=0;
                while(in.hasNextLine()){
                    String str = in.nextLine();
                    String[] arr = str.split(" ");
                    int time = job_month(arr);
                    String jobid = arr[3];
                    if((!jobid.contains(temp_jobid))&&(time>=month1&&time<=month2))
                        sum++;
                    temp_jobid = jobid;
                    if(time>month2)
                        break;
                }
                System.out.println("Number of jobs killed from " + month(month1) + " to " + month(month2) + ": " + sum);
                in.close();
            }catch(IOException e ){
                e.printStackTrace();
            }
    }
}
