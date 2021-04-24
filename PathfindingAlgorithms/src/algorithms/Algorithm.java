package algorithms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import sections.board.GridPanel;
import sections.board.Node;

// Generic algorithm with features that all specific algorithms have in common
// All specific algorithms extends from this class
public class Algorithm {

    // Constants
    protected static final double DEFAULT_VALUE = Double.MAX_VALUE;
    public static final String[] ALGORITHM_NAMES = { "Breath First Search", "Depth First Search", "A*", "Dijkstra" };

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

//    protected void showSolutionUnfold(String type) {
//	Timer timer = new Timer(10, new ActionListener() {
//	    @Override
//	    public void actionPerformed(ActionEvent e) {
//		try {
//		    // Solve grid using BFS
//		    solveUsingBFS(q, showSteps);
//
//		    // Displays specific solution (either MAGENTA path or "no solution" dialog box)
//		    showSolutionOutput(q.isEmpty(), e, distances.get(end) != DEFAULT_VALUE);
//		} catch (NoSuchElementException e1) {
//		    // Display dialog box since no solution exists
//		    noSolutionsDialogBox();
//		}
//	    }
//	});
//	timer.start();
//    }

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

    // If user wants to see steps, then check if no solution or if solution exists
    protected void showSolutionOutputWithSteps(boolean noSolution, ActionEvent e, boolean solutionFound) {
	if (noSolution) {
	    // If there is no solution, then stop timer and throw exception
	    ((Timer) e.getSource()).stop();
	    mainGrid.resetAllCosts();
	    noSolutionsDialogBox();
	} else if (solutionFound) {
	    // If End Node has been found, then solution exists, so stop timer and draw
	    // solution
	    ((Timer) e.getSource()).stop();
	    mainGrid.resetAllCosts();
	    drawPath(end);
	}
    }

    // If user does not want to see steps, then check if no solution or if solution
    // exists
    protected void showSolutionOutputWithoutSteps(boolean noSolution) {
	if (noSolution) {
	    // Display dialog box since no solution exists
	    noSolutionsDialogBox();
	} else {
	    // Draw path since solution exists
	    drawPath(end);
	}
	mainGrid.resetAllCosts();
    }

    // No solutions Dialog Box
    protected void noSolutionsDialogBox() {
	JOptionPane.showMessageDialog(null, "The board has no solution.", "No Solutions",
		JOptionPane.INFORMATION_MESSAGE);
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
	System.out.println("Total Blocks = " + totalBlocks);
    }

}
