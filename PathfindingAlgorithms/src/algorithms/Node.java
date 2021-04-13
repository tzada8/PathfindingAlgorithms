package algorithms;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

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

    public Node(int x, int y, int row, int col) {
	this.setBounds(x + row * NODE_SIZE, y + col * NODE_SIZE, NODE_SIZE, NODE_SIZE);
	this.setBackground(Color.WHITE);
	this.setOpaque(true);
	this.setBorder(new MatteBorder(1, 0, 0, 1, Color.BLACK));
	this.addMouseListener(this);
	this.row = row;
	this.col = col;
	this.colour = Color.WHITE;
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

    // Colour is BLACK, meaning it's an obstacle/barrier
    public boolean isBarrier() {
	return colour == Color.BLACK;
    }

    // Colour is ORANGE, meaning it's start point
    public boolean isStart() {
	return colour == Color.ORANGE;
    }

    // Colour is CYAN, meaning it's end point
    public boolean isFinish() {
	return colour == Color.CYAN;
    }

    /****** FOR PATHFINDING ******/
    // Colour is RED, meaning it's has been checked
    public boolean isClosed() {
	return colour == Color.RED;
    }

    // Colour is GREEN, meaning it's available
    public boolean isOpen() {
	return colour == Color.GREEN;
    }

    // Make Node WHITE, meaning it's available
    public void makeAvailable() {
	colour = Color.WHITE;
	this.setBackground(Color.WHITE);
    }

    // Make Node BLACK, meaning it's an obstacle/barrier
    public void makeBarrier() {
	colour = Color.BLACK;
	this.setBackground(Color.BLACK);
    }

    // Make Node ORANGE, meaning it's start point
    public void makeStart() {
	colour = Color.ORANGE;
	this.setBackground(Color.ORANGE);
    }

    // Make Node CYAN, meaning it's end point
    public void makeFinish() {
	colour = Color.CYAN;
	this.setBackground(Color.CYAN);
    }

    /****** FOR PATHFINDING ******/
    // Make Node RED, meaning it's been checked
    public void makeClosed() {
	colour = Color.RED;
	this.setBackground(Color.RED);
    }

    // Make Node GREEN, meaning it's open for checking
    public void makeOpen() {
	colour = Color.GREEN;
	this.setBackground(Color.GREEN);
    }

    // Make Node MAGENTA, meaning it's solved path
    public void makePath() {
	colour = Color.MAGENTA;
	this.setBackground(Color.MAGENTA);
    }

    /****** CHANGE COLOUR OF NODE ON SPECIFIC MOUSE CLICKS ******/
    @Override
    public void mouseClicked(MouseEvent e) {
	// When mouse has been pressed and released
	if (isAvailable()) {
	    makeBarrier();
	} else if (colour == Color.BLACK) {
	    makeAvailable();
	}
    }

    @Override
    public void mousePressed(MouseEvent e) {
	// When mouse has just been pressed
    }

    @Override
    public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub

    }

}
