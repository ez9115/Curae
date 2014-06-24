package gatech.cs6300.project1;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AverageSentenceLengthTest {

    private AverageSentenceLength asl;
    private String fileDir;

    @Before
    public void setUp() throws Exception {
        asl = new AverageSentenceLength();
        fileDir = new String("test" + File.separator + "inputfiles"
                + File.separator);
    }

    @After
    public void tearDown() throws Exception {
        asl = null;
        fileDir = null;
    }
    
    @Test
    public void testComputeAverageSentenceLength1() {
        asl.setFile(new File(fileDir + "multi.txt"));
        assertEquals(6, asl.computeAverageSentenceLength(), 0);
    } // Successful padded test  'it.' is meeting default minimum word length criteria
     
    
    @Test
    public void testComputeAverageSentenceLength2() {
        asl.setFile(new File(fileDir + "file.txt"));
        assertEquals(1, asl.computeAverageSentenceLength(), 0);
    } //Successful even there is no end sentence punctuation
    // file without ending punctuation considered as one sentence

    @Test
    public void testComputeAverageSentenceLength3() {
        asl.setFile(new File(fileDir + "essay.txt"));
        assertEquals(10, asl.computeAverageSentenceLength(), 0);
    } // Successful

    @Test
    public void testComputeAverageSentenceLength4() {
        asl.setFile(new File(fileDir + "essay.txt"));
        asl.setMinWordLength(5);
        assertEquals(4, asl.computeAverageSentenceLength(), 0);
    }//Failed as 217/43 = 5, not 4 cats. , fear. is considered length 4 , not 5 
   
    
    
    @Test
    public void testComputeAverageSentenceLength5() {
        asl.setFile(new File(fileDir + "numbers.txt"));
        asl.setSentenceDelimiters("/|");
        asl.setMinWordLength(1);
        assertEquals(5, asl.computeAverageSentenceLength(), 0);
    }//failed because parsed into 10 units, even there is no ending sentence  punctuation 
    //considered as one sentence
     
    @Test
    public void testComputeAverageSentenceLength6() {
        asl.setFile(new File(fileDir + "essay.txt"));
        asl.setSentenceDelimiters(" &/");
        asl.setMinWordLength(5);
        assertEquals(3, asl.computeAverageSentenceLength(), 0);
    }//Failed , actual 5 = 217/43
    @Test
    public void testComputeAverageSentenceLength7() {
        asl.setFile(new File(fileDir + "multi.txt"));
        asl.setMinWordLength(5);
        assertEquals(3, asl.computeAverageSentenceLength(), 0);
    } //Successful  run 
     
    @Test
    public void testComputeAverageSentenceLength8() {
        asl.setFile(new File(fileDir + "essay.txt"));
        asl.setMinWordLength(6);
        assertEquals(3, asl.computeAverageSentenceLength(), 0);
    }//Successful 148/43 average 3 with min length = 6 
    @Test
    public void testComputeAverageSentenceLength9() {
        asl.setFile(new File(fileDir + "numbers.txt"));
        asl.setSentenceDelimiters("/|");
        asl.setMinWordLength(1);
        assertEquals(10, asl.computeAverageSentenceLength(), 0);
    }//Successful because parsed into 10 units, even there is no ending sentence  punctuation 
    //considered as one sentence
      
    @Test
    public void testComputeAverageSentenceLength10() {
        asl.setFile(new File(fileDir + "numbers.txt"));
        asl.setSentenceDelimiters("|");
        asl.setMinWordLength(1);
        assertEquals(4, asl.computeAverageSentenceLength(), 0);
    } // Successful ,
    @Test
    public void testComputeAverageSentenceLength11() {
        asl.setFile(new File(fileDir + "essay.txt"));
        asl.setSentenceDelimiters("%");//which is default
        assertEquals(10, asl.computeAverageSentenceLength(), 0);
    }// Successful as in requirements we define if delimiter not found in file 
    // use default delimiter " " 10 = 458 /43
    @Test
    public void testComputeAverageSentenceLength12() {
        asl.setFile(new File(fileDir + "numbers.txt"));
        asl.setSentenceDelimiters("/|");
        asl.setMinWordLength(4);
        assertEquals(8, asl.computeAverageSentenceLength(), 0);
    } // Successful ,
    
}
