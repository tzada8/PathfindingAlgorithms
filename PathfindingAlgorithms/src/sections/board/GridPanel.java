package sections.board;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import sections.board.presets.MakePreset1;
import sections.board.presets.MakePreset2;
import sections.board.presets.MakePreset3;

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
    private void resetBoard() {
	for (int r = 0; r < ROWS; r++) {
	    for (int c = 0; c < COLUMNS; c++) {
		if (!map[r][c].isAvailable()) {
		    map[r][c].makeAvailable();

		}
	    }
	}
	startPointPlaced = false;
	endPointPlaced = false;
    }

    // Makes all Node's on the board unclickable
    private void makeUnclickable() {
	clickable = false;
    }

    // Makes all Node's on the board clickable
    private void makeClickable() {
	clickable = true;
    }

    // Makes board both unclickable and clears all Nodes to WHITE
    private void unclickableAndEmptyBoard() {
	this.resetBoard();
	this.makeUnclickable();
    }

    // Creates the Freehand board
    public void makeFreehand() {
	this.resetBoard();
	this.makeClickable();
    }

    // Creates the Preset1 board
    public void makePreset1() {
	this.unclickableAndEmptyBoard();
	new MakePreset1(map);
    }

    // Creates the Preset2 board
    public void makePreset2() {
	this.unclickableAndEmptyBoard();
	new MakePreset2(map);
    }

    // Creates the Preset3 board
    public void makePreset3() {
	this.unclickableAndEmptyBoard();
	new MakePreset3(map);
    }

    // Creates the Random board
    public void makeRandom() {
	this.unclickableAndEmptyBoard();
    }

    // Goes through entire board and solves it depending on specific algorithm
    public void solveBoard(/* ALGORITHM PARAMETER */) {
    }
}
