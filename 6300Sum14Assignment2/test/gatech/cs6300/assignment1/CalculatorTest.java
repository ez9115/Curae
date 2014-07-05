
package gatech.cs6300.assignment1;
import static org.junit.Assert.assertEquals;

import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import org.junit.Test;

public class CalculatorTest {
	 
	@Test
	  public void testMultiply() {
		Calculator testCalculator = new Calculator();
		testCalculator.multiply(50, 50);
	    assertEquals("2500.0",  testCalculator.getResult());
	  }
	@Test
	  public void testDivide() {
		Calculator testCalculator = new Calculator();
		testCalculator.divide(50, 50);
	     assertEquals("1.0",  testCalculator.getResult());
	  }
	// Exceptional case
	 @Test(expected = IllegalArgumentException.class)
	  public void testExceptionIsThrown() {
		 Calculator testCalculator = new Calculator();
		 testCalculator.divide(1000,  0);
	  }


	@Test
	  public void testAdd() {
		Calculator testCalculator = new Calculator();
		testCalculator.add(50, 50);
	    assertEquals("100.0",  testCalculator.getResult());
	  }
	@Test
	  public void testSubtruct() {
		Calculator testCalculator = new Calculator();
		testCalculator.subtract(150, 50);
	    assertEquals("100.0",  testCalculator.getResult());
	  }
	 
}
