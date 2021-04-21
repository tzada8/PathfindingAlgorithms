package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import sections.board.GridPanel;
import sections.board.Node;

public class DepthFirstSearch {
    private GridPanel mainGrid;
    private Node start;
    private Map<Node, Node> parents; // Parent of each vertex

    /**
     * This constructor takes a graph and source vertex as parameters and conducts a
     * depth-first search (DFS) on the graph.
     */
    public DepthFirstSearch(GridPanel mainGrid, Node start, boolean showSteps) {
	// Initializing all fields
	this.start = start;
	this.mainGrid = mainGrid;
	this.parents = new HashMap<>();
	Set<Node> visited = new HashSet<>(); // For visited Nodes

	// For every vertex that hasn't been visited yet (it's not in visited set yet),
	// conduct DFS
	Node end = mainGrid.getEndNode();
	while (!visited.contains(end)) {
	    dfsVisit(start, visited);

	}

//	for (int v : this.graph.getVertices()) {
//	    if (!visited.contains(v)) {
//		dfsVisit(v, visited);
//	    }
//	}
    }

    /**
     * This private helper method is called in the DFSTree constructor, and is the
     * main code that determines the connected components of the graph.
     */
    private void dfsVisit(Node u, Set<Node> visited) {
	// Initialize empty stack listing all vertices that need to be visited
	Stack<Node> verticesToVisit = new Stack<Node>();
	verticesToVisit.push(u);
	visited.add(u);

	Node end = mainGrid.getEndNode();
	while (!visited.contains(end)) { // Keep going until END Node is found
	    u = verticesToVisit.pop();
	    for (Node w : mainGrid.getAdjacencyNodes(u)) {
		System.out.println("Current Node " + w.getPosition()[0] + ", " + w.getPosition()[1]);
		// If vertex "w" hasn't been visited yet, then add "u" back to
		// stack, with "w" on top, and say that "w" has been visited
		if (!visited.contains(w)) {
		    this.parents.put(w, u);
		    verticesToVisit.push(u);
		    verticesToVisit.push(w);
		    visited.add(w);
		    if (!w.isEnd()) {
			w.makeOpen();
		    }
		    break;
		}

		if (!w.isEnd()) {
		    w.makeOpen();
		}

		int curR = w.getPosition()[0];
		int curC = w.getPosition()[1];
		Node parentSpot = parents.get(mainGrid.getNode(curR, curC));
		int parR = parentSpot.getPosition()[0];
		int parC = parentSpot.getPosition()[1];
		System.out.println("(" + curR + ", " + curC + ") = parent @ (" + parR + ", " + parC + ")");
	    }
	}
    }

    /**
     * This instance method finds the parent vertex to the given parameter vertex.
     */
    public Node getParent(Node v) {
	return this.parents.get(v);
    }

    /**
     * This instance method returns the source node.
     */
    public Node getStart() {
	return this.start;
    }
}
