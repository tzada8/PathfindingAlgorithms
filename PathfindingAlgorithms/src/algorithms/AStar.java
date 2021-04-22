package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import sections.board.GridPanel;
import sections.board.Node;

public class AStar extends Algorithm {
    private GridPanel mainGrid;
    private Node start;
    private Map<Node, Node> parents; // Node to Node
    private HashMap<Node, Integer> fMappings; // Open Nodes
    private ArrayList<Node> openList; // Open Nodes
    private ArrayList<Node> closedList; // Closed Nodes

    public AStar(GridPanel mainGrid, Node start, boolean showSteps) {
	// Initialize all values
	this.mainGrid = mainGrid;
	this.start = start;
	this.parents = new HashMap<>();
	this.fMappings = new HashMap<>();
	this.openList = new ArrayList<>();
	this.closedList = new ArrayList<>();

	Node current = start;

	Node end = mainGrid.getEndNode();
	// Calculates g, h, and f for start point (g = 0)
	int startH = calcHDistance(current, end);
	int startF = calcFValue(0, startH);

	fMappings.put(current, startF);
	openList.add(current);

	while (!current.equals(end)) {
	    if (!current.isStart() && showSteps) {
		current.makeClosed();
	    }

	    // For every node adjacent to u
	    for (Node v : mainGrid.getAdjacencyNodes(current)) {
		// If Node not in open or close Lists, then add to open
		if (!openList.contains(v) && !closedList.contains(v)) {
		    fMappings.put(v, -1);
		    openList.add(v);
		    if (!v.isEnd() && showSteps) {
			v.makeOpen();
		    }
		}
		// Do g, h, and f calculations
		int currentG = calcGDistance(v);
		int currentH = calcHDistance(v, end);
		int currentF = calcFValue(currentG, currentH);

		// If new F value is greeater than existing then update existing f and parent
		if (currentF < startF) {
		    startF = currentF;
		    fMappings.put(v, currentF);
		    parents.put(current, v);
		}
		System.out.println("(" + v.getPosition()[0] + ", " + v.getPosition()[1] + ") = " + startF);
	    }
	    closedList.add(current);
	    for (Node s : openList) {
		System.out.println(
			"(" + s.getPosition()[0] + ", " + s.getPosition()[1] + ") = " + startF + " (in openList)");
	    }

	    current = getLowestF();

	}
	drawPath(end);
    }

    // Calculates heuristic distance, h (finds distance between current Node
    // and end Node assuming that no obstacles exist)
    private int calcGDistance(Node current) {
	// Determine number of columns between current Node and the start
	int dx = Math.abs(current.getPosition()[1] - start.getPosition()[1]);
	// Determine number of rows between current Node and the start
	int dy = Math.abs(current.getPosition()[0] - start.getPosition()[0]);
	// Actual calculated ditance (pythagorean theorem --> a^2 + b^2 = c^2)
//	double dt = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

	return dx + dy;
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

    private Node getLowestF() {
	// Find Node with lowest F value
	Node lowestNodeF = openList.get(0);
	int lowestDist = fMappings.get(lowestNodeF);
	// Go through all Nodes
	for (Node n : openList) {
	    if (fMappings.get(n) < lowestDist) {
		lowestDist = fMappings.get(n);
		lowestNodeF = n;
	    }
	}
	openList.remove(lowestNodeF);
	return lowestNodeF;
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
