
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.text.ParseException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP PAVILION
 */
public class FOP {
    public static void main(String[] args) throws ParseException{
        Scanner in = new Scanner(System.in);
        
        //create object
        FOP obj = new FOP();
        jobComplete jobComplete = new jobComplete();
        jobSched jobSched = new jobSched();
        jobKill jobKill = new jobKill();
        jobError jobError = new jobError();
        jobByPartition jobByPartition = new jobByPartition();
        mySuper mySuper = new mySuper();
        reservations reservations = new reservations();
        
        int x=1;
        while(x>0){
            System.out.println("Information available:");
            System.out.println("1 - Number of jobs completed");
            System.out.println("2 - Number of jobs scheduled");
            System.out.println("3 - Number of jobs killed");
            System.out.println("4 - Number of jobs error");
            System.out.println("5 - Number of jobs by partition");
            System.out.println("6 - Average execution time");
            System.out.println("7 - Number of reservations made");
            int choice_of_info = in.nextInt();
            
            switch(choice_of_info){
                case 1: //job complete
                    System.out.println("Choose:");
                    System.out.println("1 - Summary table");
                    System.out.println("2 - Specific time range");
                    int choice = in.nextInt();
                    switch (choice){
                        case 1: 
                            mySuper.monthsForTable(); 
                            int chosen_month = in.nextInt();
                            switch(chosen_month){
                                case 13 -> {
                                    jobComplete.jobComplete_monthtable(chosen_month);       //display summary table for all months
                                    mySuper.graph();
                                    obj.display_jobComplete_barchart(mySuper.month_name, jobComplete.error, jobComplete.noerror);
                                }
                                default -> jobComplete.jobComplete_datetable(chosen_month);     //display summary table for chosen month                                        
                            }           
                            break;
                        case 2: 
                            jobComplete.jobComplete();
                            break;
                    }
                    break;  //case 1 (job complete) break 
                
                case 2: //job scheduled
                    System.out.println("Choose:");
                    System.out.println("1 - Summary table");
                    System.out.println("2 - Specific time range");
                    choice = in.nextInt();
                    switch (choice){
                    case 1: 
                        mySuper.monthsForTable();       //display available months
                        int choice_table = in.nextInt();
                        switch(choice_table){
                            case 13-> {
                                jobSched.jobSched_monthtable(choice_table);
                                mySuper.graph();
                                obj.display_jobSched_barchart(mySuper.month_name, jobSched.Sum1);
                            }
                            default-> jobSched.jobSched_datetable(choice_table);
                        }
                        break;
                    case 2:
                        jobSched.jobSched();
                        break;
                    }
                    break; //case 2 (job scheduled) break
                
                case 3: //job killed
                    System.out.println("Search by:");
                    System.out.println("1 - User ID");
                    System.out.println("2 - Time range");
                    choice = in.nextInt();
                    switch(choice){
                        case 1: 
                            System.out.println("Choose:");
                            System.out.println("1 - Summary table");
                            System.out.println("2 - Enter user ID");
                            int choice_user = in.nextInt();
                            switch(choice_user){
                                case 1 -> jobKill.jobkill_IDtable();
                                case 2 -> {
                                    System.out.print("Enter user ID:");
                                    int user_id = in.nextInt();
                                    jobKill.jobkill_userID(user_id);
                                }
                            }
                            break;
                        case 2:
                            System.out.println("Choose:");
                            System.out.println("1 - Summary table");
                            System.out.println("2 - Specific time range");
                            int choice_time = in.nextInt();
                            switch(choice_time){
                                case 1 -> { 
                                    mySuper.monthsForTable();
                                    int choice_table = in.nextInt();
                                    switch(choice_table){
                                        case 13 -> {
                                            jobKill.jobKilled_monthtable(choice_table);
                                            mySuper.graph();
                                            obj.display_jobKill_barchart(mySuper.month_name, jobKill.Sum1);
                                        }   
                                        default -> jobKill.jobKilled_datetable(choice_table);
                                    }
                                }
                                case 2 -> jobKill.jobKilled();
                            }
                    }
                    break; //case 3 (job killed) break
                    
                case 4: //job errors
                    System.out.println("Search by:");
                    System.out.println("1 - username");
                    System.out.println("2 - Types of error");
                    choice = in.nextInt();
                    switch(choice){
                        case 1:
                            System.out.println("Choose:");
                            System.out.println("1 - Summary table");
                            System.out.println("2 - Specific username");
                            choice = in.nextInt();
                            switch(choice){
                                case 1 -> { 
                                    jobError.username_table();
                                    mySuper.graph();
                                    obj.display_jobErrorUsername_barchart(jobError.Usernames, jobError.Sum1);
                                }
                                case 2 -> { 
                                    System.out.print("Enter username:");
                                    String username = in.next();
                                    jobError.error_username(username);
                                }
                            }
                            break;
                        case 2: 
                            System.out.println("Choose:");
                            System.out.println("1 - Node not responding");
                            System.out.println("2 - Security violation");
                            System.out.println("3 - Invalid quality of service");
                            int choice_type = in.nextInt();
                            switch(choice_type){
                                case 1 -> {
                                    jobError.node_error();
                                    mySuper.graph();
                                    obj.display_jobErrorNode_barchart(jobError.Node, jobError.Sum1);
                                }
                                case 2 -> {
                                    System.out.println("Summary table for:");
                                    System.out.println("1 - User ID");
                                    System.out.println("2 - Type of security violation");
                                    int choice_table = in.nextInt();
                                    switch(choice_table){
                                        case 1 -> jobError.Security_violation_userID();
                                        case 2 -> jobError.Security_violation_type();
                                    }
                                }
                                case 3 -> jobError.invalid_qos();
                            }
                            break;
                    }  
                    break;  //case 4 (job errors) break
                    
                case 5: //jobs by partition
                    System.out.println("Choose:");
                    System.out.println("1 - Summary table");
                    System.out.println("2 - Search by partition");
                    choice = in.nextInt();
                    switch(choice){
                        case 1 -> {
                            jobByPartition.Partition_table();
                            mySuper.graph();
                            obj.display_jobPartition_barchart(mySuper.month_name, jobByPartition.Sum1, jobByPartition.Sum2, jobByPartition.Sum3, jobByPartition.Sum4, jobByPartition.Sum5, jobByPartition.Sum6, jobByPartition.Partition);
                        }
                        case 2 -> jobByPartition.Partition();
                    }
                    break; //case 5 (job by partition) break
                case 6: avgExecutionTime.avgExecutionTime();
                        break;
                case 7: reservations.reservation_table();
                        mySuper.graph();
                        obj.display_reservations_barchart(mySuper.month_name, reservations.Sum1);
                        break;
            }
            System.out.println("1 - Back to main page");
            System.out.println("0 - Exit");
            x = in.nextInt();  
        }
    }
    
