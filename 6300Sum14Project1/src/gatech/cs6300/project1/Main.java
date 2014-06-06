package gatech.cs6300.project1;

import java.io.File;
import java.io.IOException;


public class Main {

	static int len  = 0;
	static String fileName="";
	static String delimeter;
	static File inputFile;
	
	public static void main(String[] args) throws IOException {
		try {
			fileName = args[0];
			delimeter= args[1];
			System.out.println("delimeter:"+ args[1]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("File="+fileName);
		setFile(new File(fileName));
		setSentenceDelimiters(args[1]);
		computeAverageSentenceLength();

	}

}
