package Tpfahp;

import common.SimulatorParams;

public class Simulator {

	public float [][] plate;
	public int dimen;
	/**
	 * If dimen is 3, Creates a new plate like:
	 * ----------x----------->
	 * |   top    top    top    top    top
	 * y   left   0.0    0.0    0.0    right 
	 * |   left   0.0    0.0    0.0    right
	 * |   left   0.0    0.0    0.0    right
	 * V   bottom bottom bottom bottom bottom
	 * 
	 * @Note The corners do not matter as we skip the edges in the heat method.
	 * 
	 * plate[x][y]
	 * @param dimen
	 * @param top
	 * @param bottom
	 * @param left
	 * @param right
	 */
	public Simulator(int startDimen, float top, float bottom, float left, float right) {
		dimen = startDimen + 2; //need room in each dimension for the edges
		plate = new float[dimen][dimen];
		for(int y=0; y < dimen; y++) {
			for(int x=0; x < dimen; x++) {
				if(0 == y) { //This means top edge of the plate
					plate[y][x] = top; 
				}
				else if(0 == x) { // This means left edge of the plate
					plate[y][x] = left;
				}
				else if(dimen-1 == y) { // This means bottom edge of the plate
					plate[y][x] = bottom;
				}
				else if(dimen-1 == x) { // This means right edge of the plate
					plate[y][x] = right;
				}
				else { //not on an edge
					plate[y][x] = (float)0.0;
				}
			}
		}
	}
	
	/**
	 * Copies a simulator object
	 * @param simulator The simulator to copy 
	 */
	public Simulator(Simulator simulator) {
		dimen = simulator.dimen;
		plate = new float[dimen][dimen];
		for(int y=0; y < dimen; y++) {
			for(int x=0; x < dimen; x++) {
				plate[x][y] = simulator.plate[x][y];
			}
		}
		
	}
	
	/**
	 * Unpacks a SimulatorParams object constructs this Simulator.
	 * @param params a SimulatorParams object
	 */
	public Simulator(SimulatorParams params) {
		this(params.dimen, params.top.floatValue(), params.bottom.floatValue(), params.left.floatValue(), params.right.floatValue());
	}

	/**
	 * 
	 * @param max_iter The maximum number of iterations to run. This method will
	 * 	terminate if max_iter is exceeded. If <=0, this value is ignored. 
	 * @param delta If the no points in the plate change by at least this value
	 */
	public void heat(int max_iter, float delta) {
		int iterations = 0;
		boolean loop_again = true;
		while(iterations < max_iter && loop_again == true) {
			loop_again = false; //assume we will not loop again
			Simulator old_sim = new Simulator(this);
			for(int x=1; x < dimen-1; x++) { 
				for(int y=1; y < dimen-1; y++) { 
					plate[x][y] = (old_sim.plate[x + 1][y] + old_sim.plate[x - 1][y] +
                            old_sim.plate[x][y + 1] + old_sim.plate[x][y - 1]) / (float)4.0;
					if(Math.abs(plate[x][y] - old_sim.plate[x][y]) > delta)
					{
						loop_again = true;
					}
				}
			}
		}
	}
	
	/**
	 * Calls heat() with max_iter = 10000 and delta = .01
	 */
	public void heat() {
		int max_iter = 10000;
		float delta = (float).01;
		heat(max_iter, delta);
	}
	
	/**
	 * Returns a string representation of the plate, not including the edges.
	 */
	public String toString(){
		String as_string = "";
		
		// do not include edges of plate in loop
		for(int x=1; x < dimen-1; x++) { 
			for(int y=1; y < dimen-1; y++) { 
				if( 1 != y) { // if this is not the first print for the row...
					as_string += '\t'; // add a tab before the number
				}
				as_string += String.format("%2.2f", plate[x][y]);
			}
			as_string += '\n';
		}
		return as_string;
	}
}