    public void display_jobComplete_barchart(ArrayList<String>month_name, ArrayList<Integer>error, ArrayList<Integer>noerror){
        try{
            SwingUtilities.invokeAndWait(()->{  
            jobComplete_barchart example=new jobComplete_barchart(month_name, error, noerror);  
            example.setSize(800, 400);  
            example.setLocationRelativeTo(null);  
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
            example.setVisible(true);  
            });  
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch(InvocationTargetException e){
            e.printStackTrace();
        }
    }
    
    public void display_jobSched_barchart(ArrayList<String>month_name, ArrayList<Integer>Sum1){
        try{
            SwingUtilities.invokeAndWait(()->{  
            jobSched_barchart example=new jobSched_barchart(month_name, Sum1);  
            example.setSize(800, 400);  
            example.setLocationRelativeTo(null);  
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
            example.setVisible(true);  
            });  
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch(InvocationTargetException e){
            e.printStackTrace();
        }
    }
    
    public void display_jobKill_barchart(ArrayList<String>month_name, ArrayList<Integer>Sum1){
        try{
            SwingUtilities.invokeAndWait(()->{  
            jobKill_barchart example=new jobKill_barchart(month_name, Sum1);  
            example.setSize(800, 400);  
            example.setLocationRelativeTo(null);  
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
            example.setVisible(true);  
            });  
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch(InvocationTargetException e){
            e.printStackTrace();
        }
    }
    
    public void display_jobErrorUsername_barchart(ArrayList<String>usernames, ArrayList<Integer>sums){
        try{
            SwingUtilities.invokeAndWait(()->{  
            jobError_username_barchart example = new jobError_username_barchart(sums, usernames);  
            example.setSize(800, 400);  
            example.setLocationRelativeTo(null);  
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
            example.setVisible(true);  
            });  
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch(InvocationTargetException e){
            e.printStackTrace();
        }
    }
    
    public void display_jobErrorNode_barchart(ArrayList<String>Node, ArrayList<Integer>sums){
        try{
            SwingUtilities.invokeAndWait(()->{  
            jobError_node_barchart example = new jobError_node_barchart(sums, Node);  
            example.setSize(800, 400);  
            example.setLocationRelativeTo(null);  
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
            example.setVisible(true);  
            });  
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch(InvocationTargetException e){
            e.printStackTrace();
        }
    }
        
    public void display_jobPartition_barchart(ArrayList<String>month_name, ArrayList<Integer>Sum1, ArrayList<Integer>Sum2, ArrayList<Integer>Sum3, ArrayList<Integer>Sum4, ArrayList<Integer>Sum5, ArrayList<Integer>Sum6, String[]Partition){
        try{
            SwingUtilities.invokeAndWait(()->{  
            jobByPartition_barchart example=new jobByPartition_barchart(month_name, Sum1, Sum2, Sum3, Sum4, Sum5, Sum6, Partition);  
            example.setSize(800, 400);  
            example.setLocationRelativeTo(null);  
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
            example.setVisible(true);  
            });  
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch(InvocationTargetException e){
            e.printStackTrace();
        }
    }
    
     public void display_reservations_barchart(ArrayList<String>month_name, ArrayList<Integer>Sum1){
        try{
            SwingUtilities.invokeAndWait(()->{  
            reservations_barchart example=new reservations_barchart(month_name, Sum1);  
            example.setSize(800, 400);  
            example.setLocationRelativeTo(null);  
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
            example.setVisible(true);  
            });  
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch(InvocationTargetException e){
            e.printStackTrace();
        }
    }
}
