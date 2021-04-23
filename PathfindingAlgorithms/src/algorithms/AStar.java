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
    private GridPanel mainGrid;
    private Node start;
    private Map<Node, Node> parents; // Node to Node
//    private HashMap<Node, Integer> openList; // Open Nodes to F value
    private ArrayList<Node> openTest;
    private ArrayList<Node> closedList; // Closed Nodes

    public AStar(GridPanel mainGrid, Node start, boolean showSteps) {
	// Initialize all values
	this.mainGrid = mainGrid;
	this.start = start;
	this.parents = new HashMap<>();
//	this.openList = new HashMap<>();
	this.openTest = new ArrayList<>();
	this.closedList = new ArrayList<>();

	Node current = start;

	Node end = mainGrid.getEndNode();
	// Calculates g, h, and f for start point (g = 0)
	current.setGCost(0);
	current.setHCost(calcHDistance(current, end));
	int startH = calcHDistance(current, end);
	current.setFCost(calcFValue(0, startH));
	int startF = calcFValue(current.getGCost(), startH);

	openTest.add(current);
//	openList.put(current, startF);
	parents.put(start, null);

	// Solution will be found in 2 ways depending if user wants to see solution
	// 1. Using a timer that renders the Node every 0.01s until the END node is
	// reached
	// 2. Using a while loop that doesn't render anything except the solution path
	if (showSteps) {
	    Timer timer = new Timer(1000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    try {
			// Remove Node with lowest F value from open List and make it current
			Node current = getLowestF(end);
			if (!current.isStart() && !current.isEnd() && showSteps) {
			    current.makeClosed();
			}

			// For every node adjacent to u
			for (Node v : mainGrid.getAdjacencyNodes(current)) {
			    // If Node not in open or closed Lists, then add to open
			    if (/* !openList.containsKey(v) */ !openTest.contains(v) && !closedList.contains(v)) {
				openTest.add(v);
//				    openList.put(v, Integer.MAX_VALUE);
				if (!v.isEnd() && showSteps) {
				    v.makeOpen();
				}
			    }
			    // Do g, h, and f calculations for Node v
			    int currentG = calcGDistance(v, current); // G
			    int currentH = calcHDistance(v, end); // H
			    v.setHCost(currentH);
			    int currentF = calcFValue(currentG, currentH); // F

			    // If new F value is less than the Node's existing F value, then update existing
			    // f and parent
			    System.out.println("currentF = " + currentF);
			    System.out.println("v.getFCost() = " + v.getFCost());
			    if (currentF < v.getFCost()) {
				v.setGCost(currentG);
				v.setFCost(currentF);
				parents.put(v, current);
				System.out.println();
				System.out.println(v.getPosition()[0] + ", " + v.getPosition()[1]);
				System.out.println(current.getPosition()[0] + ", " + current.getPosition()[1]);
			    }

			    System.out.println("(" + v.getPosition()[0] + ", " + v.getPosition()[1] + ") = " + startF);

			}
			// Add current Node to closed List and remove from open
			closedList.add(current);
			openTest.remove(current);
//			    openList.remove(current);

//			    for (Node s : openList.keySet()) {
//				System.out.println("(" + s.getPosition()[0] + ", " + s.getPosition()[1] + ") = " + openList.get(s)
//					+ " (in openList)");
//			    }

			System.out.println(current.getPosition()[0] + ", " + current.getPosition()[1]);

			System.out.println(current.getPosition()[0] + ", " + current.getPosition()[1]);

			if (openTest.isEmpty()) {
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
		    if (!current.isStart() && showSteps) {
			current.makeClosed();
		    }

		    // For every node adjacent to u
		    for (Node v : mainGrid.getAdjacencyNodes(current)) {
			// If Node not in open or closed Lists, then add to open
			if (/* !openList.containsKey(v) */ !openTest.contains(v) && !closedList.contains(v)) {
			    openTest.add(v);
//			    openList.put(v, Integer.MAX_VALUE);
			    if (!v.isEnd() && showSteps) {
				v.makeOpen();
			    }
			}
			// Do g, h, and f calculations for Node v
			int currentG = calcGDistance(v, current); // G
			int currentH = calcHDistance(v, end); // H
			v.setHCost(currentH);
			int currentF = calcFValue(currentG, currentH); // F

			// If new F value is less than the Node's existing F value, then update existing
			// f and parent
			System.out.println("currentF = " + currentF);
			System.out.println("v.getFCost() = " + v.getFCost());
			if (currentF < v.getFCost()) {
			    v.setGCost(currentG);
			    v.setFCost(currentF);
			    parents.put(v, current);
			    System.out.println();
			    System.out.println(v.getPosition()[0] + ", " + v.getPosition()[1]);
			    System.out.println(current.getPosition()[0] + ", " + current.getPosition()[1]);
			}

			System.out.println("(" + v.getPosition()[0] + ", " + v.getPosition()[1] + ") = " + startF);

		    }
		    // Add current Node to closed List and remove from open
		    closedList.add(current);
		    openTest.remove(current);
//		    openList.remove(current);

//		    for (Node s : openList.keySet()) {
//			System.out.println("(" + s.getPosition()[0] + ", " + s.getPosition()[1] + ") = " + openList.get(s)
//				+ " (in openList)");
//		    }

		    System.out.println(current.getPosition()[0] + ", " + current.getPosition()[1]);

		    // Remove Node with lowest F value from open List and make it current
		    current = getLowestF(end);

		    System.out.println(current.getPosition()[0] + ", " + current.getPosition()[1]);

		}
		drawPath(end);
		mainGrid.resetAllCosts();
	    } catch (NoSuchElementException e) { // Dialog box if no solution exists
		JOptionPane.showMessageDialog(null, "The board has no solution.", "No Solutions",
			JOptionPane.INFORMATION_MESSAGE);
	    }
	}
    }

    // Calculates actual distance from start, g (finds distance between current Node
    // and start Node including all present obstacles)
    private int calcGDistance(Node current, Node previous) {
	int tempG = previous.getGCost();
	int dx = Math.abs(current.getPosition()[1] - previous.getPosition()[1]);
	int dy = Math.abs(current.getPosition()[0] - previous.getPosition()[0]);
	return tempG + dx + dy;
    }

    // Calculates heuristic distance, h (finds distance between current Node
    // and end Node assuming that no obstacles exist)
    private int calcHDistance(Node current, Node end) {
	// Determine number of columns between current Node and the start
	int dx = Math.abs(current.getPosition()[1] - end.getPosition()[1]);
	// Determine number of rows between current Node and the start
	int dy = Math.abs(current.getPosition()[0] - end.getPosition()[0]);
	// Actual calculated ditance (pythagorean theorem --> a^2 + b^2 = c^2)
//	double dt = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

	return dx + dy;
    }

    // Calculates heuristic distance, h (finds distance between current Node
    // and end Node assuming that no obstacles exist)
    private int calcFValue(int g, int h) {
	return g + h;
    }

    // Find Node with lowest F value
    private Node getLowestF(Node end) {
	// Default lowestNodeF to be end Node
	Node lowestNodeF = end;
	int lowestFVal = Integer.MAX_VALUE;
	// Go through all Nodes
	for (Node n : openTest) {
	    if (n.getFCost() < lowestFVal) {
		lowestNodeF = n;
		lowestFVal = n.getFCost();
	    }
	}
	return lowestNodeF;
    }

    private void drawPath(Node end) {
	// Change all Nodes part of solution accordingly
	Node currentNode = parents.get(end);
	System.out.println();
	System.out.println(end.getPosition()[0] + ", " + end.getPosition()[1]);
	System.out.println(currentNode.getPosition()[0] + ", " + currentNode.getPosition()[1]);
	while (!currentNode.equals(start)) {
	    System.out.println(currentNode.getPosition()[0] + ", " + currentNode.getPosition()[1]);
	    currentNode.makePath();
	    currentNode = parents.get(currentNode);
	}
    }

}
