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

/**
 * The following Dijkstra class conducts the Dijkstra pathfinding algorithm on a
 * given grid, displaying the shortest path from a start point to an end point.
 * 
 * @author Troy Zada
 *
 */

public class Dijkstra extends Algorithm {

    // Each Node maps to a Double value, being the distance from start
    private Map<Node, Double> distances;

    /**
     * This constructor conducts a Dijkstra Search on the provided grid, where the
     * solution can either be viewed as it unfolds or just the final answer will be
     * displayed.
     * 
     * @param mainGrid  - The board that will have BFS conducted on it.
     * @param start     - The source Node where BFS will begin.
     * @param showSteps - Whether the solution can be seen or just final answer.
     */
    public Dijkstra(GridPanel mainGrid, Node start, boolean showSteps) {
	this.mainGrid = mainGrid;
	this.start = start;
	this.end = mainGrid.getEndNode();
	this.parents = new HashMap<>();
	this.distances = new HashMap<>();

	// For every Node that isn't a barrier, initialize a default distance and parent
	fillDefaultValues(distances, DEFAULT_VALUE);

	// Keep track of visited Nodes and their current distance
	HashMap<Node, Double> visited = new HashMap<>();
	visited.put(start, 0.0);
	distances.put(start, 0.0);

	// Display solution in 1 of 2 ways, depending on showSteps boolean
	if (showSteps) { // Using a timer that renders a Node every 0.01s until End Node is reached
	    Timer timer = new Timer(10, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    solveUsingDijkstra(visited, showSteps, end);

		    // Displays specific solution (either MAGENTA path or "no solution" dialog box)
		    showSolutionOutputWithSteps(visited.isEmpty(), e, distances.get(end) != DEFAULT_VALUE);
		}
	    });
	    timer.start();
	} else { // Using a while loop that doesn't render anything except the solution path
	    // While End Node hasn't been reached yet and there's still Nodes to view
	    while (distances.get(end) == DEFAULT_VALUE && !visited.isEmpty()) {
		solveUsingDijkstra(visited, showSteps, end);
	    }
	    // Displays specific solution (either MAGENTA path or "no solution" dialog box)
	    showSolutionOutputWithoutSteps(visited.isEmpty());
	}
    }

    /**
     * Helper method to do main algorithm; will be called both if user wants to see
     * steps or does not want to see steps
     */
    private void solveUsingDijkstra(Map<Node, Double> visited, boolean showSteps, Node end) {
	// Find Node with smallest distance from 'testDist' map, save it as var then
	// remove it
	Node lowestNodeD = end;
	double lowestDist = DEFAULT_VALUE;
	// Go through all Nodes
	for (Node n : visited.keySet()) {
	    if (visited.get(n) < lowestDist) {
		lowestDist = visited.get(n);
		lowestNodeD = n;
	    }
	}
	visited.remove(lowestNodeD);

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
		visited.put(v, currentDistToStart);
	    }
	}
    }

    // Calculate the distance between the current Node and the start Node.
    // Distance will be whatever parent's distance is, plus distance between
    // 2 Nodes
    private double calculateDistFromStart(Node current, Node parent) {
	return distances.get(parent) + calculateActualDistance(current, parent);
    }
}
