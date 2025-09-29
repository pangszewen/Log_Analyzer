
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
public class jobByPartition extends mySuper{
    public ArrayList<Integer> Sum1 = new ArrayList<Integer>();
    public ArrayList<Integer> Sum2 = new ArrayList<Integer>();
    public ArrayList<Integer> Sum3 = new ArrayList<Integer>();
    public ArrayList<Integer> Sum4 = new ArrayList<Integer>();
    public ArrayList<Integer> Sum5 = new ArrayList<Integer>();
    public ArrayList<Integer> Sum6 = new ArrayList<Integer>();
    public String[] Partition;
    
    public jobByPartition(){
        String[] Partition = {"cpu-opteron","cpu-epyc","gpu-k10","gpu-k40c","gpu-titan","gpu-v100s"};
        this.Partition = Partition;
    }
    
    void Partition_table(){
        Sum1.clear();
        Sum2.clear();
        Sum3.clear();
        Sum4.clear();
        Sum5.clear();
        Sum6.clear(); 
        System.out.println(String.format("%-10s","| Month")+String.format("%-97s","| Number of jobs by partition") + "|");
        System.out.println("|         +------------------------------------------------------------------------------------------------+");
        System.out.println(String.format("%-10s","|")+String.format("%-15s%-15s%-15s%-15s%-15s%-15s%-7s","| "+Partition[0],"| "+Partition[1],"| "+Partition[2],"| "+Partition[3],"| "+Partition[4],"| "+Partition[5],"| "+"Total")+"|");
        System.out.println("+----------------------------------------------------------------------------------------------------------+");
        int[] sum = {0,0,0,0,0,0};
        int[] sum_partition = {0,0,0,0,0,0};
       try{
           Scanner in = new Scanner(new FileInputStream("partition.txt"));
            String str = in.nextLine();
            String[] arr = str.split(" ");
            String [] type_partition_arr = arr[6].split("=");
            int month = job_month(arr);
            while(in.hasNextLine()){
                int temp_month=month;
                while(temp_month==month){
                    for(int i=0; i<Partition.length; i++){
                        if(Partition[i].equals(type_partition_arr[1])){
                            sum[i]++;
                            sum_partition[i]++;
                        }
                    }
                    if(in.hasNextLine()){
                        str = in.nextLine();
                        arr = str.split(" ");
                        type_partition_arr = arr[6].split("=");
                        month = job_month(arr);
                    }else
                        break;
                }
                int total = 0;
                for(int i=0; i<sum.length; i++){
                    total += sum[i];
                }  
                System.out.println(String.format("%-10s","| "+month(temp_month))+String.format("%-15s%-15s%-15s%-15s%-15s%-15s%-7s","| "+sum[0],"| "+sum[1],"| "+sum[2],"| "+sum[3],"| "+sum[4],"| "+sum[5],"| "+total)+"|");
                Sum1.add(sum[0]);
                Sum2.add(sum[1]);
                Sum3.add(sum[2]);
                Sum4.add(sum[3]);
                Sum5.add(sum[4]);
                Sum6.add(sum[5]);
                for(int i=0; i<sum.length; i++){
                    sum[i]=0;
                }
                total=0;
            }
            System.out.println("+----------------------------------------------------------------------------------------------------------+");
            int grand_total = 0, max=sum_partition[0], min=sum_partition[0], index_max=0, index_min=0;
            String max_partition=Partition[0], min_partition=Partition[0];
            for(int i=1; i<sum_partition.length; i++){
                grand_total += sum_partition[i];
                if(sum_partition[i]>max){       //calculate the highest number of jobs by partition
                    max = sum_partition[i];
                    index_max = i;
                    max_partition = Partition[i];
                }else if (sum_partition[i]==max)
                    max_partition += ", " + Partition[i];
                if(sum_partition[i]<min){       //calculate the lowest number of jobs by partition
                    min = sum_partition[i];
                    index_min = i;
                    min_partition = Partition[i];
                }else if (sum_partition[i]==max)
                    min_partition += ", " + Partition[i];
            }
            System.out.println(String.format("%-10s","| Total")+String.format("%-15s%-15s%-15s%-15s%-15s%-15s%-7s","| "+sum_partition[0],"| "+sum_partition[1],"| "+sum_partition[2],"| "+sum_partition[3],"| "+sum_partition[4],"| "+sum_partition[5],"| "+grand_total)+"|");
            System.out.println("The highest number of jobs by partition: " + max_partition + " = " + sum_partition[index_max]);
            System.out.println("The lowest number of jobs by partition: " + min_partition + " = " + sum_partition[index_min]);
            this.Sum1 = Sum1;
            this.Sum2 = Sum2;
            this.Sum3 = Sum3;
            this.Sum4 = Sum4;
            this.Sum5 = Sum5;
            this.Sum6 = Sum6;
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    void Partition_highlow(){
        
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
