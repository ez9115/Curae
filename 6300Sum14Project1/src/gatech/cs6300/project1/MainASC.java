package gatech.cs6300.project1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainASC {

	static int len  = 0;
	static int minWordLength = 3; //Default
	static String fileName="";
	static String sentenceDelimeter=" ";// Default
	static String endSentenceDelimeter;
	static File inputFile;
	static String fileDir;
	public static void main(String[] args) throws IOException {
		 
		readArguments(args);
		AverageSentenceLength asl = new AverageSentenceLength();
		asl.setFile(new File(fileDir+fileName));
		asl.setSentenceDelimiters(sentenceDelimeter);
		asl.setMinWordLength( minWordLength);
		asl.setEndSentenceDelimeter(endSentenceDelimeter);
		asl.computeAverageSentenceLength();

	}
	public static void readArguments(String args[]){
		List<String> argsList  = new ArrayList<String>();  // not used yet
		System.out.println( "Num of Args length " + args.length);// +args[0]);
	
			for (int i=0; i < args.length; i++) {
				argsList.add(args[i]);
				if (i == 0)
				fileDir= args[i];
				if (i==1)
				fileName=args[1];
			     if(args[i].startsWith("-d")){
			    	 sentenceDelimeter =args[++i];
			    	// System.out.println("Delimeters="+sentenceDelimeter);
			     }
			     else if(args[i].startsWith("-l")){
			    	 minWordLength =Integer.parseInt(args[++i]);
			    	// System.out.println("minWordLength="+minWordLength);
			     }
			     // for future release
			    // else if(args[i].startsWith("-s")){
			    //	 endSentenceDelimeter = args[++i];
			   // 	 System.out.println("minWordLength="+minWordLength);
			   //  }
			}
		  if (args.length <2 ){
			  System.out.println( " Please enter required locaton and name of the file");
			  System.exit(0);
		  }
	}
	 
}
