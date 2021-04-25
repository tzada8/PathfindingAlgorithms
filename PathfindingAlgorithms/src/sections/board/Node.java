package sections.board;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

/**
 * 
 * The following Node class extends JLabel and implements a MouseListener acting
 * as a box for each individual cell / tile. Each Node has a specific row,
 * column, and colour, as well as g, h, and f cost values for the A* algorithm.
 * 
 * The MouseListener allows the Nodes to be clicked on in order to change it
 * colour for the different specifications.
 * 
 * Nodes can either be open (WHITE), a barrier (BLACK), a start point (ORANGE),
 * an end point (CYAN), a closed path (RED), an open path (GREEN), or the
 * solution (MAGENTA).
 * 
 * @author Troy Zada
 *
 */

public class Node extends JLabel implements MouseListener {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // Constants
    public static final int NODE_SIZE = 14;

    // Fields
    private int row;
    private int col;
    private Color colour;
    private double gCost, hCost, fCost = Double.MAX_VALUE;

    /**
     * This constructor creates a Node object at the specified row and column,
     * adding a border and initializing it as WHITE.
     * 
     * @param row - The row this Node sits at.
     * @param col - The column this Node sits at.
     */
    public Node(int row, int col) {
	this.setBounds(col * NODE_SIZE, row * NODE_SIZE, NODE_SIZE, NODE_SIZE);
	this.setOpaque(true);
	this.addMouseListener(this);

	// Add border to Nodes, parameter order is: TOP, LEFT, BOTTOM, RIGHT, COLOUR
	if (row == 0 && col == 0) { // Top-left corner
	    this.setBorder(new MatteBorder(2, 2, 0, 1, Color.BLACK));
	} else if (row == 0 && col == GridPanel.COLUMNS - 1) { // Top-right corner
	    this.setBorder(new MatteBorder(2, 0, 0, 2, Color.BLACK));
	} else if (row == GridPanel.ROWS - 1 && col == 0) { // Bottom-left corner
	    this.setBorder(new MatteBorder(1, 2, 2, 1, Color.BLACK));
	} else if (row == GridPanel.ROWS - 1 && col == GridPanel.COLUMNS - 1) { // Bottom-right corner
	    this.setBorder(new MatteBorder(1, 0, 2, 2, Color.BLACK));
	} else if (col == 0) { // Left-most side
	    this.setBorder(new MatteBorder(1, 2, 0, 1, Color.BLACK));
	} else if (col == GridPanel.COLUMNS - 1) { // Right-most side
	    this.setBorder(new MatteBorder(1, 0, 0, 2, Color.BLACK));
	} else if (row == 0) { // Top-most side
	    this.setBorder(new MatteBorder(2, 0, 0, 1, Color.BLACK));
	} else if (row == GridPanel.ROWS - 1) { // Bottom-most side
	    this.setBorder(new MatteBorder(1, 0, 2, 1, Color.BLACK));
	} else {
	    this.setBorder(new MatteBorder(1, 0, 0, 1, Color.BLACK));
	}
	this.row = row;
	this.col = col;
	this.makeAvailable();
    }

    /**
     * Getter method to get the position of this Node.
     * 
     * @return - This Node's position, being row and col.
     */
    public int[] getPosition() {
	int[] pos = { this.row, this.col };
	return pos;
    }

    /**
     * Getter method to get the colour of this Node.
     * 
     * @return - This Node's colour.
     */
    public Color getColour() {
	return colour;
    }

    /**
     * Determines if 2 Nodes are equal to one another, meaning same position (row
     * and col) and colour.
     * 
     * @param other - Node being compared to this.
     * @return - True / False stating if this and other Nodes are equal or not.
     */
    public boolean equals(Node other) {
	return this.row == other.row && this.col == other.col && this.colour.equals(other.colour);
    }

    /* FOR CREATING THE MAP */
    /**
     * If Node's colour is WHITE, then it's available.
     * 
     * @return - If Node is available.
     */
    public boolean isAvailable() {
	return colour == Color.WHITE;
    }

    /**
     * Makes this Node available by changing its colour to WHITE.
     */
    public void makeAvailable() {
	colour = Color.WHITE;
	this.setBackground(Color.WHITE);
    }

    /**
     * If Node's colour is BLACK, then it's a barrier.
     * 
     * @return - If Node is a barrier.
     */
    public boolean isBarrier() {
	return colour == Color.BLACK;
    }

    /**
     * Makes this Node a barrier by changing its colour to BLACK.
     */
    public void makeBarrier() {
	colour = Color.BLACK;
	this.setBackground(Color.BLACK);
    }

    /**
     * If Node's colour is ORANGE, then it's start point.
     * 
     * @return - If Node is start point.
     */
    public boolean isStart() {
	return colour == Color.ORANGE;
    }

