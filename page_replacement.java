package com.company;
import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        int b;
        Scanner scan= new Scanner(System.in);
        System.out.println("Please enter the size of the frame");
        b=scan.nextInt();
        int[] referencest = {1, 2, 3, 4, 2, 1, 5, 6, 2, 1, 2, 4, 3, 7, 6, 6, 5, 3, 2, 1, 2, 3, 6};
        int[] rframe=new int[b];
        for(int i=0;i<b;i++){
            rframe[i]=-1;
        }
        System.out.println("For Fifo enter 1 for optimal enter 2 for lru enter 3");
        int c=scan.nextInt();
        switch (c) {
            case 1:
            System.out.println("This is FIFO page replacement algorithm");
            System.out.println(fifo(referencest, rframe));
            break;
            case 2:
            System.out.println("This is Optimal page replacement algorithm");
            System.out.println(optimal(referencest, rframe));
            break;
            case 3:
                System.out.println("This is LRU page replacement algorithm");
                System.out.println(LRU(referencest, rframe));
            break;
        }
    }
    //FIFO Page Replacement Algorithm
    public static int fifo(int[] ref,int[] frame) {
        int c = 0;
        int j = 0;
        int i ;
        boolean flag=true;
        for (i = 0; i < ref.length; i++,j++) {
            if(flag==false){  
                j=j-1;
            }
            if(j> frame.length-1){  
                j=0;
            }
            for(int g=0;g< frame.length;g++){ 

                if(frame[g]==ref[i]){
                    flag=false;
                    break;
                }
                flag=true; 


            }

            if(flag==true){
            frame[j]=ref[i];
            c++;
            }



        }
        System.out.println("Number of the page fault is: ");
        return  c;
    }
    //Optimal Page Replacement Algorithm
    public static int optimal(int[] ref,int[] frame){
        int i ;
        int c = 0;

        for (i = 0; i < frame.length; i++) {
            frame[i] = ref[i];
            c++; 
        }
        for(int j =i;j<ref.length;j++){ 
            int init=0; 
            if(flagmethod(ref[j], frame)){ 
                continue;}
            else{ 
                int max=0; 
                for(int k=0;k<frame.length;k++){
                    int count=0;
                    for(int l=j;l<ref.length;l++){
                        if(frame[k]!=ref[l]){
                            count++; 
                        }
                        else{
                            break; /
                        }
                    }
                    if (count>max){  
                        max=count;
                        init=k;
                    }
                }
                frame[init]=ref[j]; 
                c++; 
            }
        }
        return c;
    }
//Least Recent Used Page replacement Algorithm
 public static int LRU(int[] ref, int[] frame){
     int i;
     int c = 0;
     for (i = 0; i < frame.length; i++) { 
         frame[i] = ref[i];
         c++; //Also I increased page faults here
     }
     for (int j = i; j < ref.length; j++) { 
         int init = 0; 
         if (flagmethod(ref[j], frame)) 
         {
             continue;
         }
         else {
             int max = 0; 
             for (int k = 0; k < frame.length; k++) {
                 int count = 0; 

                 for (int l = j; l >= 0; l--) {
                     if (frame[k] != ref[l]) { 
                         count++; 
                     }
                     else {
                         break;
                     }
                 }
                 if (count > max) { 
                     max = count;
                     init = k;
                 }
             }
             frame[init] = ref[j];
             c++; 
         }
     }
     return c;


    }


    public static boolean flagmethod(int ref, int[] frame) {
        for (int j : frame) {
            if (j == ref) {
                return true;
            }
        }
        return false;
    }
}

