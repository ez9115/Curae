package gatech.cs6300.project1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class AverageSentenceLength {
	int minWordLength = 3;
	public String fileName = "";
	public String delimeter;
	public File inputFile;
	public String fileDir;
	public List<Character> wordsDelimeters = new ArrayList<Character>();
	public String multiDelimeters = " ";
	char superDelimeter = ' ';
	private String sentenceDelimeter = "";
	private String endSentenceDelimeter = "";

	public int computeAverageSentenceLength() {
		List<String> linesList;

		int totalWordsInSentences = 0;
		int numWords = 0;
		int averageNumOfWords = 0;

		List<Integer> listNumOfWord = new ArrayList<Integer>();

		int lineCount = 0;
		int sentencesCount = 0;
		//System.out.println ("sentenceDelimeter" +getSentenceDelimiters());
		wordsDelimeters = parseMultiDelimeters(getSentenceDelimiters());//MmultiDelimeters);
		try {
			superDelimeter = findSuperValidDelimeter(getSentenceDelimiters());
		} catch (Exception e1) {
			//System.out.println("Valid File Name is a required attribute");
			//e1.printStackTrace();
		
		}
		// Split file into lines
		try {
			linesList = FileUtils.readLines(getFile());
			numWords = 0;
			totalWordsInSentences = 0;

			for (String line : linesList) {
				lineCount++;
				// go through lines
				if (line.length() > 0) {
					// split each line into words
					// add code here

					String processedLine = processLine(wordsDelimeters, line,
							superDelimeter);
				
					String words[] =
                            processedLine.trim().split(
							superDelimeter == '|' ? "\\" + superDelimeter
									: superDelimeter + "");

					for (int i = 0; i < words.length; i++) {

						if (words[i].length() >= minWordLength) {
							 numWords++;
							 totalWordsInSentences++;
							// System.out.println("test=" +
							///  (words[i].length()>1?words[i]+":" +words[i].charAt(words[i].length()-1):words[i]));
							 if (words[i].length() == (minWordLength )&&
									isCharEndOfFile(words[i].charAt(words[i].length()-1))){
								 //( words[i].endsWith(".")|| words[i].endsWith("?")||words[i].endsWith("!"))){
							 //	System.out.println("trouble word: " + words[i] +
							 		//	":"+words[i].length()  +":"+minWordLength);
						 --numWords;
						 --totalWordsInSentences;
							 
							 }
							 
	
						}

						numWords = lookUpEndPunctuation(numWords,
								listNumOfWord, words, i, lineCount);
					 //it was reset in lookUp for starting new count of new sentence
								 
					}

				}
			}
			sentencesCount = listNumOfWord.size();

		} catch (Exception e) {
			System.out.println( " File " + inputFile +" not valid . Please  re-enter new file name");
			 e.printStackTrace();
		}
		 
		return getAverageNum(totalWordsInSentences , sentencesCount);
	}

	private int getAverageNum(int totalWordsInSentences, int sentencesCount) {
		int retvalue=0;
		if (totalWordsInSentences > 0 && sentencesCount > 0 )
			retvalue = totalWordsInSentences/sentencesCount;
		else if (totalWordsInSentences > 0 && sentencesCount == 0){
			retvalue = totalWordsInSentences;
			sentencesCount =1;
		}
		String msg = "Everage Number = " + retvalue  
				+" Total number of words: "+ totalWordsInSentences + " : Numebr of Sentences:  " + sentencesCount ;
		displayMessage(msg);
		return retvalue;
	}

	private char findSuperValidDelimeter(String multiDelimeters)
			throws Exception {
		char c = ' ';
		String fileToString = FileUtils.readFileToString(getFile());
		for (int i = 0; i < multiDelimeters.length(); i++) {
			char cDel = multiDelimeters.charAt(i);
			//System.out.println("item Delimeter:"+cDel);
			if (fileToString.contains(cDel + ""))
				return cDel;
		}
		//System.out.println("SupperDelimeter:" +c);
		return c;
	}

	private String processLine(List<Character> wordsDelimeters, String line,
			char superDelimeter) {

		for (int i = 0; i < wordsDelimeters.size(); i++) {
			line = line.replace(wordsDelimeters.get(i), wordsDelimeters.get(0));

		}

		// System.out.println( "Converted to new line with only one delimeter"+
		// line);
		return line;
	}

	private List<Character> parseMultiDelimeters(String multiDelimeters) {
		List<Character> listDelimeters = new ArrayList<Character>();
		for (int i = 0; i < multiDelimeters.length(); i++) {
			char c = multiDelimeters.charAt(i);
			//System.out.println(" ParseDel"+c);
			listDelimeters.add(c);
		}

		return listDelimeters;

	}

	private void displayMessage(String msg) {
		System.out.println(msg);

	}

	public int lookUpEndPunctuation(int numWords, List<Integer> listNumOfWord,
			String[] words, int i, int lineCount) {
		for (int n = 0; n < words[i].length(); n++) {

			char c = words[i].charAt(n);

			if (isCharEndOfFile(c) ){//|| getEndSentenceDelimiter().equals("" + c)) {
				//System.out.println("End of file:" + numWords);
				if (numWords > 20)
					displayWarning(numWords, words[i], lineCount);
				// totalWordsInSentences++;
				listNumOfWord.add(numWords);
				numWords = 0;

			}

		}
		return numWords;
	}

	private void displayWarning(int numWords, String sentence, int line) {
		System.out
				.println("Warning: You used Excessive number of words:" + numWords
						+ ": on the line# " + line + " in sentence ending with: "
						+ sentence);

	}

	private boolean isCharEndOfFile(char c) {
		boolean endOfFile = false;

		switch (c) {

		case '.': {

			endOfFile = true;
			break;
		}
		case '?': {

			endOfFile = true;
			break;
		}
		case '!': {

			endOfFile = true;
			break;
		}
		default:
			break;

		}
		return endOfFile;

	}

	public void setMinWordLength(int i) {
		this.minWordLength = i;

	}

	public int getMinWordLength() {
		return minWordLength;
	}

	public void setSentenceDelimiters(String del) {
		this.sentenceDelimeter = del;
		//System.out.println("SentenceDelimeter:" + sentenceDelimeter);

	}

	public String getSentenceDelimiters() {

		return sentenceDelimeter;

	}

	public void setFile(File file) {
		this.inputFile = file;
		System.out.println("inputFile " + inputFile);

	}

	public File getFile() {
		return inputFile;
	}

	public void setEndSentenceDelimeter(String endSentenceDelimeter) {
		this.endSentenceDelimeter = endSentenceDelimeter;

	}

	private String getEndSentenceDelimiter() {

		return endSentenceDelimeter;
	}

}
