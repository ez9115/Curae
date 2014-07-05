package gatech.cs6300.assignment1;

public class Calculator implements CalculatorInterface {
	static double calcResult= 0;
	@Override
	public void add(double x, double y) throws NumberFormatException {
		double result = x+y;
		calcResult = result;
		System.out.println ("Add Result="+getResult());
	}

	@Override
	public void subtract(double x, double y) throws NumberFormatException {
		double result = x-y;
		calcResult = result;
		System.out.println ("Substruct Result="+getResult());

	}

	@Override
	public void multiply(double x, double y) throws NumberFormatException {
		double result = x*y;
		calcResult=result;
		System.out.println ("Multiplication Result="+getResult());

	}

	@Override
	public void divide(double x, double y) throws NumberFormatException {
		double result = 0;
		 
			if (y == 0){
		    System.out.println("0 is not valid argument, division result will be infinity");
		    throw new IllegalArgumentException("y  should be different from  '0' ");
		}
		result= x/y;
		calcResult=result;
		System.out.println ("Division Result="+getResult());

	}

	@Override
	public double getResult() {
		// TODO Auto-generated method stub
		//return 0;
		return calcResult;
	}

	/**
	 Instructions do not indicate that main method is not need , I decided to include it 
	 **/
	public static void main(String[] args) {
		
          Calculator calculator = new Calculator();
		 
		  String operator ="";
		  double number1 = 0.0;
		  double number2 = 0.0;
		  try {
		  operator = args[0];
		  number1 =  Double.parseDouble(args[1]);
		  number2 =  Double.parseDouble(args[2]);
		  }catch (ArrayIndexOutOfBoundsException e){
			  System.out.println("Please add Arguments in Run as :example '* 3 4 ' ");
			  
		  }
  
		  switch (operator) {
		   
          case "+":calculator.add(number1, number2);
                    break;
          case "-": calculator.subtract(number1, number2);
                    break;
          case "*": calculator.multiply(number1, number2);
                    break;
          case "/": calculator.divide(number1, number2);
                    break;
           
          // case "^" :calculator.power(inputA, inputB);
                    //break;*/
           default:
        	   break;
		  }
		 
		System.out.println( "Operator= " +args[0]);
		System.out.println( "Result for " +args[0] +
				" and Number1=" +number1+ ", Number2=" +number2
				+" = " + calcResult );


	}

}