package sections.board;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import sections.board.presets.MakePreset1;

public class GridPanel extends JPanel {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // Constants
    public static final int COLUMNS = 31;
    public static final int ROWS = 31;
    public static final int MAP_SIZE = ROWS * Node.NODE_SIZE;

    // Fields
    private Node[][] map = new Node[ROWS][COLUMNS];
    static boolean startPointPlaced = false;
    static boolean endPointPlaced = false;
    static boolean clickable = true;

    // Basic Panel that holds all content for a given algorithm
    public GridPanel() {
	this.setPreferredSize(new Dimension(MAP_SIZE, MAP_SIZE));
	this.setBackground(null);
	this.setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	// Create a Node object at each row/column of grid
	for (int r = 0; r < ROWS; r++) {
	    for (int c = 0; c < COLUMNS; c++) {
		map[r][c] = new Node(r, c);
		this.add(map[r][c]);
	    }
	}
    }

    // Goes through entire board and resets each Node to colour WHITE
    public void resetBoard() {
	for (int r = 0; r < ROWS; r++) {
	    for (int c = 0; c < COLUMNS; c++) {
//		System.out.println(r + ", " + c + " = " + map[r][c].getColour());
		if (!map[r][c].isAvailable()) {
		    System.out.println(map[r][c].getColour());
		    map[r][c].makeAvailable();
		    System.out.println(map[r][c].getColour());

		}
	    }
	}
	startPointPlaced = false;
	endPointPlaced = false;
    }

    // Makes all Node's on the board unclickable
    public void makeUnclickable() {
	clickable = false;
    }

    // Makes all Node's on the board clickable
    public void makeClickable() {
	clickable = true;
    }

    public void makeFreehand() {
	for (int r = 0; r < ROWS; r++) {
	    for (int c = 0; c < COLUMNS; c++) {
		map[r][c].makeAvailable();
	    }
	}
    }

    // MAKECLICKABLE / MAKEUNCLICKABLE / RESETBOARD CAN ALL BE PRIVATE FIELDS AND
    // THEN CALLED IN MAKEFREEHAND, MAKEPRESET1, ..., RANDOM

    public void makePreset1() {
	new MakePreset1(map);
    }

    // Goes through entire board and solves it depending on specific algorithm
    public void solveBoard(/* ALGORITHM PARAMETER */) {
    }
}
