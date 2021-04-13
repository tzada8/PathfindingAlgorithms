package algorithms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GridPanel extends JPanel {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // Constants
    private static final int COLUMNS = 30;
    private static final int ROWS = 30;
    private static final int ORIGIN_X = 0;
    private static final int ORIGIN_Y = 0;
    private static final int MAP_SIZE = ROWS * Node.NODE_SIZE;

    // Fields
    private Node[][] map = new Node[ROWS][COLUMNS];

    // Basic Panel that holds all content for a given algorithm
    public GridPanel(Color background) {
	this.setPreferredSize(new Dimension(MAP_SIZE, MAP_SIZE));
	this.setBackground(background);
	this.setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	// Create a Node object at each row/column of grid
	for (int r = 0; r < ROWS; r++) {
	    for (int c = 0; c < COLUMNS; c++) {
		map[r][c] = new Node(ORIGIN_X, ORIGIN_Y, r, c);
		this.add(map[r][c]);
	    }
	}
    }
}
