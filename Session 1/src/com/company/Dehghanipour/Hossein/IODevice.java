package com.company.Dehghanipour.Hossein;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class IODevice {
	
	
	
	public ArrayList<String> readFile (  ArrayList<String> file , String fileName ) {
		String line = null ;
		try {
			
			FileReader fileReader = new FileReader(fileName) ; 


	        // Always wrap FileReader in BufferedReader.
	        BufferedReader bufferedReader = 
	            new BufferedReader(fileReader);

	        while((line = bufferedReader.readLine()) != null) {
	           file.add(line);
	           
	        }   

	        // Always close files.
	        bufferedReader.close();         
	    }
	    catch(Exception e) {
	        System.out.println(
	            "Unable to open file '" + 
	            fileName + "'");                
	    }
		
		return file ;
	}
	
	
	
	public void writeFile ( String fileName , ArrayList<ArrayList<Long>> calculatedTimes , int[] threadNumbers ) {
		// The name of the file to open.

        try {
            // Assume default encoding.
            FileWriter fileWriter = new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);


            // Core Info.
			ArrayList<Long> allMinimums = new ArrayList<>() ;
			ArrayList<Float> allAverageTimes = new ArrayList<>() ;
			int index = 0 ;

			for ( ArrayList<Long> AL : calculatedTimes){
            	bufferedWriter.write("Threads(" + (threadNumbers[index++]) + ")\t");
            	for ( Long time : AL){
            		bufferedWriter.write( time + " ms\t\t");
				}
				float avgTime = Main.calculateAverage(AL) ;
				allAverageTimes.add(avgTime) ;
            	bufferedWriter.write("Max: " + Collections.max(AL) + "\t Min : " + Collections.min(AL)+ " ms\t Avg : " + avgTime);
				allMinimums.add(Collections.min(AL)) ;
				bufferedWriter.write("\n");
			}
			int minThreadIndex = Main.findMinThread(allMinimums) ;
			String min_maxConclusion = "Min :\t" + Collections.min(allMinimums) + " ms | Number of Threads : (" + threadNumbers[minThreadIndex] + " )\n" ;
			String avgConclusion = "Min Average:\t" + Collections.min(allAverageTimes)  + " ms | Number Of Threads :  ( " + threadNumbers[Main.findMinAvgTime(allAverageTimes)] +" )\n"   ;
			bufferedWriter.write(min_maxConclusion);
			bufferedWriter.write(avgConclusion);



            // Always close files.
            bufferedWriter.close();
        }
        catch(Exception ex) {
            System.out.println(
                "\n++++++ERROR++++++\n");

        }
    }	
}
