/**
 * 
 */
package Tpdahp;
import Tpdahp.Simulator;
import common.SimulatorParams;
/**
 * @author Anthony
 *
 */
public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimulatorParams params = new SimulatorParams(args);
		Simulator sim = new Simulator(params);
		sim.heat();
		System.out.print(sim.toString());
	}

}
