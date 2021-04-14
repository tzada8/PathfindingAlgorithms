package sections.board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GridPanel extends JPanel {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // Constants
    private static final int COLUMNS = 30;
    private static final int ROWS = 30;
    public static final int MAP_SIZE = ROWS * Node.NODE_SIZE;

    // Fields
    private Node[][] map = new Node[ROWS][COLUMNS];
    static boolean startPointPlaced = false;
    static boolean endPointPlaced = false;

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
		if (map[r][c].getColour() != Color.WHITE) {
		    map[r][c].makeAvailable();
		}
	    }
	}
	startPointPlaced = false;
	endPointPlaced = false;
    }
}
