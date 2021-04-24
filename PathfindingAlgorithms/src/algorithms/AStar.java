package algorithms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import sections.board.GridPanel;
import sections.board.Node;

public class AStar extends Algorithm {
    private ArrayList<Node> openList; // Open Nodes
    private ArrayList<Node> closedList; // Closed Nodes

    public AStar(GridPanel mainGrid, Node start, boolean showSteps) {
	// Initialize all values
	this.mainGrid = mainGrid;
	this.start = start;
	this.end = mainGrid.getEndNode();
	this.parents = new HashMap<>();
	this.openList = new ArrayList<>();
	this.closedList = new ArrayList<>();

	Node current = this.start;
	// Calculates g, h, and f for start point (g = 0)
	current.setGCost(0);
	current.setHCost(calcHDistance(current, end));
	current.setFCost(calcFValue(current.getGCost(), current.getHCost()));

	openList.add(current);

	// Solution will be found in 2 ways depending if user wants to see solution
	// 1. Using a timer that renders the Node every 0.01s until the END node is
	// reached
	// 2. Using a while loop that doesn't render anything except the solution path
	if (showSteps) {
	    Timer timer = new Timer(10, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    try {
			// Remove Node with lowest F value from open List and make it current
			Node current = getLowestF(end);
			solveUsingAStar(current, end, showSteps);

			if (openList.isEmpty()) {
			    // If Queue is empty, then we ran out of options, so no solution found
			    ((Timer) e.getSource()).stop();
			    throw new NoSuchElementException("Force catch block");
			} else if (current.equals(end)) {
			    // If END Node's distance is not default -1, then we reached it, so draw
			    // solution
			    ((Timer) e.getSource()).stop();
			    drawPath(end);
			    mainGrid.resetAllCosts();
			}
		    } catch (NoSuchElementException e1) { // Dialog box if no solution exists
			JOptionPane.showMessageDialog(null, "The board has no solution.", "No Solutions",
				JOptionPane.INFORMATION_MESSAGE);
		    }
		}
	    });
	    timer.start();
	} else {
	    // Try solving board, but if it has no solutions, then catch as error
	    try {
		// While we haven't reached the END node yet
		while (!current.equals(end)) {
		    solveUsingAStar(current, end, showSteps);
		    // Remove Node with lowest F value from open List and make it current
		    current = getLowestF(end);
		}
		drawPath(end);
		mainGrid.resetAllCosts();
	    } catch (NullPointerException e) { // Dialog box if no solution exists
		JOptionPane.showMessageDialog(null, "The board has no solution.", "No Solutions",
			JOptionPane.INFORMATION_MESSAGE);
	    }
	}
    }

    private void solveUsingAStar(Node current, Node end, boolean showSteps) {
	visuallyCloseNode(current, showSteps);

	// For every node adjacent to u
	for (Node v : mainGrid.getAdjacencyNodes(current)) {
	    // If Node not in open or closed Lists, then add to open
	    if (!openList.contains(v) && !closedList.contains(v)) {
		openList.add(v);
		visuallyOpenNode(v, showSteps);
	    }
	    // Do g, h, and f calculations for Node v
	    double currentG = calcGDistance(v, current); // G
	    v.setHCost(calcHDistance(v, end));
	    double currentF = calcFValue(currentG, v.getHCost()); // F

	    // If new F value is less than the Node's existing F value, then update existing
	    // f and parent
	    if (currentF < v.getFCost()) {
		v.setGCost(currentG);
		v.setFCost(currentF);
		parents.put(v, current);
		System.out.println(
			current.getPosition()[0] + ", " + current.getPosition()[1] + " = " + current.getFCost());
	    }

	}
	// Add current Node to closed List and remove from open
	closedList.add(current);
	openList.remove(current);
    }

    // Calculates actual distance from start, g (finds distance between current Node
    // and start Node including all present obstacles)
    private double calcGDistance(Node current, Node previous) {
	return previous.getGCost() + calculateActualDistance(current, previous);
    }

    // Calculates heuristic distance, h (finds distance between current Node
    // and end Node assuming that no obstacles exist)
    private double calcHDistance(Node current, Node end) {
	return calculateActualDistance(current, end);
    }

    // Calculates heuristic distance, h (finds distance between current Node
    // and end Node assuming that no obstacles exist)
    private double calcFValue(double g, double h) {
	return g + h;
    }

    // Find Node with lowest F value
    private Node getLowestF(Node end) {
	// Default lowestNodeF to be end Node
	Node lowestNodeF = end;
	double lowestFVal = end.getFCost();
	// Go through all Nodes
	for (Node n : openList) {
	    if (n.getFCost() < lowestFVal) {
		lowestNodeF = n;
		lowestFVal = n.getFCost();
	    }
	}
	return lowestNodeF;
    }

}
