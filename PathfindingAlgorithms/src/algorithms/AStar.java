package algorithms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Timer;

import sections.board.GridPanel;
import sections.board.Node;

/**
 * 
 * The following AStar class conducts the A* pathfinding algorithm on a given
 * grid, displaying the shortest path from a start point to an end point.
 * 
 * @author Troy Zada
 *
 */

public class AStar extends Algorithm {

    private ArrayList<Node> openList; // Open Nodes
    private ArrayList<Node> closedList; // Closed Nodes

    /**
     * This constructor conducts an A* Search on the provided grid, where the
     * solution can either be viewed as it unfolds or just the final answer will be
     * displayed.
     * 
     * @param mainGrid  - The board that will have BFS conducted on it.
     * @param start     - The source Node where BFS will begin.
     * @param showSteps - Whether the solution can be seen or just final answer.
     */
    public AStar(GridPanel mainGrid, Node start, boolean showSteps) {
	this.mainGrid = mainGrid;
	this.start = start;
	this.end = mainGrid.getEndNode();
	this.parents = new HashMap<>();
	this.openList = new ArrayList<>();
	this.closedList = new ArrayList<>();

	// Calculates g, h, and f for start point (where g = 0) and adds Node to open
	Node current = this.start;
	current.setGCost(0);
	current.setHCost(calcHDistance(current));
	current.setFCost(calcFValue(current.getGCost(), current.getHCost()));
	openList.add(current);

	// Display solution in 1 of 2 ways, depending on showSteps boolean
	if (showSteps) { // Using a timer that renders a Node every 0.01s until End Node is reached
	    Timer timer = new Timer(10, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    Node current = getLowestF();
		    solveUsingAStar(current, showSteps);

		    // Displays specific solution (either MAGENTA path or "no solution" dialog box)
		    showSolutionOutputWithSteps(openList.isEmpty(), e, current.equals(end));
		}
	    });
	    timer.start();
	} else { // Using a while loop that doesn't render anything except the solution path
	    // While End Node hasn't been reached yet and there's still Nodes to view
	    while (!current.equals(end) && !openList.isEmpty()) {
		current = getLowestF();
		solveUsingAStar(current, showSteps);
	    }
	    // Displays specific solution (either MAGENTA path or "no solution" dialog box)
	    showSolutionOutputWithoutSteps(openList.isEmpty());
	}
    }

    /**
     * Private helper method that performs A* algorithm. This method will be called
     * both if the user wants to see the steps unfold or if they just want to see
     * the solution.
     * 
     * @param current   - Current Node to be checked.
     * @param showSteps - Whether the solution can be seen or just final answer.
     */
    private void solveUsingAStar(Node current, boolean showSteps) {
	visuallyCloseNode(current, showSteps);

	// For every Node adjacent to the current Node
	for (Node v : mainGrid.getAdjacencyNodes(current)) {
	    // If Node not in open or closed Lists, then add to open
	    if (!openList.contains(v) && !closedList.contains(v)) {
		visuallyOpenNode(v, showSteps);
		openList.add(v);
	    }
	    // Do g, h, and f calculations for Node v
	    double currentG = calcGDistance(v, current);
	    v.setHCost(calcHDistance(v));
	    double currentF = calcFValue(currentG, v.getHCost());

	    // If new F value is less than the Node's existing F value, then update existing
	    // G, F, and parent
	    if (currentF < v.getFCost()) {
		v.setGCost(currentG);
		v.setFCost(currentF);
		parents.put(v, current);
	    }

	}
	// Add current Node to closed List and remove from open
	closedList.add(current);
	openList.remove(current);
    }

    /**
     * Goes through all the Nodes in the List, finding the Node with the smallest
     * f-cost, and returning it.
     * 
     * @return - The Node with the smallest f-cost.
     */
    private Node getLowestF() {
	Node lowestFNode = end;
	double lowestFVal = end.getFCost();
	// Go through all Nodes checking if the next Node has a smaller distance than
	// the previous one
	for (Node n : openList) {
	    if (n.getFCost() < lowestFVal) {
		lowestFNode = n;
		lowestFVal = n.getFCost();
	    }
	}
	return lowestFNode;
    }

    /**
     * Calculates the actual distance, g, between the current Node and the start
     * Node (including all obstacles). The distance will be whatever the parent's
     * distance is, plus the distance between the 2 Nodes.
     * 
     * @param current  - The Node that we are calculating the distance of.
     * @param previous - The Node that comes before the current Node.
     * @return - The total distance between the current and the start Node.
     */
    private double calcGDistance(Node current, Node previous) {
	return previous.getGCost() + calculateDistanceBetweenNodes(current, previous);
    }

    /**
     * Calculates the heuristic distance, h, between the current Node and the end
     * Node (excluding all obstacles). The distance will be the direct distance
     * between the 2 Nodes, assuming there are no obstacles between them.
     * 
     * @param current - The Node that we are calculating the distance of.
     * @return - The total heuristic distance between the current and the end Node.
     */
    private double calcHDistance(Node current) {
	return calculateDistanceBetweenNodes(current, end);
    }

    // Calculates heuristic distance, h (finds distance between current Node
    // and end Node assuming that no obstacles exist)
    /**
     * Calculates the F-cost for the Node, being f = g + h. This cost determines
     * which direction the path will be heading.
     * 
     * @param g - G-cost, actual distance between current and start Node.
     * @param h - H-cost, heuristic distance between current and end Node.
     * @return - The total F-cost for proceeding in that direction.
     */
    private double calcFValue(double g, double h) {
	return g + h;
    }

}
