package algorithms;

import java.awt.event.ActionEvent;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import sections.board.GridPanel;
import sections.board.Node;

/**
 * 
 * The following Algorithm class is a generic Algorithm in which all algorithms
 * such as BFS, DFS, Dijkstra, and A* extends from. This class has all the
 * properties, fields, and methods that each of the specific algorithms have in
 * common.
 * 
 * @author Troy Zada
 *
 */

public class Algorithm {

    // Constants
    protected static final double DEFAULT_VALUE = Double.MAX_VALUE;

    // Fields
    protected GridPanel mainGrid;
    protected Node start;
    protected Node end;
    protected Map<Node, Node> parents; // Each Node maps to its parent

    /**
     * Finds the parent Node to the given parameter Node.
     * 
     * @param v - The child.
     * @return - The child's parent.
     */
    protected Node getParent(Node v) {
	return parents.get(v);
    }

    /**
     * Getter method for the source Node of the grid.
     * 
     * @return - Start Node.
     */
    protected Node getStart() {
	return start;
    }

    /**
     * Getter method for the end Node of the grid.
     * 
     * @return - End Node.
     */
    protected Node getEnd() {
	return end;
    }

    /**
     * Goes through entire board initializing every Node that isn't a barrier a
     * default distance and parent.
     * 
     * @param distances    - Map of all distances to fill with default values.
     * @param defaultValue - The default distance value to give each Node.
     */
    protected void fillDefaultValues(Map<Node, Double> distances, double defaultValue) {
	for (int r = 0; r < GridPanel.ROWS; r++) {
	    for (int c = 0; c < GridPanel.COLUMNS; c++) {
		if (!mainGrid.getNode(r, c).isBarrier()) {
		    distances.put(mainGrid.getNode(r, c), defaultValue);
		    parents.put(mainGrid.getNode(r, c), null);
		}
	    }
	}
    }

    /**
     * Helper method that calculates the direct distance between the 2 parameter
     * Nodes. The distance is calculated by finding the difference in the Node's
     * x-coordinates and y-coordinates separately, then finding the hypotenuse of
     * that triangle; using pythagorean's theorem, a^2 + b^2 = c^2.
     * 
     * @param current - The start Node for the distance.
     * @param other   - The end Node for the distance.
     * @return - The distance between the current and other Node.
     */
    protected double calculateDistanceBetweenNodes(Node current, Node other) {
	double dx = Math.abs(current.getPosition()[1] - other.getPosition()[1]);
	double dy = Math.abs(current.getPosition()[0] - other.getPosition()[0]);
	return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    /**
     * Visually closes Nodes that have been checked (turns it RED). Only close the
     * Node if it is not the start, not the end, and we actually want to see the
     * steps unfold.
     * 
     * @param n         - Node to close.
     * @param showSteps - Condition if we want to actually see steps unfold.
     */
    protected void visuallyCloseNode(Node n, boolean showSteps) {
	if (!n.isStart() && !n.isEnd() && showSteps) {
	    n.makeClosed();
	}
    }

    /**
     * Visually opens Nodes that need to be checked (turns it GREEN). Only open the
     * Node if it is not the start, not the end, and we actually want to see the
     * steps unfold.
     * 
     * @param n         - Node to open.
     * @param showSteps - Condition if we want to actually see steps unfold.
     */
    protected void visuallyOpenNode(Node n, boolean showSteps) {
	if (!n.isStart() && !n.isEnd() && showSteps) {
	    n.makeOpen();
	}
    }

    /**
     * If user wants to see steps unfold, then a Timer is being used to display each
     * Node individually. Thus, check each time if a solution exists.
     * 
     * If no solution exists at all, then stop the Timer and show the "No Solution"
     * dialog box. If a solution is found, then stop the Timer and draw the solution
     * path. Both cases the g, h, and f costs are reset such that if an algorithm is
     * being rerun, it starts with fresh costs (and not ones from the previous run).
     * 
     * @param noSolution    - Condition that means no solution exists.
     * @param e             - Timer that allows for Node's to be displayed one at a
     *                      time.
     * @param solutionFound - Condition that means a solution exist.
     */
    protected void showSolutionOutputWithSteps(boolean noSolution, ActionEvent e, boolean solutionFound) {
	if (noSolution) {
	    stopTimerAndResetCosts(e);
	    noSolutionsDialogBox();
	} else if (solutionFound) {
	    stopTimerAndResetCosts(e);
	    drawPath(end);
	}
    }

    /**
     * Private helper method to reduce redundancy which stops the Timer and resets
     * g, h, and f costs.
     * 
     * @param e - Timer allowing Nodes to be displayed one at a time.
     */
    private void stopTimerAndResetCosts(ActionEvent e) {
	((Timer) e.getSource()).stop();
	mainGrid.resetAllCosts();
    }

    /**
     * If user does not want to see steps unfold, then no Timer is needed. Thus, can
     * simply check if a solution exists or not.
     * 
     * If no solution exists at all, then show the "No Solution" dialog box. If a
     * solution is found, then draw the solution path. Both cases the g, h, and f
     * costs are reset such that if an algorithm is being rerun, it starts with
     * fresh costs (and not ones from the previous run).
     * 
     * @param noSolution - Condition that means no solution exists.
     */
    protected void showSolutionOutputWithoutSteps(boolean noSolution) {
	if (noSolution) {
	    noSolutionsDialogBox();
	} else {
	    drawPath(end);
	}
	mainGrid.resetAllCosts();
    }

    /**
     * Dialog box that displays that no solution exists.
     */
    protected void noSolutionsDialogBox() {
	JOptionPane.showMessageDialog(null, "The board has no solution.", "No Solutions",
		JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to visually draw solution on board (turn MAGENTA)
    /**
     * Visually draws the solution to the grid (turns all Nodes part of the solution
     * path MAGENTA). Executed by starting at the end Node, and getting its parent
     * Node, then repeating until the start Node is reached.
     * 
     * @param end - The end Node to start at.
     */
    protected void drawPath(Node end) {
	Node currentNode = parents.get(end);
	while (!currentNode.equals(start)) {
	    currentNode.makePath();
	    currentNode = parents.get(currentNode);
	}
    }

}
