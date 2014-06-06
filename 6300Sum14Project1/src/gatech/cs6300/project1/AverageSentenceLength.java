package gatech.cs6300.project1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class AverageSentenceLength {

	public static int computeAverageSentenceLength() {
		List<String> linesList;
		 
		int totalWordsInSentences = 0;
		int numWords = 0;
	    int averageNumOfWords =0;
	    List<Integer> listNumOfWord = new ArrayList();
	
		 int numWords1 = 0;
		try {
			
			String wholeFile=FileUtils.readFileToString(inputFile);
			boolean prevWhiteSpace = true;
		 
			for(int i=0;i< wholeFile.length();i++)
			{
				 
				char c = wholeFile.charAt(i);
				  boolean currWhiteSpace = Character.isWhitespace(c);
				   
				  if(prevWhiteSpace && !currWhiteSpace){
					  numWords++;
					  totalWordsInSentences++;
					  }
					  prevWhiteSpace = currWhiteSpace;
//IsEndOfFile checks for (. , ?, !), if it is true we reset number  of words					  
					  if(isEndOfFile(c)){
					  System.out.println ("End of file" +numWords);
					  listNumOfWord.add(numWords);
					  numWords=0;
					   					  
					  }
					   
			}
			int len = listNumOfWord.size();
			averageNumOfWords=totalWordsInSentences/len;
			System.out.println(  "EverNum= " + totalWordsInSentences/len);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	//lets output line by line to view result  Junk bellow 
		try {
		  linesList= FileUtils.readLines(inputFile);
			 
		   //option 1
		   
		   for (String line : linesList) {  
			   if(line.length()>0){
		   String words[]= line.trim().split(delimeter);
		   System.out.println("Line:" +line +" :No. of words "+words.length);
		   numWords1 = numWords1+words.length;
			   }
		   }
		    
	 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	// Endo of Junk	 
		return averageNumOfWords;
	}
	
	private static boolean isEndOfFile(char c) {
		boolean endOfFile=false;
		switch (c) {
        
        case '.':
        {
     	  
           endOfFile=true;
     	   break;
        }
        case '?':
        {
     	   
           endOfFile=true;
     	   break;
        }
        case '!':
        {
     	   
           endOfFile=true;
     	   break;
        }
        default:
     	   break;
        
		  }
		return endOfFile;
		 
		
	}


	public void setMinWordLength(int i) {
		// TODO Auto-generated method stub
		
	}
	public static void setSentenceDelimiters(String del) {
		delimeter = ""+del;
		System.out.println( "Delimeter:"+delimeter);
		
	}


	public static void setFile(File file) {
		inputFile= file;
		
	}
	
}
