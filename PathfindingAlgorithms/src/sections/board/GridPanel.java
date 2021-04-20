package sections.board;

import java.awt.Dimension;

import javax.swing.JPanel;

import algorithms.Algorithm;
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

	// Create a Node object at each (row, column) of grid
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

    // Goes through entire board and resets pathfinding Nodes to WHITE
    // --> RED, GREEN, and MAGENTA
    public void resetPathfinding() {
	for (int r = 0; r < ROWS; r++) {
	    for (int c = 0; c < COLUMNS; c++) {
		if (map[r][c].isClosed() || map[r][c].isOpen() || map[r][c].isPath()) {
		    map[r][c].makeAvailable();
		}
	    }
	}
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

    // Makes board both clickable and clears all Nodes to WHITE
    private void clickableAndEmptyBoard() {
	this.resetBoard();
	this.makeClickable();
    }

    // Creates the Freehand board
    public void makeFreehand() {
	this.clickableAndEmptyBoard();
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
    public void solveBoard(Algorithm algorithm, boolean showSteps) {
	System.out.println(algorithm + " and " + showSteps);
    }
}
