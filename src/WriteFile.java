
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP PAVILION
 */
public class WriteFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            Scanner in = new Scanner(new FileInputStream("C:\\Users\\HP PAVILION\\Documents\\UM Y1 S1\\FOP\\extracted_log"));
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
            Scanner in = new Scanner(new FileInputStream("C:\\Users\\HP PAVILION\\Documents\\UM Y1 S1\\FOP\\extracted_log"));
            PrintWriter out = new PrintWriter(new FileOutputStream("job_sched.txt"));
            while(in.hasNextLine()){
                String str = in.nextLine();
                String [] arr = str.split(" ");
                if(arr[1].contains("sched:")||arr[1].contains("backfill")){
                    out.println(str);
                }

            }
            out.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        
        try{
            Scanner in = new Scanner(new FileInputStream("C:\\Users\\HP PAVILION\\Documents\\UM Y1 S1\\FOP\\extracted_log"));
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
            Scanner in = new Scanner(new FileInputStream("C:\\Users\\HP PAVILION\\Documents\\UM Y1 S1\\FOP\\extracted_log"));
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
            Scanner in = new Scanner(new FileInputStream("job_error.txt"));
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
        
        try{
            Scanner in = new Scanner(new FileInputStream("job_sched.txt"));
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
            Scanner in = new Scanner(new FileInputStream("C:\\Users\\HP PAVILION\\Documents\\UM Y1 S1\\FOP\\extracted_log"));
            PrintWriter out = new PrintWriter(new FileOutputStream("executionTime.txt"));
            while(in.hasNextLine()){
                String str = in.nextLine();
                if(str.contains("Allocate")){
                    out.println(str);
                }
                if(str.contains("done")){
                    out.println(str);
                }
                if(str.contains("REQUEST_KILL_JOB")){
                    out.println(str);
                }
                if(str.contains("denied")){
                    out.println(str);
                }
                if(str.contains("backfill")){
                    out.println(str);
                }
                
            }
            out.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

