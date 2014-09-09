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
	 *    null - null - null - null - null  - null
	 *    null - t_l  -  top - top  - right   - null
	 *    null - left -  0.0 - 0.0  - right - null
	 *    null - left -  0.0 - 0.0  - right - null
	 *    null - left -  0.0 - 0.0  - right - null
	 *    null - bot  -  bot - bot  - bot   - null
	 * 
	 * @Note The corners do not matter as we skip the edges in the heat method.
	 * @param dimen
	 * @param top
	 * @param bottom
	 * @param left
	 * @param right
	 */
	public Simulator(int startDimen, double top, double bottom, double left, double right) {
		dimen = startDimen + 2; //need room in each dimension for the edges
		int y = 0;
		int x = 0;
		top_left = new PlateNode(0.0, false, false);
		PlateNode current = top_left;
		PlateNode currentRow = top_left;
		PlateNode above_row = null;
		boolean bottom_edge = false;
		
		while(y < dimen-1)
		{
			boolean right_edge;
			if(x >= dimen-2){
				right_edge = true;
			}
			else{
				right_edge = false;
			}

			if(current.is_right_edge && current.is_bottom_edge)
			{
				current.value = bottom; //we need to amend the value to bottom.
				y++; //terminate the loop
			}
			else if(current.is_right_edge){// we have moved all the way to the right and need to move to the next row
				current.value = right; //the current node is rightmost, so we need to amend its value
				
				// reset x and increment y
				x = 0;
				y++; // increment y and check if we are on the bottom row yet.
				if(y == dimen-1){
					bottom_edge = true;
				}
				// create a new node below the current leftmost node
				currentRow.below = new PlateNode(currentRow, null, left, false, bottom_edge);
				
				// move the iterators to this new node 
				current = currentRow.below;
				currentRow = currentRow.below;
				
				// move above_row above this the node above this node
				above_row = current.above;
			}
			else if(y == 0)
			{
				current.right = new PlateNode(current, top, right_edge, bottom_edge);
				// move on to the right
				current = current.right;
				x++; 
			}
			else if(current.is_bottom_edge){
				
				current.right = new PlateNode(above_row, current, bottom, right_edge, bottom_edge);//current, bottom, right_edge, true);
				
				
				// move on to the right
				current = current.right;
				above_row.below = current;
			
				above_row = above_row.right;
				x++;
			}
			else { //somewhere in the left of the plate... continue right
				double t = 0.0;
				if(x == 0) {
					t = left;
				}
				current.right = new PlateNode(above_row, current, t, right_edge, bottom_edge);
				
				// move on to the right
				above_row = above_row.right;
				above_row.below = current;
				current = current.right;
				x++;
			}
			
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