    /**
     * Makes this Node a start point by changing its colour to ORANGE.
     */
    public void makeStart() {
	colour = Color.ORANGE;
	this.setBackground(Color.ORANGE);
    }

    /**
     * If Node's colour is CYAN, then it's end point.
     * 
     * @return - If Node is end point.
     */
    public boolean isEnd() {
	return colour == Color.CYAN;
    }

    /**
     * Makes this Node an end point by changing its colour to CYAN.
     */
    public void makeEnd() {
	colour = Color.CYAN;
	this.setBackground(Color.CYAN);
    }
    /* FOR CREATING THE MAP */

    /* FOR PATHFINDING THROUGH THE MAP */
    /**
     * If Node's colour is RED, then it has been checked.
     * 
     * @return - If Node has already been checked.
     */
    public boolean isClosed() {
	return colour == Color.RED;
    }

    /**
     * Makes this Node closed by changing its colour to RED.
     */
    public void makeClosed() {
	colour = Color.RED;
	this.setBackground(Color.RED);
    }

    /**
     * If Node's colour is GREEN, then it can be checked.
     * 
     * @return - If Node is available to be checked.
     */
    public boolean isOpen() {
	return colour == Color.GREEN;
    }

    /**
     * Makes this Node open by changing its colour to GREEN.
     */
    public void makeOpen() {
	colour = Color.GREEN;
	this.setBackground(Color.GREEN);
    }

    /**
     * If Node's colour is MAGENTA, then it is part of solved path.
     * 
     * @return - If Node is part of solution path.
     */
    public boolean isPath() {
	return colour == Color.MAGENTA;
    }

    /**
     * Makes this Node part of solution path by changing its colour to MAGENTA.
     */
    public void makePath() {
	colour = Color.MAGENTA;
	this.setBackground(Color.MAGENTA);
    }
    /* FOR PATHFINDING THROUGH THE MAP */

    /* FOR A* ALGORITHM FOR G, H, AND F COSTS */
    /**
     * Resets all costs of this Node back to default value of the double max value.
     */
    public void resetCosts() {
	gCost = Double.MAX_VALUE;
	hCost = Double.MAX_VALUE;
	fCost = Double.MAX_VALUE;
    }

    /**
     * Getter method to get g cost value.
     * 
     * @return - current g cost.
     */
    public double getGCost() {
	return gCost;
    }

    /**
     * Getter method to get h cost value.
     * 
     * @return - current h cost.
     */
    public double getHCost() {
	return hCost;
    }

    /**
     * Getter method to get f cost value.
     * 
     * @return - current f cost.
     */
    public double getFCost() {
	return fCost;
    }

    /**
     * Sets this Node's g cost to be the parameter value.
     * 
     * @param g - Updated g cost value.
     */
    public void setGCost(double g) {
	gCost = g;
    }

    /**
     * Sets this Node's h cost to be the parameter value.
     * 
     * @param g - Updated h cost value.
     */
    public void setHCost(double h) {
	hCost = h;
    }

    /**
     * Sets this Node's f cost to be the parameter value.
     * 
     * @param g - Updated f cost value.
     */
    public void setFCost(double f) {
	fCost = f;
    }
    /* FOR A* ALGORITHM FOR G, H, AND F COSTS */

    /* ON MOUSECLICKS CHANGE THIS NODE'S COLOUR */
    @Override
    public void mouseClicked(MouseEvent e) {
	// When mouse has been pressed and released
    }

    /**
     * If a start point hasn't been placed yet, then place that first, following an
     * end point. After both start and end points have been placed, then all other
     * clicks will either create a barrier / make a Node available.
     */
    @Override
    public void mousePressed(MouseEvent e) {
	// When mouse has just been pressed
	if (GridPanel.clickable) {
	    if (!GridPanel.startPointPlaced) { // Place start point FIRST
		this.makeStart();
		GridPanel.startPointPlaced = true;
	    } else if (!GridPanel.endPointPlaced && !this.isStart()) { // Place end point SECOND
		this.makeEnd();
		GridPanel.endPointPlaced = true;
	    } else { // Place all other barriers
		if (this.isAvailable()) {
		    this.makeBarrier();
		} else if (isBarrier()) {
		    this.makeAvailable();
		}
	    }
	}
    }

    @Override
    public void mouseReleased(MouseEvent e) {
	// When mouse has just been released
    }

    @Override
    public void mouseEntered(MouseEvent e) {
	// When mouse hovers over object

    }

    @Override
    public void mouseExited(MouseEvent e) {
	// When mouse leaves object
    }
    /* ON MOUSECLICKS CHANGE THIS NODE'S COLOUR */

}
