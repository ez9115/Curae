package common;


/**
 * This class parses the command line. Normally I would use a library for such 
 * a boilerplate piece of code, but alas we are not allowed to. Its as 
 * bulletproof as I could make it. Could use a robust test suite but I think
 * that would be a little beyond the scope of the assignment.
 * @author Anthony Lozano
 *
 */
public class SimulatorParams {
	public Double top;
	public Double bottom;
	public Double left;
	public Double right;
	public int dimen;
	
	/**
	 * Default constructor, just passes parameters into the class.
	 * @param top Temperature for top. [0.0, 100.0].
	 * @param bottom Temperature for top. [0.0, 100.0].
	 * @param left Temperature for top. [0.0, 100.0].
	 * @param right Temperature for top. [0.0, 100.0].
	 * @param dimen How large a plate should be, excluding the edges. must be greater than 1.
	 */
	public SimulatorParams(Double top, Double bottom, Double left,
			Double right, int dimen) {
		super();
		this.top = top;
		this.bottom = bottom;
		this.left = left;
		this.right = right;
		this.dimen = dimen;
	}

	/**
	 * Takes a command line string and parses it into doubles.
	 * @note see usage string for exact format required.
	 * @note will exit the program if it cannot parse the usage String. 
	 * @param arg_string A string that matches usage string.
	 */
	public SimulatorParams(String[] arg_string) {		
		// These track if we have found the variable so that we don't override
		// the variable if we find it again.
		boolean d_found = false;
		boolean t_found = false;
		boolean b_found = false;
		boolean l_found = false;
		boolean r_found = false;
		
		for(int i=0; i < arg_string.length; i++) {
			String dummy = arg_string[i];
			switch (arg_string[i]) {
			case "-d": {
				try {
					if(d_found) throw new IllegalArgumentException("can't assign more than once");
					d_found = true;
					dimen = Integer.parseInt(arg_string[++i]);
					check_dimension(dimen);
				}
				catch (NumberFormatException e){
					shutdown_parser("Dimension(-d) must be an integer.");
				}
				catch (IllegalArgumentException e){
					shutdown_parser("Dimension(-d): " + e.getMessage());
				}
				catch (ArrayIndexOutOfBoundsException e){
					shutdown_parser("Dimen missing value.");
				}
				break;
			}
			case "-l":
				try {
					if(l_found) throw new IllegalArgumentException("can't assign more than once");
					l_found = true;
					left = Double.parseDouble(arg_string[++i]);
					this.check_temp_range(left);
				}
				catch (NumberFormatException e){
					shutdown_parser("Couldn't parse Left (-l), must be between 0.0 and 100.0, inclusive.");
				}
				catch (IllegalArgumentException e){
					shutdown_parser("Left (-l): " + e.getMessage());
				}
				catch (ArrayIndexOutOfBoundsException e){
					shutdown_parser("Left (-l) missing value.");
				}
				break;
				
			case "-r":
				try {
					if(r_found) throw new IllegalArgumentException("can't assign more than once");
					r_found = true;
					right = Double.parseDouble(arg_string[++i]);
					this.check_temp_range(right);
				}
				catch (NumberFormatException e){
					shutdown_parser("Couldn't parse Right (-r), must be between 0.0 and 100.0, inclusive.");
				}
				catch (IllegalArgumentException e){
					shutdown_parser("Right (-t): " + e.getMessage());
				}
				catch (ArrayIndexOutOfBoundsException e){
					shutdown_parser("Right (-r) missing value.");
				}
				break;
				
			case "-t":
				try {
					if(t_found) throw new IllegalArgumentException("can't assign more than once");
					t_found = true;
					top = Double.parseDouble(arg_string[++i]);
					this.check_temp_range(top);
				}
				catch (NumberFormatException e){
					shutdown_parser("Couldn't parse Top (-t), must be between 0.0 and 100.0, inclusive.");
				}
				catch (IllegalArgumentException e){
					shutdown_parser("Top (-t): " + e.getMessage());
				}
				catch (ArrayIndexOutOfBoundsException e){
					shutdown_parser("Top (-t) missing value.");
				}
				break;
				
			case "-b":
				try {
					if(b_found) throw new IllegalArgumentException("can't assign more than once");
					b_found = true;
					bottom = Double.parseDouble(arg_string[++i]);
					this.check_temp_range(bottom);
				}
				catch (NumberFormatException e){
					shutdown_parser("Couldn't parse Bottom (-b), must be between 0.0 and 100.0, inclusive.");
				}
				catch (IllegalArgumentException e){
					shutdown_parser("Bottom (-b): " + e.getMessage() );
				}
				catch (ArrayIndexOutOfBoundsException e){
					shutdown_parser("Bottom (-b) missing value.");
				}
				break;
				
			default:
				shutdown_parser("Invalid parameter " + arg_string[i]);
			}
		} //end loop

		if (!d_found) {
			shutdown_parser("Missing -d parameter");
		}
		if (!b_found) {
			shutdown_parser("Missing -b parameter");
		}
		if (!t_found) {
			shutdown_parser("Missing -t parameter");
		}
		if (!l_found) {
			shutdown_parser("Missing -l parameter");
		}
		if (!r_found) {
			shutdown_parser("Missing -b parameter");
		}
		
	}

	private void shutdown_parser(String error_message) {
		System.out.println(error_message);
		usage();
		System.exit(0);
	}
	
	private void check_temp_range(Double temp) {
		if(temp < 0.0 || temp > 100) {
			throw new IllegalArgumentException("Tempurature was " + temp.toString() + " but must be between 0.0 and 100.0, inclusive.");
		}
	}
	
	private void check_dimension(int dimen) {
		if(dimen < 1) {
			throw new IllegalArgumentException("Dimension for plate must be integer greater than 1, but was " + dimen);
		}
	}
	
	private void usage() {
		StackTraceElement[] stack = Thread.currentThread ().getStackTrace ();
	    StackTraceElement main = stack[stack.length - 1];
	    String mainClass = main.getClassName ();
		System.out.println("Usage: " + mainClass + " -d number -l temp -r temp -t temp -b temp");
		System.out.println();
		System.out.println("number must be a positive integer.");
		System.out.println("temp must be in the range 0.0 to 100.0, inclusive");

	}
}
