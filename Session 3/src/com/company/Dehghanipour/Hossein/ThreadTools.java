package com.company.Dehghanipour.Hossein;

import java.util.ArrayList;
import java.util.Collections;

public class ThreadTools  {

    public static long multiplicationThread(){
        final int MILLION = 1000000;
        final int THOUSAND = 1000 ;
        long startTime = System.nanoTime();
        //Create a ThreadPool due to the number of wanted threads

        for ( MatrixMultiplier mp : MatrixMultiplier.getThreadPool()){
            mp.start();
        }
        try {
            for ( MatrixMultiplier mp :MatrixMultiplier.getThreadPool()){
                mp.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        //Terminate Thread Pool.
        return (totalTime/MILLION) ;
    }

    public static long AdderThread(){

        final int MILLION = 1000000;
        final int THOUSAND = 1000 ;
        long startTime = System.nanoTime();
        //Create a ThreadPool due to the number of wanted threads

        for ( Adder A : Adder.getThreadPool()){
            A.start();
        }
        try {
            for ( Adder A :Adder.getThreadPool()){
                A.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        return (totalTime/MILLION) ;

    }

    public static int findMinThreadIndex(ArrayList<Long> allMinimums){
        Long min = Collections.min(allMinimums) ;
        return allMinimums.indexOf(min);
    }


    public static void printTheMultiplierTable(ArrayList<Long> times , int[] threads ){

        for(int i = 0 ; i < threads.length ; i++){
            System.out.println("Thread(" + threads[i]+") : "+ times.get(i));
        }
        int minIndex = findMinThreadIndex(times);

        System.out.println("Min : " + times.get(minIndex) + " ns\t ->\t Thread(" + threads[minIndex]+")");

    }

    public static void printAdderTable(){
        for(int i = 0 ; i < Adder.frequencyKeeper.length ; i++){
            if(Adder.frequencyKeeper[i] != 0 ){
                System.out.println("(" + i + ") -> " + Adder.frequencyKeeper[i]);
            }
        }
    }

    public static void matrixFileWriter(Boolean sinh , ArrayList<Long> eachThreadTimes , int[] threadsNumbers , int arraySize){
        if ( sinh == true){
            IODevice.deletePredefinedFile(Main.HYPERBOLIC_MATRIX_FILE_NAME);
            IODevice.writeFileMatrix(Main.HYPERBOLIC_MATRIX_FILE_NAME,eachThreadTimes,threadsNumbers,arraySize);
        }
        else {
            IODevice.deletePredefinedFile(Main.NORMAL_MATRIX_FILE_NAME);
            IODevice.writeFileMatrix(Main.NORMAL_MATRIX_FILE_NAME,eachThreadTimes,threadsNumbers,arraySize);

        }
    }

}
