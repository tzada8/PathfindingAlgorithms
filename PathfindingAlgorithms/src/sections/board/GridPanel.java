package sections.board;

import java.awt.Dimension;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.JPanel;

import sections.board.presets.MakePreset1;
import sections.board.presets.MakePreset2;
import sections.board.presets.MakePreset3;
import sections.board.presets.MakeRandom;

/**
 * 
 * The following GridPanel class extends JPanel and acts as a container for
 * every Node that has been created. This class stores all the Nodes in a 2D
 * array allowing each Node to be accessed when necessary.
 * 
 * All methods of this class are for the grid as an entirety, applying to all
 * Nodes.
 * 
 * @author Troy Zada
 *
 */

public class GridPanel extends JPanel {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // Constants
    public static final int COLUMNS = 31;
    public static final int ROWS = 31;
    public static final int MAP_SIZE_WIDTH = COLUMNS * Node.NODE_SIZE;
    public static final int MAP_SIZE_HEIGHT = ROWS * Node.NODE_SIZE;

    // Fields
    private Node[][] map = new Node[ROWS][COLUMNS];
    static boolean startPointPlaced = false;
    static boolean endPointPlaced = false;
    static boolean clickable = true;

    // Basic Panel that holds all content for a given algorithm
    /**
     * This constructor creates the panel for the grid combining each individual
     * Node to make the entire grid.
     */
    public GridPanel() {
	this.setPreferredSize(new Dimension(MAP_SIZE_WIDTH, MAP_SIZE_HEIGHT));
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

    /**
     * Getter method to get the Node that sits at the given row and column.
     * 
     * @param row - Row of the Node being returned.
     * @param col - Column of the Node being returned.
     * @return - The Node sitting at the specified row and column.
     */
    public Node getNode(int row, int col) {
	return map[row][col];
    }

    /**
     * Goes through entire board looking for the start Node (ORANGE).
     * 
     * @return - Start Node if one exists, and null otherwise.
     */
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

    /**
     * Goes through entire board looking for the end Node (CYAN).
     * 
     * @return - End Node if one exists, and null otherwise.
     */
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

    /**
     * Gets all the Nodes that are adjacent to the parameter Node, v, that are
     * available (WHITE).
     * 
     * @param v - The previous Node.
     * @return - All Nodes adjacent to Node v.
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

	addAdjacentNodes(adjacentNodes, v, topR, currentCol); // TOP
	addAdjacentNodes(adjacentNodes, v, currentRow, rightC); // RIGHT
	addAdjacentNodes(adjacentNodes, v, bottomR, currentCol); // BOTTOM
	addAdjacentNodes(adjacentNodes, v, currentRow, leftC); // LEFT

	addAdjacentNodes(adjacentNodes, v, topR, leftC); // TOP-LEFT
	addAdjacentNodes(adjacentNodes, v, topR, rightC); // TOP-RIGHT
	addAdjacentNodes(adjacentNodes, v, bottomR, rightC); // BOTTOM-RIGHT
	addAdjacentNodes(adjacentNodes, v, bottomR, leftC); // BOTTOM-LEFT

	return adjacentNodes;
    }

    /**
     * Private helper method to determine if a Node outside the board is trying to
     * be accessed, being a negative index or greater than the number of ROWS /
     * COLUMNS.
     * 
     * @param testVal             - The location being tests to see if it lies
     *                            within the boundaries.
     * @param upperLimitingFactor - Factor for upper limit (total ROWS / COLUMNS).
     * @return - The corrected location such that value will still lie within the
     *         boundaires.
     */
    private int determineBoundaries(int testVal, int upperLimitingFactor) {
	// If outside lower or upper boundaries, adjust value to keep it inside
	if (testVal < 0) {
	    testVal = 0;
	} else if (testVal > upperLimitingFactor - 1) {
	    testVal = upperLimitingFactor - 1;
	}
	return testVal;
    }

    /**
     * Private helper method to determine if a given point should be added to the
     * adjacency set or not.
     * 
     * @param adjacency - Set for all Nodes adjacent to the current one.
     * @param current   - The current Node.
     * @param rowLoc    - The given row for a potential adjacent Node.
     * @param colLoc    - The given column for a potential adjacent Node.
     */
    private void addAdjacentNodes(Set<Node> adjacency, Node current, int rowLoc, int colLoc) {
	if ((map[rowLoc][colLoc].isAvailable() || map[rowLoc][colLoc].isEnd())
		&& !map[rowLoc][colLoc].equals(current)) {
	    adjacency.add(map[rowLoc][colLoc]);
	}
    }

    /**
     * Resets all costs of every Node back to default value of the double max value.
     */
    public void resetAllCosts() {
	for (int r = 0; r < GridPanel.ROWS; r++) {
	    for (int c = 0; c < GridPanel.COLUMNS; c++) {
		map[r][c].resetCosts();
	    }
	}
    }

    /**
     * Goes through entire board and resets each Node to a colour of WHITE, as well
     * as stating that the start and end points haven't been placed anymore.
     */
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

    /**
     * Goes through entire board and resets pathfinding Nodes to a colour of WHITE.
     * This includes all RED, GREEN, and MAGENTA Nodes.
     */
    public void resetPathfinding() {
	for (int r = 0; r < ROWS; r++) {
	    for (int c = 0; c < COLUMNS; c++) {
		if (map[r][c].isClosed() || map[r][c].isOpen() || map[r][c].isPath()) {
		    map[r][c].makeAvailable();
		}
	    }
	}
    }

    /**
     * Goes through the entire board and verifies that both a start and end point
     * have been place.
     * 
     * @return - Whether both start and end point exist.
     */
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

    /**
     * Makes all Node's on the board unclickable.
     */
    public void makeUnclickable() {
	clickable = false;
    }

    /**
     * Makes all Node's on the board clickable.
     */
    public void makeClickable() {
	clickable = true;
    }

    /**
     * Makes the board unclickable and resets all Nodes to WHITE.
     */
    private void unclickableAndEmptyBoard() {
	this.resetBoard();
	this.makeUnclickable();
    }

    /**
     * Makes the board clickable and resets all Nodes to WHITE.
     */
    private void clickableAndEmptyBoard() {
	this.resetBoard();
	this.makeClickable();
    }

    /**
     * Creates a Freehand board.
     */
    public void makeFreehand() {
	this.clickableAndEmptyBoard();
    }

    /**
     * Creates the Preset1 board by calling the MakePreset1 class.
     */
    public void makePreset1() {
	this.unclickableAndEmptyBoard();
	new MakePreset1(map);
    }

    /**
     * Creates the Preset2 board by calling the MakePreset2 class.
     */
    public void makePreset2() {
	this.unclickableAndEmptyBoard();
	new MakePreset2(map);
    }

    /**
     * Creates the Preset3 board by calling the MakePreset3 class.
     */
    public void makePreset3() {
	this.unclickableAndEmptyBoard();
	new MakePreset3(map);
    }

    /**
     * Creates a Random board by calling the MakeRandom class.
     */
    public void makeRandom() {
	this.unclickableAndEmptyBoard();
	new MakeRandom(map);
    }
}
