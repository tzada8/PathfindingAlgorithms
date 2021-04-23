package sections.board;

import java.awt.Dimension;
import java.util.LinkedHashSet;
import java.util.Set;

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

	// Create a Node object at each (row, column) of grid
	for (int r = 0; r < ROWS; r++) {
	    for (int c = 0; c < COLUMNS; c++) {
		map[r][c] = new Node(r, c);
		this.add(map[r][c]);
	    }
	}
    }

    // Gets Node at specified parameters
    public Node getNode(int row, int col) {
	return map[row][col];
    }

    // Gets Start Node from map
    public Node getStartNode() {
	for (int r = 0; r < ROWS; r++) {
	    for (int c = 0; c < COLUMNS; c++) {
		if (map[r][c].isStart()) {
		    return map[r][c];
		}
	    }
	}
	return null;
    }

    // Gets End Node from map
    public Node getEndNode() {
	for (int r = 0; r < ROWS; r++) {
	    for (int c = 0; c < COLUMNS; c++) {
		if (map[r][c].isEnd()) {
		    return map[r][c];
		}
	    }
	}
	return null;
    }

    /*
     * Returns all the adjacent Nodes to Node v that are available (WHITE Nodes).
     */
    public Set<Node> getAdjacencyNodes(Node v) {

	Set<Node> adjacentNodes = new LinkedHashSet<Node>();
	int currentRow = v.getPosition()[0];
	int currentCol = v.getPosition()[1];

	// Order of Insertion:
	// TOP, RIGHT, BOTTOM, LEFT, TOP-LEFT, TOP-RIGHT, BOTTOM-RIGHT, BOTTOM-LEFT

	// Boundairies for top, right, bottom, and left
	int topR = determineBoundaries(currentRow - 1, GridPanel.ROWS);
	int rightC = determineBoundaries(currentCol + 1, GridPanel.COLUMNS);
	int bottomR = determineBoundaries(currentRow + 1, GridPanel.ROWS);
	int leftC = determineBoundaries(currentCol - 1, GridPanel.COLUMNS);

	// TOP
	addAdjacentNodes(adjacentNodes, v, topR, currentCol);
	// RIGHT
	addAdjacentNodes(adjacentNodes, v, currentRow, rightC);
	// BOTTOM
	addAdjacentNodes(adjacentNodes, v, bottomR, currentCol);
	// LEFT

	addAdjacentNodes(adjacentNodes, v, currentRow, leftC);
	// TOP-LEFT
	addAdjacentNodes(adjacentNodes, v, topR, leftC);
	// TOP-RIGHT
	addAdjacentNodes(adjacentNodes, v, topR, rightC);
	// BOTTOM-RIGHT
	addAdjacentNodes(adjacentNodes, v, bottomR, rightC);
	// BOTTOM-LEFT
	addAdjacentNodes(adjacentNodes, v, bottomR, leftC);

	return adjacentNodes;
    }

    // General method to determine if a Node outside the board is trying to be
    // accessed
    private int determineBoundaries(int testVal, int upperLimitingFactor) {
	// If outside lower or upper boundaries, adjust value to keep it inside
	if (testVal < 0) {
	    testVal = 0;
	} else if (testVal > upperLimitingFactor - 1) {
	    testVal = upperLimitingFactor - 1;
	}
	return testVal;
    }

    // Helper method to determine if given point should be added to adjacency list
    private void addAdjacentNodes(Set<Node> adjacency, Node current, int rowLoc, int colLoc) {
	if ((map[rowLoc][colLoc].isAvailable() || map[rowLoc][colLoc].isEnd())
		&& !map[rowLoc][colLoc].equals(current)) {
	    adjacency.add(map[rowLoc][colLoc]);
	}
    }

    public void resetAllCosts() {
	for (int r = 0; r < GridPanel.ROWS; r++) {
	    for (int c = 0; c < GridPanel.COLUMNS; c++) {
		map[r][c].resetCosts();
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

    // Goes through board and verifies if there is both a START and END point
    public boolean hasStartAndEndPoint() {
	int counter = 0;
	for (int r = 0; r < ROWS; r++) {
	    for (int c = 0; c < COLUMNS; c++) {
		if (map[r][c].isStart() || map[r][c].isEnd()) {
		    counter++;
		}
	    }
	}
	return counter == 2;
    }

    // Makes all Node's on the board unclickable
    public void makeUnclickable() {
	clickable = false;
    }

    // Makes all Node's on the board clickable
    public void makeClickable() {
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
}
