/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author HP PAVILION
 */
public class FOP_Assignment {
    public static void main(String[] args) {
        FOP_Assignment obj = new FOP_Assignment();
        Scanner in = new Scanner(System.in);
        int x = 1;
        while(x>0){
            System.out.println("Information available:");
            System.out.println("1 - Number of jobs completed");
            System.out.println("2 - Number of jobs scheduled");
            System.out.println("3 - Number of jobs killed");
            System.out.println("4 - Number of jobs error");
            System.out.println("5 - Number of jobs by partition");
            int choice_of_info = in.nextInt();
            switch(choice_of_info){
                case 1:     //Number of jobs completed
                    System.out.println("Choose:");
                    System.out.println("1 - Summary table");
                System.out.println("2 - Specific time range");
                int choice = in.nextInt();
                switch (choice){
                    case 1: obj.table_list();
                            int choice_table = in.nextInt();
                            switch(choice_table){
                                case 13 -> obj.jobComplete_monthtable(choice_table);
                                default -> obj.jobComplete_datetable(choice_table);
                            }
                            break;
                    case 2: obj.jobComplete();
                            break;
                }
                break;
            case 2:     //Number of jobs scheduled
                System.out.println("Choose:");
                System.out.println("1 - Summary table");
                System.out.println("2 - Specific time range");
                choice = in.nextInt();
                switch (choice){
                    case 1: 
                            obj.table_list();
                            int choice_table = in.nextInt();
                            switch(choice_table){
                                case 13 -> obj.jobSched_monthtable(choice_table);
                                default -> obj.jobSched_datetable(choice_table);
                            }
                            break;
                    case 2: obj.jobSched();
                            break;
                }
                break;
            case 3:     //Number of jobs killed
                System.out.println("Search by:");
                System.out.println("1 - User ID");
                System.out.println("2 - Time range");
                choice = in.nextInt();
                switch(choice){
                    case 1: System.out.println("Choose:");
                            System.out.println("1 - Summary table");
                            System.out.println("2 - Enter user ID");
                            int choice_user = in.nextInt();
                            switch(choice_user){
                                case 1: obj.jobkill_IDtable();
                                        break;
                                case 2: System.out.print("Enter user ID:");
                                        int user_id = in.nextInt();
                                        obj.jobkill_userID(user_id);
                                        break;
                            }
                            break;
                    case 2: System.out.println("Choose:");
                            System.out.println("1 - Summary table");
                            System.out.println("2 - Specific time range");
                            int choice_time = in.nextInt();
                            switch(choice_time){
                                case 1: obj.table_list();
                                        int choice_table = in.nextInt();
                                        switch(choice_table){
                                            case 13 -> obj.jobKilled_monthtable(choice_table);   
                                            default -> obj.jobKilled_datetable(choice_table);
                                        }
                                        break;
                                case 2: obj.jobKilled();
                                        break;
                            }
                            break;
                }
                break;
                case 4:     //Number of jobs error
                    System.out.println("Search by:");
                    System.out.println("1 - username");
                    System.out.println("2 - Types of error");
                    choice = in.nextInt();
                        switch(choice){
                        case 1: System.out.println("Choose:");
                                System.out.println("1 - Summary table");
                                System.out.println("2 - Specific username");
                                choice = in.nextInt();
                                switch(choice){
                                    case 1: obj.username_table();
                                            break;
                                    case 2: System.out.print("Enter username:");
                                            String username = in.next();
                                            obj.error_username(username);
                                            break;
                                }
                                break;
                        case 2: System.out.println("Choose:");
                                System.out.println("1 - Error on node");
                                System.out.println("3 - securoty violation ");
                                int choice_type = in.nextInt();
                                switch(choice_type){
                                    case 1 -> obj.node_table();
                                }
                                break;
                    }
                    break;
                    case 5: System.out.println("Choose:");
                            System.out.println("1 - Summary table");
                            System.out.println("2 - Search by partition");
                            choice = in.nextInt();
                            switch(choice){
                                case 1 -> obj.Partition_table();
                                case 2 -> obj.Partition();
                            }
                        break;
            }
            System.out.println("1 - Back to main page");
            System.out.println("0 - Exit");
            x = in.nextInt();
        }
      
        
        try{
            in = new Scanner(new FileInputStream("C:\\Users\\HP PAVILION\\Documents\\UM Y1 S1\\FOP\\extracted_log"));
            PrintWriter out = new PrintWriter(new FileOutputStream("job_completed.txt"));
            while(in.hasNextLine()){
                String str = in.nextLine();
                if(str.contains("_job_complete:")){
                    out.println(str);
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
                    if((str.contains("Allocate")||(str.contains("_slurm_rpc_allocate_resources"))))
                    out.println(str);
                }
            }
            out.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        
        try{
            in = new Scanner(new FileInputStream("C:\\Users\\HP PAVILION\\Documents\\UM Y1 S1\\FOP\\extracted_log"));
            PrintWriter out = new PrintWriter(new FileOutputStream("job_kill.txt"));
            String str = in.nextLine();
            String temp_str = str;
            str = in.nextLine();
            while(in.hasNextLine()){
                if(temp_str.contains("REQUEST_KILL_JOB")&& temp_str.contains("_slurm_rpc_kill_job:")){
                    if(!(str.contains("job_str_signal(3):")||str.contains("Security violation,")))
                        out.println(temp_str);
                }
                temp_str = str;
                str = in.nextLine();
            }
            out.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        
        try{
            in = new Scanner(new FileInputStream("job_sched.txt"));
            PrintWriter out = new PrintWriter(new FileOutputStream("partition.txt"));
            while(in.hasNextLine()){
                String str = in.nextLine();
                String [] arr = str.split(" ");
                if(arr[2].equals("Allocate")){
                    out.println(str);
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
                    out.println(str);
                }
            }
            out.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        
        try{
            in = new Scanner(new FileInputStream("job_error.txt"));
            PrintWriter out = new PrintWriter(new FileOutputStream("err_username.txt"));
            while(in.hasNextLine()){
                String str = in.nextLine();
                if(str.contains("association"))
                    out.println(str);
            }
            out.close();
        }catch(IOException e){
            e.printStackTrace();
        }
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
    
    void table_list(){
        System.out.println("Table for: ");
        System.out.println("6 - Jun");
        System.out.println("7 - July");
        System.out.println("8 - Aug");
        System.out.println("9 - Sep");
        System.out.println("10 - Oct");
        System.out.println("11 - Nov");
        System.out.println("12 - Dec");
        System.out.println("13 - all months");
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
    
    void jobComplete_datetable(int choice_table){
        try{
            Scanner in = new Scanner(new FileInputStream("job_completed.txt"));
            System.out.println(month(choice_table) + ":");
            System.out.println("| " + String.format("%-5s","Date") + " | " + String.format("%-24s","Number of jobs completed") + " |");
            System.out.println("|       | " + String.format("%-8s","no error") + "|" + String.format("%-7s","error") + "|" + String.format("%-7s","total") + " |");
            System.out.println("+----------------------------------+");
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
                    System.out.println("| " + String.format("%-5s", temp_day) + " | " + String.format("%-8s",sum_noerror) +"|"+ String.format("%-7s",sum_error) +"|"+ String.format("%-7s",sum) + " |");
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
            System.out.println("+----------------------------------+");
            System.out.println("| " + String.format("%-5s", "Total") + " | " + String.format("%-24s",total) + " |");
            in.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    void jobComplete_monthtable(int choice_table){
        try{
            Scanner in = new Scanner(new FileInputStream("job_completed.txt"));
            System.out.println("| " + String.format("%-5s","Month") + " | " + String.format("%-24s", "Number of jobs completed" + " |"));
            System.out.println("|       | " + String.format("%-8s","no error") + "|" + String.format("%-7s","error") + "|" + String.format("%-7s","total") + " |");
            System.out.println("+----------------------------------+");
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
                System.out.println("| " + String.format("%-5s", month(temp_month)) + " | " + String.format("%-8s",sum_noerror) +"|"+ String.format("%-7s",sum_error) +"|"+ String.format("%-7s",sum) + " |");
                sum=0;
                sum_noerror=0;
                sum_error=0;
            } 
            System.out.println("+----------------------------------+");
            System.out.println("| " + String.format("%-5s", "Total") + " | " + String.format("%-24s",total) + " |");
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
    
    void jobSched_datetable(int choice_table){
        try{
            Scanner in = new Scanner(new FileInputStream("job_sched.txt"));
            System.out.println(month(choice_table) + ":");
            System.out.printf("| " + String.format("%-5s","Date") + " | " + String.format("%-24s", "Number of jobs allocated" + " |\n"));
            System.out.println("+----------------------------------+");
            int sum=0, total=0;
            String job_sched = in.nextLine();
            String [] job_sched_arr = job_sched.split(" ");
            int month = job_month(job_sched_arr);
            int day = job_day(job_sched_arr);
            while(in.hasNextLine()){
                int temp_month = month;
                while(month==choice_table){
                    int temp_day = day;
                    while(temp_day==day){
                        if(!(job_sched.contains("null")))
                             sum++;
                        if(in.hasNextLine()){
                            job_sched = in.nextLine();
                            job_sched_arr = job_sched.split(" ");
                            month = job_month(job_sched_arr);
                            day = job_day(job_sched_arr);
                        }else{
                            month=0;
                            break;
                        }
                    }
                    total += sum;
                    System.out.printf("| " + String.format("%-5s", temp_day) + " | " + String.format("%-24s",sum) + " |\n");
                    sum=0;
                }
                if(in.hasNextLine()){
                    job_sched = in.nextLine();
                    job_sched_arr = job_sched.split(" ");
                    month = job_month(job_sched_arr);
                    day = job_day(job_sched_arr);
                }else
                    break;
                if(month>choice_table)
                    break;
            }
            System.out.println("+----------------------------------+");
            System.out.println("| " + String.format("%-5s", "Total") + " | " + String.format("%-24s",total) + " |");
            in.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    void jobSched_monthtable(int choice_table){
        try{
            Scanner in = new Scanner(new FileInputStream("job_sched.txt"));
            System.out.printf("| " + String.format("%-5s","Month") + " | " + String.format("%-24s", "Number of jobs allocated" + " |\n"));
            System.out.println("+----------------------------------+");
            int sum=0, total=0;
            String job_sched = in.nextLine();
            String [] job_sched_arr = job_sched.split(" ");
            int month = job_month(job_sched_arr);
            while(in.hasNextLine()){
                int temp_month = month;
                while(temp_month==month){
                    if(!(job_sched.contains("null")))
                        sum++;
                    if(in.hasNextLine()){
                    job_sched = in.nextLine();
                    job_sched_arr = job_sched.split(" ");
                    month = job_month(job_sched_arr);
                    }else{
                        break;
                    }
                }
                total+=sum;
                System.out.printf("| " + String.format("%-5s", month(temp_month)) + " | " + String.format("%-24s",sum) + " |\n");
                sum=0;
            }            
            System.out.println("+----------------------------------+");
            System.out.println("| " + String.format("%-5s", "Total") + " | " + String.format("%-24s",total) + " |");
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
                    String job_sched = in.nextLine();
                    String [] job_sched_arr = job_sched.split(" ");
                    int time = job_month(job_sched_arr);
                    if((time>=month1 && time<=month2) && (!(job_sched.contains("null")))){
                        System.out.println(job_sched);
                        sum++;
                    }
                }             
                System.out.println("Number of jobs allocated from " + month(month1) + " to " + month(month2) + ": " + sum);
                in.close();
            }catch(IOException e ){
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
    
    void jobkill_IDtable(){
        try{
            Scanner in = new Scanner(new FileInputStream("job_kill.txt"));
            System.out.println("| " + String.format("%-15s","User ID") + " | " + String.format("%-20s", "Number of jobs killed" + " |"));
            System.out.println("+----------------------------------------+");
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
                System.out.println("| " + String.format("%-15s", userid.get(i)) + " | " + String.format("%-20s",sum.get(i)) + " |");
            }             
            System.out.println("+----------------------------------------+");
            System.out.println("| " + String.format("%-15s","Total") + " | " + String.format("%-20s",total) + " |");
            in.close();
        }catch(IOException e){
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
    
    void jobKilled_monthtable(int choice_table){
        try{
            Scanner in = new Scanner(new FileInputStream("job_kill.txt"));
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
                    System.out.println("| " + String.format("%-5s", month(temp_month)) + " | " + String.format("%-21s",sum) + " |");
                    sum=0;
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
    
    void username_table(){
        try{
            Scanner in = new Scanner(new FileInputStream("err_username.txt"));
            System.out.println("| " + String.format("%-15s","Username") + " | " + String.format("%-20s", "Number of jobs error" + " |"));
            System.out.println("+----------------------------------------+");
            ArrayList<Integer> sum = new ArrayList<Integer>();
            ArrayList<String> username = new ArrayList<String>();
            int count=0, total=0;
            boolean status = true;
            while(in.hasNextLine()){
                String error = in.nextLine();
                String [] job_error_arr = error.split("'");
                String name = job_error_arr[3];
                if(sum.size()==0){
                    username.add(name);
                    int value = 1;
                    sum.add(value);
                    count++;
                    total++;
                }else{
                    for(int i=0; i<count; i++){
                        if(name.equals(username.get(i))){
                            int value = sum.get(i);
                            value++;
                            sum.set(i, value);
                            status = false;
                            total++;
                            break;
                        }
                    }
                    if(status){
                        count++;
                        int value=1;
                        username.add(name);
                        sum.add(value);
                        total++;
                    }
                    status = true;
                }
            }
            for(int i=0; i<sum.size(); i++){
                System.out.printf("| " + String.format("%-15s", username.get(i)) + " | " + String.format("%-20s",sum.get(i)) + " |\n");
            }             
            System.out.println("+----------------------------------------+");
            System.out.println("| " + String.format("%-15s","Total") + " | " + String.format("%-20s",total) + " |");
            in.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
      
    void error_username(String username){
        try{
            Scanner in = new Scanner(new FileInputStream("err_username.txt"));
            int sum = 0;
            while(in.hasNextLine()){
                String error = in.nextLine();
                String [] job_error_arr = error.split("'");
                String name = job_error_arr[3];
                if(username.equals(name)){
                    sum++;
                }
            }
            System.out.println("Number of error by username '" + username + "': " + sum);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    void node_table(){
        try{
            Scanner in = new Scanner(new FileInputStream("C:\\Users\\HP PAVILION\\Documents\\UM Y1 S1\\FOP\\extracted_log"));
            System.out.println("Error on node:");
            System.out.printf("| " + String.format("%-7s","Month") + " | " + String.format("%-10s","Job ID") + " | " + String.format("%-14s", "Node" )+ " | " + String.format("%-30s", "Error") + "|\n");
            String str = in.nextLine();
            String temp_str = str;
            str = in.nextLine();
            int temp_month=0, temp_day=0, total=0;
            while(in.hasNextLine()){
                if((str.contains("Prolog launch failure"))||((str.contains("lookup failure"))&&(temp_str.contains("NodeList=cpu[")))){
                    total++;
                    String[] arr = temp_str.split(" ");
                    String[] arr_month = arr[0].split("-");
                    int month = Integer.parseInt(arr_month[1]);
                    String[] day_arr = arr_month[2].split("T");
                    int day = Integer.parseInt(day_arr[0]);
                    String[] arr_cause = arr[4].split("=");
                    String[] arr_jobid = arr[3].split("=");
                    if((temp_month==month)&&(temp_day==day)){
                        System.out.printf("| " + String.format("%-7s", "")+ " | " + String.format("%-10s", arr_jobid[1]) + " | " + String.format("%-14s",arr_cause[1]) + " | ");
                    }else{
                        System.out.println("+-----------------------------------------------------------------------+");
                        System.out.printf("| " + String.format("%-7s", day + " " + month(month))+ " | " + String.format("%-10s", arr_jobid[1]) + " | " + String.format("%-14s",arr_cause[1]) + " | ");
                        temp_month = month;
                        temp_day = day;
                    }
                    if(str.contains("Prolog"))
                        System.out.printf( String.format("%-30s", "Prolog setup failure") + "|\n");
                    else if(str.contains("lookup"))
                        System.out.printf(String.format("%-30s", "Lookup failure(invalid node)") + "|\n");
                    
                }
                if(in.hasNextLine()){
                    temp_str = str;
                    str = in.nextLine();
                }
            }
            System.out.println("+-----------------------------------------------------------------------+");
            System.out.println("| " + String.format("%-7s","Total")+ " | " + String.format("%-10s", total) + " | " + String.format("%-14s", "") + " | " + String.format("%-30s","") + "|");
            in.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
      
    void Error(){
        Scanner sc = new Scanner(System.in);
        try{
            Scanner in = new Scanner(new FileInputStream("job_error.txt"));
            while(in.hasNextLine()){
                String error_info = in.nextLine();
                String[] error_notfound_arr = error_info.split(" ");
                if(error_notfound_arr[2].equals("User")){
                    System.out.println(error_info);
                }
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    void Partition_table(){
        System.out.println(String.format("%-10s","| Month")+String.format("%-97s","| Number of jobs by partition") + "|");
        System.out.println("|         +------------------------------------------------------------------------------------------------+");
        String[] partition = {"cpu-opteron","cpu-epyc","gpu-k10","gpu-k40c","gpu-titan","gpu-v100s"};
        System.out.println(String.format("%-10s","|")+String.format("%-15s%-15s%-15s%-15s%-15s%-15s%-7s","| "+partition[0],"| "+partition[1],"| "+partition[2],"| "+partition[3],"| "+partition[4],"| "+partition[5],"| "+"Total")+"|");
        System.out.println("+----------------------------------------------------------------------------------------------------------+");
        int[] sum = {0,0,0,0,0,0};
        int[] sum_month = {0,0,0,0,0,0};
        try{
            Scanner in = new Scanner(new FileInputStream("partition.txt"));
            while(in.hasNextLine()){
                String str = in.nextLine();
                String[] arr = str.split(" ");
                int month = job_month(arr);
                int temp_month=month;
                while(temp_month==month){
                    for(int i=0; i<partition.length; i++){
                        if(str.contains(partition[i])){
                            sum[i]++;
                            sum_month[i]++;
                        }
                    }
                    if(in.hasNextLine()){
                        str = in.nextLine();
                        arr = str.split(" ");
                        month = job_month(arr);
                    }else
                        break;
                }
                int total = 0;
                for(int i=0; i<sum.length; i++){
                    total += sum[i];
                }  
                System.out.println(String.format("%-10s","| "+month(temp_month))+String.format("%-15s%-15s%-15s%-15s%-15s%-15s%-7s","| "+sum[0],"| "+sum[1],"| "+sum[2],"| "+sum[3],"| "+sum[4],"| "+sum[5],"| "+total)+"|");
                for(int i=0; i<sum.length; i++){
                    sum[i]=0;
                }
                total=0;
            }
            System.out.println("+----------------------------------------------------------------------------------------------------------+");
            int grand_total = 0;
            for(int i=0; i<sum_month.length; i++){
                grand_total += sum_month[i];
            }
            System.out.println(String.format("%-10s","| Total")+String.format("%-15s%-15s%-15s%-15s%-15s%-15s%-7s","| "+sum_month[0],"| "+sum_month[1],"| "+sum_month[2],"| "+sum_month[3],"| "+sum_month[4],"| "+sum_month[5],"| "+grand_total)+"|");
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
    void Partition(){
        Scanner sc = new Scanner(System.in);
            try{
                Scanner in = new Scanner(new FileInputStream("partition.txt"));
                int sum = 0;
                System.out.println("Choose partition:");
                System.out.println("1 - cpu-opteron");
                System.out.println("2 - cpu-epyc");
                System.out.println("3 - gpu-k10");
                System.out.println("4 - gpu-k40c");
                System.out.println("5 - gpu-titan");
                System.out.println("6 - gpu-v100s");
                int choice = sc.nextInt();
                String partition_input = "";
                switch(choice){
                    case 1: 
                        partition_input = "cpu-opteron";
                        break;
                    case 2: 
                        partition_input = "cpu-epyc";
                        break;
                    case 3: 
                        partition_input = "gpu-k10";
                        break;
                    case 4: 
                        partition_input = "gpu-k40c";
                        break;
                    case 5: 
                        partition_input = "gpu-titan";
                        break;
                    case 6: 
                        partition_input = "gpu-v100s";
                        break;
                }
                while(in.hasNextLine()){
                    String partition = in.nextLine();
                    String [] partition_arr = partition.split(" ");
                    String [] type_partition_arr = partition_arr[6].split("=");
                    if(partition_input.equals(type_partition_arr[1]))
                        sum++;
                }
                System.out.println("Number of jobs by " + partition_input + ": " + sum);
                in.close();
            }catch(IOException e ){
                e.printStackTrace();
            }
    }
}
