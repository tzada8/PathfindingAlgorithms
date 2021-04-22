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
    private GridPanel mainGrid;
    private Node start;
    private Map<Node, Integer> distances; // Node to integers
    private Map<Node, Node> parents; // Node to Node

    public Dijkstra(GridPanel mainGrid, Node start, boolean showSteps) {
	// Initialize all values
	this.mainGrid = mainGrid;
	this.start = start;
	this.distances = new HashMap<>();
	this.parents = new HashMap<>();

	// For every Node that isn't a barrier, initialize a default distance and parent
	for (int r = 0; r < GridPanel.ROWS; r++) {
	    for (int c = 0; c < GridPanel.COLUMNS; c++) {
		if (!mainGrid.getNode(r, c).isBarrier()) {
		    distances.put(mainGrid.getNode(r, c), Integer.MAX_VALUE);
		    parents.put(mainGrid.getNode(r, c), null);
		}
	    }
	}
	Node end = mainGrid.getEndNode();

	// Keep track of "visited" vertices and their current distance
	HashMap<Node, Integer> testDist = new HashMap<>();
	testDist.put(start, 0);
	// Distance from source to itself is always 0
	distances.put(start, 0);

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
			} else if (distances.get(end) != Integer.MAX_VALUE) {
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
		while (distances.get(end) == Integer.MAX_VALUE) {
		    solveUsingDijkstra(testDist, showSteps, end);
		}
		drawPath(end);
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
    private void solveUsingDijkstra(Map<Node, Integer> testDist, boolean showSteps, Node end) {
//	Node u = q.remove();

	// Find Node with smallest distance from known distances
	Node lowestNodeD = end;
	int lowestDist = Integer.MAX_VALUE;
	// Go through all Nodes
	for (Node n : testDist.keySet()) {
	    System.out.println(n.getPosition()[0] + ", " + n.getPosition()[1] + " = " + testDist.get(n));
	    if (testDist.get(n) < lowestDist) {
		lowestDist = testDist.get(n);
		lowestNodeD = n;
	    }
	}
	testDist.remove(lowestNodeD);

	if (!lowestNodeD.isStart() && showSteps) {
	    lowestNodeD.makeClosed();
	}

	// For every node adjacent to u
	for (Node v : mainGrid.getAdjacencyNodes(lowestNodeD)) {
	    // If v is not yet visited, then change its distance and parent accordingly
	    if (distances.get(v) == Integer.MAX_VALUE) {
		if (!v.isEnd() && showSteps) {
		    v.makeOpen();
		}

		// Calc dist from start for each unvisited Node
		int currentDistToStart = calculateDistFromStart(v, lowestNodeD);
		// If newly calculated distance is less than its previous know distance,
		// then update its distance and parent
		if (currentDistToStart < distances.get(v)) {
		    distances.put(v, currentDistToStart);
		    parents.put(v, lowestNodeD);
		}
		testDist.put(v, currentDistToStart);
//		    q.add(v);

		System.out.println("(" + v.getPosition()[0] + ", " + v.getPosition()[1] + ") = "
			+ distances.get(mainGrid.getNode(v.getPosition()[0], v.getPosition()[1])));
	    }
	}
    }

    // WILL PROBABLY ADJUST THIS FOR DIAGONAL DISTANCES TOO

    // Calculate the distance between the current Node and the start Node
    // E.g. if dx = 5, and dy = 10, then will need to move 5 columns over
    // from start Node and 10 rows down to get to the current Node
    // (total of 15 block to move)
    private int calculateDistFromStart(Node current, Node parent) {
	// Determine number of columns between current Node and the start
	int dx = Math.abs(current.getPosition()[1] - parent.getPosition()[1]);
	// Determine number of rows between current Node and the start
	int dy = Math.abs(current.getPosition()[0] - parent.getPosition()[0]);
	// Actual calculated ditance (pythagorean theorem --> a^2 + b^2 = c^2)
	double dt = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

	return distances.get(parent) + dx + dy;
    }

    private void drawPath(Node end) {
	// Change all Nodes part of solution accordingly
	Node currentNode = parents.get(end);
	while (currentNode != start) {
	    currentNode.makePath();
	    currentNode = parents.get(currentNode);
	}
    }
}
