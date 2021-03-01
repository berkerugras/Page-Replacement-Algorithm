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
            if(flag==false){  //flag becomes false if  and only if there is page hit
                j=j-1;
            }
            if(j> frame.length-1){  //this is for the frame array it prevents the extension (if j becomes 3 it makes it 0 again)
                j=0;
            }
            for(int g=0;g< frame.length;g++){ //At this part the loop is looking for page hit. If there is page hit flag becomes false

                if(frame[g]==ref[i]){
                    flag=false;
                    break;
                }
                flag=true; //If there is no page hit flag remains true.


            }

            if(flag==true){ //It counts page faults and sets corresponding frame[j] to ref[i]
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

        for (i = 0; i < frame.length; i++) { //At first I added  reference set values (frame.length many) to the frame
            frame[i] = ref[i];
            c++; //Also I increased page faults here
        }
        for(int j =i;j<ref.length;j++){ //Then I created a loop for traverse through the reference set
            int init=0; //Initial frame index
            if(flagmethod(ref[j], frame)){ //I controlled page hit with that condition,If it is true continues the loop
                continue;}
            else{ //If there is no page hit
                int max=0; //initial maximum distance
                for(int k=0;k<frame.length;k++){
                    int count=0;
                    for(int l=j;l<ref.length;l++){
                        if(frame[k]!=ref[l]){
                            count++; // It starts to count the distance.It continue until it find the same reference set value with corresponding frame value
                        }
                        else{
                            break; //If it found it breaks the loop
                        }
                    }
                    if (count>max){  //I set the distance(count) to max and set the index(k) that I found in loop to init variable.
                        max=count;
                        init=k;
                    }
                }
                frame[init]=ref[j];  //Because of the page fault I set the reference value to corresponding frame
                c++; // Then I increase the page fault counter
            }
        }
        return c;
    }
//Least Recent Used Page replacement Algorithm
 public static int LRU(int[] ref, int[] frame){
     int i;
     int c = 0;
     for (i = 0; i < frame.length; i++) { //At first I added  reference values (frame.length many) to the frame
         frame[i] = ref[i];
         c++; //Also I increased page faults here
     }
     for (int j = i; j < ref.length; j++) { //Again I created a for loop for traverse through the reference set
         int init = 0; //initial frame index
         if (flagmethod(ref[j], frame)) // Page Hit control
         {
             continue;
         }
         else {
             int max = 0; //Initial maximum distance
             for (int k = 0; k < frame.length; k++) {
                 int count = 0; //for calculate the distance

                 for (int l = j; l >= 0; l--) {
                     if (frame[k] != ref[l]) { //This time it starts to count the distance backwardly.It continue until it find the same reference set value with corresponding frame value
                         count++; //If it cant find it increases the count value.
                     }
                     else {
                         break; //If it found the same value it breaks the loop.
                     }
                 }
                 if (count > max) { //I set the distance(count) to max and set the index(k) that I found in loop to init variable.
                     max = count;
                     init = k;
                 }
             }
             frame[init] = ref[j]; //Because of the page fault it replaces the frame value with reference set value
             c++; //It increases page fault
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

