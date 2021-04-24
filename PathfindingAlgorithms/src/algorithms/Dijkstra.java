package algorithms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

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
	HashMap<Node, Double> nodesToVisit = new HashMap<>();
	nodesToVisit.put(start, 0.0);
	distances.put(start, 0.0);

	// Display solution in 1 of 2 ways, depending on showSteps boolean
	if (showSteps) { // Using a timer that renders a Node every 0.01s until End Node is reached
	    Timer timer = new Timer(10, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    solveUsingDijkstra(nodesToVisit, showSteps);

		    // Displays specific solution (either MAGENTA path or "no solution" dialog box)
		    showSolutionOutputWithSteps(nodesToVisit.isEmpty(), e, distances.get(end) != DEFAULT_VALUE);
		}
	    });
	    timer.start();
	} else { // Using a while loop that doesn't render anything except the solution path
	    // While End Node hasn't been reached yet and there's still Nodes to view
	    while (distances.get(end) == DEFAULT_VALUE && !nodesToVisit.isEmpty()) {
		solveUsingDijkstra(nodesToVisit, showSteps);
	    }
	    // Displays specific solution (either MAGENTA path or "no solution" dialog box)
	    showSolutionOutputWithoutSteps(nodesToVisit.isEmpty());
	}
    }

    /**
     * Private helper method that performs Dijkstra algorithm. This method will be
     * called both if the user wants to see the steps unfold or if they just want to
     * see the solution.
     * 
     * @param visited   - Contains all the Nodes that still need to be visited.
     * @param showSteps - Whether the solution can be seen or just final answer.
     */
    private void solveUsingDijkstra(Map<Node, Double> nodesToVisit, boolean showSteps) {
	Node current = getSmallestDist(nodesToVisit);
	visuallyCloseNode(current, showSteps);

	// For every Node adjacent to the current Node
	for (Node v : mainGrid.getAdjacencyNodes(current)) {
	    // If Node v has not been visited, then visit it and calculate its distance
	    if (distances.get(v) == DEFAULT_VALUE) {
		visuallyOpenNode(v, showSteps);
		double updatedDistToStart = calculateDistFromStart(v, current);
		// If updated distance is less than its previous know distance, then update
		// Node's distance and parent
		if (updatedDistToStart < distances.get(v)) {
		    distances.put(v, updatedDistToStart);
		    parents.put(v, current);
		}
		nodesToVisit.put(v, updatedDistToStart);
	    }
	}
    }

    /**
     * Goes through all the Nodes in the Map, finding the Node with the smallest
     * distance, removing it from the Map, and returning it.
     * 
     * @param nodesToVisit - Map of all Nodes to be visited pointing to their
     *                     current distance.
     * @return - The Node with the lowest distance.
     */
    private Node getSmallestDist(Map<Node, Double> nodesToVisit) {
	Node lowestDistNode = end;
	double lowestDist = distances.get(lowestDistNode);
	// Go through all Nodes checking if the next Node has a smaller distance than
	// the previous one
	for (Node n : nodesToVisit.keySet()) {
	    if (nodesToVisit.get(n) < lowestDist) {
		lowestDist = nodesToVisit.get(n);
		lowestDistNode = n;
	    }
	}
	// Remove and return Node with lowest distance
	nodesToVisit.remove(lowestDistNode);
	return lowestDistNode;
    }

    /**
     * Calculates the distance between the current Node and the start Node. The
     * distance will be whatever the parent's distance is, plus the distance between
     * the 2 Nodes.
     * 
     * @param current  - The Node that we are calculating the distance of.
     * @param previous - The Node that comes before the current Node.
     * @return - The total distance between the current and the start Node.
     */
    private double calculateDistFromStart(Node current, Node previous) {
	return distances.get(previous) + calculateDistanceBetweenNodes(current, previous);
    }

}
