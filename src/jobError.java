
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

public class jobError extends mySuper {
    public ArrayList<Integer> Sum1 = new ArrayList<Integer>();
    public ArrayList<String> Usernames = new ArrayList<String>();
    public ArrayList<String> Node = new ArrayList<String>();
    public ArrayList<String> qos = new ArrayList<String>();

    
    void username_table(){
        try{
            Scanner in = new Scanner(new FileInputStream("err_username.txt"));
            Usernames.clear();      //clear the array list of Usernames
            Sum1.clear();           //clear the array list of Sum1
            System.out.println(String.format("%-20s","| Username") + String.format("%-25s", "| Number of jobs error")+ "|");
            System.out.println("+--------------------------------------------+");
            ArrayList<Integer> sum = new ArrayList<Integer>();
            String error = in.nextLine();
            String [] job_error_arr = error.split("'");
            String name = job_error_arr[3];
            int count=0, total=0;
            boolean status = true;
            while(in.hasNextLine()){
                if(sum.size()==0){ //if sum arraylist is empty
                    Usernames.add(name); //add name into username array list
                    int value = 1;
                    sum.add(value); //add value into sum arraylist(0 index element represents number of jobs by 0 index username)
                    count++;
                    total++;
                }else{ //after first name is added
                    for(int i=0; i<count; i++){
                        if(name.equals(Usernames.get(i))){ //if name equals 0 index username
                            int value = sum.get(i);
                            value++; //add value into sum arraylist
                            sum.set(i, value);
                            status = false;
                            total++;
                            break;
                        }
                    }
                    if(status){
                        count++;
                        int value=1;
                        Usernames.add(name);
                        sum.add(value);
                        total++;
                    }
                    status = true;
                }
                if(in.hasNextLine()){
                    error = in.nextLine();
                    job_error_arr = error.split("'");
                    name = job_error_arr[3];
                }else
                    break;
                
            }
            for(int i=0; i<sum.size(); i++){
                System.out.println(String.format("%-20s", "| " + Usernames.get(i)) + String.format("%-25s", "| " + sum.get(i)) + "|");
            }             
            System.out.println("+--------------------------------------------+");
            System.out.println(String.format("%-20s","| Total") + String.format("%-25s", "| " + total) + "|");
            int max=sum.get(0), min=sum.get(0), index_max=0, index_min=0;
            String max_username = Usernames.get(0), min_username = Usernames.get(0);
            for(int i=1; i<sum.size(); i++){
                if(sum.get(i)>max){
                    max = sum.get(i);
                    index_max = i;
                    max_username = Usernames.get(i);
                }else if (sum.get(i)>max)
                    max_username += ", " + Usernames.get(i);
                if(sum.get(i)<min){
                    min = sum.get(i);
                    index_min = i;
                    min_username = Usernames.get(i);
                }else if (sum.get(i)==min)
                    min_username += ", " + Usernames.get(i);
            }
            System.out.println("Highest number of error by username: '" + max_username + "' = " + sum.get(index_max));
            System.out.println("Lowest number of error by username: '" + min_username + "' = " + sum.get(index_min));
            this.Sum1 = sum;
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

    void node_error(){
        try{
            Scanner in = new Scanner(new FileInputStream("job_error.txt"));
            Node.clear();
            Sum1.clear();
            System.out.println("Nodes not responding:");
            System.out.println(String.format("%-10s","| Date") + String.format("%-15s","| Node") + String.format("%-15s", "| Frequency" )+ "|");
            System.out.println("+---------------------------------------+");
            String str = in.nextLine();
            String[] arr = str.split(" ");
            int month = job_month(arr);
            int day = job_day(arr);
            int total=0;
            ArrayList<String> node = new ArrayList<String>();
            ArrayList<Integer> sum = new ArrayList<Integer>();
            boolean status = true;
            while(in.hasNextLine()){
                int temp_month = month;
                int temp_day = day;
                while((temp_month==month)&&(temp_day==day)){
                    if(str.contains("not responding")){
                        total++;
                        if(node.size()==0){        // when the node array list is empty
                            node.add(arr[3]);
                            int value=1;
                            sum.add(value);
                        }else{
                            for(int i=0; i<node.size(); i++){       //compare the node with the existing nodes in array list
                                if(arr[3].equals(node.get(i))){     //if the node is already in the array list, get its sum and add value to it
                                    int value = sum.get(i);
                                    value++;
                                    sum.set(i, value);
                                    status = false;
                                }
                            }   
                            if(status){         //if the node is new, add to array list
                                node.add(arr[3]);
                                int value=1;
                                sum.add(value);
                            }
                            status = true;
                        }
                    }
                    if(in.hasNextLine()){
                        str = in.nextLine();
                        arr = str.split(" ");
                        month = job_month(arr);
                        day = job_day(arr);
                    }else
                        break;
                }
                if(node.size()!=0){
                    for(int i=0; i<node.size(); i++){
                        if(i==0)
                            System.out.println(String.format("%-10s","| " + temp_day + " " + month(temp_month)) + String.format("%-15s","| " + node.get(i)) + String.format("%-15s", "| " + sum.get(i))+ "|");
                        else
                            System.out.println(String.format("%-10s","| ") + String.format("%-15s","| " + node.get(i)) + String.format("%-15s", "| " + sum.get(i))+ "|");
                    }
                    System.out.println("+---------------------------------------+");
                    for(int i=0; i<node.size(); i++){       //preparation of array list for graph
                        if(Node.size()==0){
                            Node.add(node.get(i));
                            Sum1.add(sum.get(i));
                        }else{
                            for(int j=0; j<Node.size(); j++){
                                if(Node.get(j).equals(node.get(i))){
                                    int value = Sum1.get(i);
                                    value += sum.get(i);
                                    Sum1.set(i, value);
                                    status = false;
                                }
                            }
                            if(status){
                                Node.add(node.get(i));
                                int value=sum.get(i);
                                Sum1.add(value);
                            }
                            status = true;
                        }
                    }
                    node.clear();
                    sum.clear();  
                }
            }
            System.out.println(String.format("%-25s","| Total") + String.format("%-15s", "| " + total)+ "|");
            System.out.println("+---------------------------------------+");
            this.Node = Node;
            this.Sum1 = Sum1;
            in.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    void Security_violation_userID(){
        try{
            Scanner in = new Scanner(new FileInputStream("job_error.txt"));
            System.out.println("Security violation:");
            System.out.println(String.format("%-15s","| User ID") + String.format("%-25s","| Type") + String.format("%-20s", "| Number of errors" ) + "|");
            System.out.println("+-----------------------------------------------------------+");
            ArrayList<String> type = new ArrayList<String>();       //array list for type of security violation error done by user ID
            ArrayList<Integer> userID = new ArrayList<Integer>();   // array list for user ID
            ArrayList<Integer> sum = new ArrayList<Integer>();      //array list for number of jobs error due to security violation by respective user ID
            int total=0;    //total is the total number of jobs error due to security violation
            String str = in.nextLine();
            String[] arr = str.split(" ");
            boolean status = true;      //status is the existence of user ID in array list
            while(in.hasNextLine()){
                if(str.contains("Security violation")){
                    if(type.size()==0){
                        type.add(arr[4]);
                        userID.add(Integer.parseInt(arr[arr.length-1]));
                        int value=1;
                        sum.add(value);
                    }else{
                        for(int i=0; i<userID.size(); i++){
                            if(Integer.parseInt(arr[arr.length-1])==userID.get(i)){
                                if(!type.get(i).contains(arr[4])){
                                    String temp_str = type.get(i);
                                    temp_str += ", " + arr[4];
                                    type.set(i, temp_str);
                                }
                                int value = sum.get(i);
                                value++;
                                sum.set(i, value);
                                status=false;       //status false if user ID already exist in array list
                            }
                        }
                        if(status){                 // if status true, add the new user ID into array list
                            type.add(arr[4]);
                            userID.add(Integer.parseInt(arr[arr.length-1]));
                            int value=1;
                            sum.add(value);
                        }
                        status=true;
                    }
                }
                if(in.hasNextLine()){
                    str = in.nextLine();
                    arr = str.split(" ");
                }else
                    break;
            }
            for(int i=0; i<userID.size(); i++){
                System.out.println(String.format("%-15s","| " + userID.get(i)) + String.format("%-25s","| " + type.get(i)) + String.format("%-20s", "| " + sum.get(i)) + "|");
                total += sum.get(i);
            }
            System.out.println("+-----------------------------------------------------------+");
            System.out.println(String.format("%-40s","| " + "Total number of security violation") + String.format("%-20s","| " + total) + "|");
            in.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    void Security_violation_type(){
        try{
            Scanner in = new Scanner(new FileInputStream("job_error.txt"));
            System.out.println("Security violation:");
            System.out.println(String.format("%-10s","| Month") + String.format("%-40s","| Type of security violation") + String.format("%-20s", "| Total by month" ) + "|");
            System.out.println(String.format("%-10s","| ") + String.format("%-20s","| REQUEST_KILL_JOB") + String.format("%-20s", "| JOB_UPDATE" ) + String.format("%-20s", "| " ) + "|");
            System.out.println("+---------------------------------------------------------------------+");
            String[] type = {"REQUEST_KILL_JOB", "JOB_UPDATE"}; //array list for type of security violation error
            int[] sum = {0, 0};     //array list for number of each type of security violation error
            int total=0, total_type1=0, total_type2=0;
            String str = in.nextLine();
            String[] arr = str.split(" ");
            int month = job_month(arr);
            while(in.hasNextLine()){
                int temp_month = month;
                while(temp_month == month){
                    for(int i=0; i<type.length; i++){
                        if(str.contains(type[i])){
                            sum[i]++;
                            total++;
                            break;
                        }
                    }
                    if(in.hasNextLine()){
                        str = in.nextLine();
                        arr = str.split(" ");
                        month = job_month(arr);
                    }else
                        break;
                }
                total_type1 += sum[0];      //calculate the total of error by type of security violation
                total_type2 += sum[1];
                System.out.println(String.format("%-10s","| " + month(temp_month)) + String.format("%-20s","| " + sum[0]) + String.format("%-20s", "| " + sum[1]) + String.format("%-20s", "| " + total) + "|");
                total=0;            //clear the total for next month
                for(int i=0; i<sum.length; i++){
                    sum[i]=0;       //clear the sum array for next month
                }
            }
            int grand_total = total_type1 + total_type2;        //calculate the total number of job error due to security violation
            System.out.println("+---------------------------------------------------------------------+");
            System.out.println(String.format("%-10s","| Total") + String.format("%-20s","| " + total_type1) + String.format("%-20s", "| " + total_type2) + String.format("%-20s", "| " + grand_total) + "|");
            in.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    void invalid_qos(){
        try{
            Scanner in = new Scanner(new FileInputStream("job_error.txt"));
            qos.clear();
            int total=0;
            while(in.hasNextLine()){
                String str = in.nextLine();
                String[] arr = str.split(" ");
                boolean status = true;
                if(str.contains("Invalid qos")){
                    if(qos.size()==0){
                        qos.add(arr[4]);
                        int value=1;
                        Sum1.add(value);
                    }else{
                        for(int i=0; i<qos.size(); i++){
                            if(qos.get(i).equals(arr[4])){
                                int value = Sum1.get(i);
                                value++;
                                Sum1.set(i, value);
                                status=false;
                                break;
                            }
                        }
                        if(status){
                            qos.add(arr[4]);
                            int value=1;
                            Sum1.add(value);
                        }
                    }
                    total++;
                }
            }
            System.out.println("Number of jobs error due to invalid quality of service:");
            for(int i=0; i<qos.size(); i++){
                System.out.println("Type " + qos.get(i) + " = " + Sum1.get(i));
            }
            System.out.println("Total: " + total);
            in.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

