package algorithms;

import java.util.Map;

import javax.swing.JOptionPane;

import sections.board.GridPanel;
import sections.board.Node;

// Generic algorithm with features that all specific algorithms have in common
// All specific algorithms extends from this class
public class Algorithm {

    // Fields
    protected GridPanel mainGrid;
    protected Node start;
    protected Node end;
    protected Map<Node, Node> parents; // Node to Node

    /**
     * This instance method finds the parent vertex to the given parameter vertex.
     */
    protected Node getParent(Node v) {
	return parents.get(v);
    }

    /**
     * This instance method returns the source node.
     */
    protected Node getStart() {
	return start;
    }

    // Fills entire board with a default value
    protected void fillDefaultValues(Map<Node, Double> distances, double defaultValue) {
	// For every Node that isn't a barrier, initialize a default distance and parent
	for (int r = 0; r < GridPanel.ROWS; r++) {
	    for (int c = 0; c < GridPanel.COLUMNS; c++) {
		if (!mainGrid.getNode(r, c).isBarrier()) {
		    distances.put(mainGrid.getNode(r, c), defaultValue);
		    parents.put(mainGrid.getNode(r, c), null);
		}
	    }
	}
    }

    // Helper method to calculate the direct distance between 2 Nodes
    protected double calculateActualDistance(Node current, Node other) {
	// X difference between Nodes
	double dx = Math.abs(current.getPosition()[1] - other.getPosition()[1]);
	// Y difference between Nodes
	double dy = Math.abs(current.getPosition()[0] - other.getPosition()[0]);
	// Return actual distance from pythagorean's theorem --> a^2 + b^2 = c^2
	return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    // Method to visually close (turn RED) a Node that has been checked
    // If Node is not start, not end, and we want to see steps, then show it
    protected void visuallyCloseNode(Node n, boolean showSteps) {
	if (!n.isStart() && !n.isEnd() && showSteps) {
	    n.makeClosed();
	}
    }

    // Method to visually show next Nodes to be checked (turn GREEN)
    // If Node is not start, not end, and we want to see steps, then show it
    protected void visuallyOpenNode(Node n, boolean showSteps) {
	if (!n.isStart() && !n.isEnd() && showSteps) {
	    n.makeOpen();
	}
    }

    // Method to visually draw solution on board (turn MAGENTA)
    protected void drawPath(Node end) {
	// Change all Nodes part of solution accordingly
	int totalBlocks = 1;
	Node currentNode = parents.get(end);
	while (!currentNode.equals(start)) {
	    currentNode.makePath();
	    currentNode = parents.get(currentNode);
	    totalBlocks++;
	}
	System.out.println(totalBlocks);
    }

    // No solutions Dialog Box
    protected void noSolutionsDialogBox() {
	JOptionPane.showMessageDialog(null, "The board has no solution.", "No Solutions",
		JOptionPane.INFORMATION_MESSAGE);
    }

}
