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

    // Node object with WHITE colour
    public Node(int row, int col) {
	this.setBounds(row * NODE_SIZE, col * NODE_SIZE, NODE_SIZE, NODE_SIZE);
	this.setOpaque(true);
	this.addMouseListener(this);
	this.setBorder(new MatteBorder(1, 0, 0, 1, Color.BLACK));
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

    // Make Node MAGENTA, meaning it's the solved path
    public void makePath() {
	colour = Color.MAGENTA;
	this.setBackground(Color.MAGENTA);
    }

    /****** CHANGE COLOUR OF NODE ON SPECIFIC MOUSE CLICKS ******/
    @Override
    public void mouseClicked(MouseEvent e) {
	// When mouse has been pressed and released
    }

    @Override
    public void mousePressed(MouseEvent e) {
	// When mouse has just been pressed
	if (!GridPanel.startPointPlaced) { // Place start point FIRST
	    this.makeStart();
	    GridPanel.startPointPlaced = true;
	} else if (!GridPanel.endPointPlaced && this.colour != Color.ORANGE) { // Place end point SECOND
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
