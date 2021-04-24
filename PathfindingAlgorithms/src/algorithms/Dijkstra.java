package algorithms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import sections.board.GridPanel;
import sections.board.Node;

public class Dijkstra extends Algorithm {

    // Constants
    private static final double DEFAULT_VALUE = Double.MAX_VALUE;

    // Fields
    private Map<Node, Double> distances; // Node to doubles

    public Dijkstra(GridPanel mainGrid, Node start, boolean showSteps) {
	// Initialize all values
	this.mainGrid = mainGrid;
	this.start = start;
	this.end = mainGrid.getEndNode();
	this.distances = new HashMap<>();
	this.parents = new HashMap<>();

	// For every Node that isn't a barrier, initialize a default distance and parent
	fillDefaultValues(distances, DEFAULT_VALUE);

	// Keep track of "visited" vertices and their current distance
	HashMap<Node, Double> testDist = new HashMap<>();
	testDist.put(start, 0.0);
	// Distance from source to itself is always 0
	distances.put(start, 0.0);

	// Solution will be found in 2 ways depending if user wants to see solution
	// 1. Using a timer that renders the Node every 0.01s until the END node is
	// reached
	// 2. Using a while loop that doesn't render anything except the solution path
	if (showSteps) {
	    Timer timer = new Timer(10, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    try {
			solveUsingDijkstra(testDist, showSteps, end); // Solve grid using BFS

			if (testDist.isEmpty()) {
			    // If Queue is empty, then we ran out of options, so no solution found
			    ((Timer) e.getSource()).stop();
			    throw new NoSuchElementException("Force catch block");
			} else if (distances.get(end) != DEFAULT_VALUE) {
			    // If END Node's distance is not default MAX_VALUE, then we reached it, so draw
			    // solution
			    ((Timer) e.getSource()).stop();
			    drawPath(end);
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
		while (distances.get(end) == DEFAULT_VALUE && !testDist.isEmpty()) {
		    solveUsingDijkstra(testDist, showSteps, end);
		}
		if (testDist.isEmpty()) {
		    throw new NoSuchElementException();
		} else {
		    drawPath(end);
		}
	    } catch (NoSuchElementException e) { // Dialog box if no solution exists
		JOptionPane.showMessageDialog(null, "The board has no solution.", "No Solutions",
			JOptionPane.INFORMATION_MESSAGE);
	    }
	}
    }

    /**
     * Helper method to do main algorithm; will be called both if user wants to see
     * steps or does not want to see steps
     */
    private void solveUsingDijkstra(Map<Node, Double> testDist, boolean showSteps, Node end) {
	// Find Node with smallest distance from known distances
	Node lowestNodeD = end;
	double lowestDist = DEFAULT_VALUE;
	// Go through all Nodes
	for (Node n : testDist.keySet()) {
	    System.out.println(n.getPosition()[0] + ", " + n.getPosition()[1] + " = " + testDist.get(n));
	    if (testDist.get(n) < lowestDist) {
		lowestDist = testDist.get(n);
		lowestNodeD = n;
	    }
	}
	testDist.remove(lowestNodeD);

	visuallyCloseNode(lowestNodeD, showSteps);

	// For every node adjacent to u
	for (Node v : mainGrid.getAdjacencyNodes(lowestNodeD)) {
	    // If v is not yet visited, then change its distance and parent accordingly
	    if (distances.get(v) == DEFAULT_VALUE) {
		visuallyOpenNode(v, showSteps);

		// Calc dist from start for each unvisited Node
		double currentDistToStart = calculateDistFromStart(v, lowestNodeD);
		// If newly calculated distance is less than its previous know distance,
		// then update its distance and parent
		if (currentDistToStart < distances.get(v)) {
		    distances.put(v, currentDistToStart);
		    parents.put(v, lowestNodeD);
		}
		testDist.put(v, currentDistToStart);

		System.out.println("(" + v.getPosition()[0] + ", " + v.getPosition()[1] + ") = "
			+ distances.get(mainGrid.getNode(v.getPosition()[0], v.getPosition()[1])));
	    }
	}
    }

    // Calculate the distance between the current Node and the start Node.
    // Distance will be whatever parent's distance is, plus difference between
    // 2 Nodes
    private double calculateDistFromStart(Node current, Node parent) {
	return distances.get(parent) + calculateActualDistance(current, parent);
    }
}
