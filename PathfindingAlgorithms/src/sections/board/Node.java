package sections.board;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

public class Node extends JLabel implements MouseListener {
    /*
     * Nodes can either be open (WHITE), an obstacle (BLACK), a start point
     * (ORANGE), an end point (CYAN), a closed path (RED), an open path (GREEN), or
     * the solution (MAGENTA)
     */

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
    private int gCost, hCost, fCost = Integer.MAX_VALUE;

    // Node object with WHITE colour
    public Node(int row, int col) {
	this.setBounds(col * NODE_SIZE, row * NODE_SIZE, NODE_SIZE, NODE_SIZE);
	this.setOpaque(true);
	this.addMouseListener(this);

	// Add border to Nodes --> (TOP, LEFT, BOTTOM, RIGHT, COLOUR)
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

    // Get position of this Node
    public int[] getPosition() {
	int[] pos = { this.row, this.col };
	return pos;
    }

    // Get colour of Node
    public Color getColour() {
	return colour;
    }

    // Checks if 2 Nodes are equal, meaning same position and colour
    public boolean equals(Node other) {
	return this.row == other.row && this.col == other.col && this.colour.equals(other.colour);
    }

    /****** FOR CREATING MAP ******/
    // Colour is WHITE, meaning it's available
    public boolean isAvailable() {
	return colour == Color.WHITE;
    }

    // Make Node available (WHITE)
    public void makeAvailable() {
	colour = Color.WHITE;
	this.setBackground(Color.WHITE);
    }

    // Colour is BLACK, meaning it's an obstacle/barrier
    public boolean isBarrier() {
	return colour == Color.BLACK;
    }

    // Make Node an obstacle/barrier (BLACK)
    public void makeBarrier() {
	colour = Color.BLACK;
	this.setBackground(Color.BLACK);
    }

    // Colour is ORANGE, meaning it's start point
    public boolean isStart() {
	return colour == Color.ORANGE;
    }

    // Make Node start point (ORANGE)
    public void makeStart() {
	colour = Color.ORANGE;
	this.setBackground(Color.ORANGE);
    }

    // Colour is CYAN, meaning it's end point
    public boolean isEnd() {
	return colour == Color.CYAN;
    }

    // Make Node end point (CYAN)
    public void makeEnd() {
	colour = Color.CYAN;
	this.setBackground(Color.CYAN);
    }

    /****** FOR PATHFINDING ******/
    // Colour is RED, meaning it has been checked
    public boolean isClosed() {
	return colour == Color.RED;
    }

    // Make Node checked (RED)
    public void makeClosed() {
	colour = Color.RED;
	this.setBackground(Color.RED);
    }

    // Colour is GREEN, meaning it's available to be checked
    public boolean isOpen() {
	return colour == Color.GREEN;
    }

    // Make Node open to be checked (GREEN)
    public void makeOpen() {
	colour = Color.GREEN;
	this.setBackground(Color.GREEN);
    }

    // Colour is MAGENTA, meaning it's part of solved path
    public boolean isPath() {
	return colour == Color.MAGENTA;
    }

    // Make Node MAGENTA, meaning it's the solved path
    public void makePath() {
	colour = Color.MAGENTA;
	this.setBackground(Color.MAGENTA);
    }

    /****** FOR A* ALGORITHM TO UPDATE G, H, AND F COSTS ******/
    // Resets all costs
    public void resetCosts() {
	gCost = Integer.MAX_VALUE;
	hCost = Integer.MAX_VALUE;
	fCost = Integer.MAX_VALUE;
    }

    public int getGCost() {
	return gCost;
    }

    public int getHCost() {
	return hCost;
    }

    public int getFCost() {
	return fCost;
    }

    public void setGCost(int g) {
	gCost = g;
    }

    public void setHCost(int h) {
	hCost = h;
    }

    public void setFCost(int f) {
	fCost = f;
    }

    /****** CHANGE COLOUR OF NODE ON SPECIFIC MOUSE CLICKS ******/
    @Override
    public void mouseClicked(MouseEvent e) {
	// When mouse has been pressed and released
    }

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
	    if (e.getButton() == MouseEvent.BUTTON1) {
		// if left click pressed
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

}
