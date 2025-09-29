
import java.util.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP PAVILION
 */
public class avgExecutionTime {
    static void avgExecutionTime() throws ParseException{
        try{
            Scanner sc = new Scanner(new FileInputStream("executionTime.txt"));
            long totalTime = 0, allocateTotalTime=0, backfillTotalTime =0;
            int totalJob=0, allocateTotalJob = 0, backfillTotalJob=0;
            Date allocateTime = null;
            Date backfillTime = null;
            Date doneTime = null;
            Date requestTime = null;
            
            SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            
            while(sc.hasNextLine()){
                String str = sc.nextLine();
                if(str.contains("Allocate")){
                    String timestamp = str.substring(1, str.indexOf("]"));
                    allocateTime = timestampFormat.parse(timestamp);
                    String [] str_arr = str.split(" ");
                    String ID = str_arr[3];
                    
                    while(sc.hasNextLine()){
                        String nextline = sc.nextLine();
                        if(nextline.contains(ID)&&nextline.contains("done")){
                            timestamp = nextline.substring(1, nextline.indexOf("]"));
                            doneTime = timestampFormat.parse(timestamp);
                            long executionTime = (doneTime.getTime() - allocateTime.getTime()) / 1000;
                            totalTime+=executionTime;
                            allocateTotalTime+=executionTime;
                            totalJob++;
                            allocateTotalJob++;
                            break;
                        }
                        else{
                            if(nextline.contains(ID)&&nextline.contains("REQUEST_KILL_JOB")){
                                String lineBeneath = sc.nextLine();
                                if(lineBeneath.contains("error:")){
                                    System.out.println(lineBeneath);
                                    break;
                                   //ignore
                                }
                                else{
                                    timestamp = nextline.substring(1, nextline.indexOf("]"));
                                    requestTime = timestampFormat.parse(timestamp);  
                                    long executionTime = (requestTime.getTime() - allocateTime.getTime()) / 1000;
                                    totalTime+=executionTime;
                                    allocateTotalTime+=executionTime;
                                    totalJob++;
                                    allocateTotalJob++;
                                    break;
                                }
                            }
                            else{
                                break;
                                //ignore
                            }
                        }
                    }  
                }
                if(str.contains("sched/backfill:")){
                    String timestamp = str.substring(1, str.indexOf("]"));
                    backfillTime = timestampFormat.parse(timestamp);
                    String [] str_arr = str.split(" ");
                    String ID = str_arr[4];
                    
                    while(sc.hasNextLine()){
                        String nextline = sc.nextLine();
                        if(nextline.contains(ID)&&nextline.contains("done")){
                            timestamp = nextline.substring(1, nextline.indexOf("]"));
                            doneTime = timestampFormat.parse(timestamp);
                            long executionTime = (doneTime.getTime() - backfillTime.getTime()) / 1000;
                            totalTime+=executionTime;
                            backfillTotalTime+=executionTime;
                            totalJob++;
                            backfillTotalJob++;
                            break;
                        }
                        else{
                            if(nextline.contains(ID)&&nextline.contains("REQUEST_KILL_JOB")){
                                String lineBeneath = sc.nextLine();
                                if(lineBeneath.contains("error:")){
                                    break;
                                   //ignore
                                }
                                else{
                                    timestamp = nextline.substring(1, nextline.indexOf("]"));
                                    requestTime = timestampFormat.parse(timestamp);  
                                    long executionTime = (requestTime.getTime() - backfillTime.getTime()) / 1000;
                                    totalTime+=executionTime;
                                    backfillTotalTime+=executionTime;
                                    totalJob++;
                                    backfillTotalJob++;
                                    break;
                                }
                            }
                            else{
                                break;
                                //ignore
                            }
                        }
                    }  
                }
            }
            long allocateAvg = allocateTotalTime/allocateTotalJob;
            long backfillAvg = backfillTotalTime/backfillTotalJob;
            long avg = totalTime/totalJob;
            String format = "| %-20s | %-20s | %-25s | %-30s |%n";
            System.out.println("+----------------------------------------------------------------------------------------------------------+");
            System.out.printf(format, "", "Execution Time(s)", "Number of jobs executed", "Average execution time(s)");
            System.out.println("+----------------------------------------------------------------------------------------------------------+");
            System.out.printf(format, "Allocate", allocateTotalTime, allocateTotalJob, allocateAvg);
            System.out.println("+----------------------------------------------------------------------------------------------------------+");
            System.out.printf(format, "Backfill", backfillTotalTime, backfillTotalJob, backfillAvg);
            System.out.println("+----------------------------------------------------------------------------------------------------------+");
            System.out.printf(format, "Allocate+Backfill", totalTime, totalJob, avg);
            System.out.println("+----------------------------------------------------------------------------------------------------------+");
                       
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
