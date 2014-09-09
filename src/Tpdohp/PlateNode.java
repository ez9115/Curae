package Tpdohp;

import java.util.Iterator;
import Tpdohp.PlateNodeIter;
/**
 * The most basic of
 * @author Anthony
 *
 */
public class PlateNode implements Iterable {
	PlateNode above;
	PlateNode below;
	PlateNode left;
	PlateNode right;
	double value;
	double old_value; //used in transition instead of copying whole plate again
	boolean is_bottom_edge;
	boolean is_right_edge;
	
	/**
	 * Builds a PlateNode with no neighbors 
	 * @param value The temperature value at this node
	 * @param is_right_edge Marks whether this node is a right edge or not
	 * @param is_bottom_edge Marks whether this node is a bottom edge or not
	 */
	PlateNode(double value, boolean is_right_edge, boolean is_bottom_edge) {
		this.value = value;
		this.old_value = value;
		this.is_right_edge = is_right_edge;
		this.is_bottom_edge = is_bottom_edge;
	}	
	
	/**
	 * For the top row, we can only set what's to the left 
	 * @param left PlateNode to the left.
	 * @param value The temperature value at this node
	 * @param is_right_edge Marks whether this node is an edge or not
	 * @param is_bottom_edge Marks whether this node is a bottom edge or not
	 */
	PlateNode(PlateNode left, double value, boolean is_right_edge, boolean is_bottom_edge) {
		this(value, is_right_edge, is_bottom_edge);
		this.left = left;
	}

	/**
	 * After the top row, we can set the node above and to the left at once.
	 * @param above PlateNode above.
	 * @param left PlateNode to the left.
	 * @param value The temperature value at this node
	 * @param is_right_edge Marks whether this node is an edge or not
	 * @param is_bottom_edge Marks whether this node is a bottom edge or not
	 */
	PlateNode(PlateNode above, PlateNode left, double value, boolean is_right_edge, boolean is_bottom_edge) {
		this(left, value, is_right_edge, is_bottom_edge);
		this.above = above;
	}

	@Override
	public PlateNodeIter iterator() {
		PlateNodeIter iter = new PlateNodeIter(this);
		return iter;
	}
}
