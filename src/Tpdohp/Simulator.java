package Tpdohp;

import sun.org.mozilla.javascript.internal.Node;
import common.SimulatorParams;
import Tpdohp.PlateNode;

public class Simulator {

	public PlateNode top_left;
	public int dimen;
	/**
	 * If dimen is 3, Creates a new plate like: 
	 * (t_l = top_left and has value 'top')
	 * 
	 *    null - null - null - null - null - null  - null
	 *    null - t_l  -  top - top  - top  - right - null
	 *    null - left -  0.0 - 0.0  - 0.0  - right - null
	 *    null - left -  0.0 - 0.0  - 0.0  - right - null
	 *    null - left -  0.0 - 0.0  - 0.0  - right - null
	 *    null - bot  -  bot - bot  - bot  - bot   - null
	 * 
	 * @Note The corners do not matter as we skip the edges in the heat method.
	 * @Note bottom nodes and right nodes serve as sentinels, and as such are
	 *   tracked.
	 * @param dimen
	 * @param top
	 * @param bottom
	 * @param left
	 * @param right
	 */
	public Simulator(int startDimen, double top, double bottom, double left, double right) {
		dimen = startDimen + 2; //need room in each dimension for the edges
		PlateNode leftmost = create_node_top_or_bot_row(top, false);
		PlateNode above = null;
		top_left = leftmost;
		for(int i=1; i < dimen-1; i++){
			above = leftmost;
			leftmost = create_node_mid_row(left, right);
			stitch_rows(above, leftmost);	
		}
		above = leftmost;
		leftmost = create_node_top_or_bot_row(bottom, true); //create the last row
		stitch_rows(above, leftmost);
	}
	
	private PlateNode create_node_top_or_bot_row(double temp, boolean is_bot) {
		PlateNode leftmost = new PlateNode(temp, false, is_bot);
		PlateNode current = leftmost;
		for(int i=0; i < dimen-2; i++) //iterate to the second to last node.
		{
			current.right = new PlateNode(current, temp, false, is_bot);
			current = current.right;
		}
		current.right = new PlateNode(current, temp, true, is_bot); //make the right edge node
		return leftmost;
	}
	
	private PlateNode create_node_mid_row(double temp_left, double temp_right) {
		PlateNode leftmost = new PlateNode(temp_left, false, false);
		PlateNode current = leftmost;
		for(int i=0; i < dimen-2; i++) //iterate to the second to last node.
		{
			current.right = new PlateNode(current, 0.0, false, false);
			current = current.right;
		}
		current.right = new PlateNode(current, temp_right, true, false); //make the right edge node
		return leftmost;
	}
	
	private void stitch_rows(PlateNode above, PlateNode below) {
		for(int i=0; i < dimen; i++) {
			above.below = below;
			below.above = above;
			above = above.right;
			below = below.right;
		}
	}
	
	/**
	 * Copies a simulator object
	 * @param simulator The simulator to copy 
	 */
	public Simulator(Simulator simulator) {

	}
	
	/**
	 * Unpacks a SimulatorParams object constructs this Simulator.
	 * @param params a SimulatorParams object
	 */
	public Simulator(SimulatorParams params) {
		this(params.dimen, params.top, params.bottom, params.left, params.right);
	}

	/**
	 * 
	 * @param max_iter The maximum number of iterations to run. This method will
	 * 	terminate if max_iter is exceeded. If <=0, this value is ignored. 
	 * @param delta If the no points in the plate change by at least this value
	 */
	public void heat(int max_iter, double delta) {
		int iterations = 0;
		boolean loop_again = true;
		while(iterations < max_iter && loop_again == true) {
			loop_again = false;
			PlateNodeIter iter = top_left.iterator();
			while(iter.hasNext()) {
				PlateNode node = iter.next();
				node.value = (node.above.old_value + node.below.old_value + node.left.old_value + node.right.old_value) / 4.0;
				if(Math.abs(node.value - node.old_value) > delta) {
					loop_again = true;
				}
			}
			PlateNodeIter iter2 = top_left.iterator();
			while(iter2.hasNext()) {
				PlateNode node = iter2.next();
				node.old_value = node.value;
			}
		}
	}
	
	/**
	 * Calls heat() with max_iter = 10000 and delta = .01
	 */
	public void heat() {
		int max_iter = 10000;
		double delta = .01;
		heat(max_iter, delta);
	}
	
	/**
	 * Returns a string representation of the plate, not including the edges.
	 */
	public String toString(){
		String as_string = "";
		PlateNodeIter iter = top_left.iterator();
		
		while(iter.hasNext()) {
			PlateNode node = iter.next();
			as_string += String.format("%2.2f", node.value);
			if(!node.right.is_right_edge) {
				as_string += '\t';
			}
			else {
				as_string += '\n';
			}
		}
		
		return as_string;
	}
}
