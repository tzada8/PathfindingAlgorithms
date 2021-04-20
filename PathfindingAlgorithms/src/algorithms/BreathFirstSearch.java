package algorithms;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import sections.board.GridPanel;
import sections.board.Node;

public class BreathFirstSearch {
    private Node start;
    private Map<Node, Integer> distances; // Node to integers
    private Map<Node, Node> parents; // Node to Node

    /**
     * This constructor takes a board and source vertex as parameters and conducts a
     * breath-first search (BFS) on the graph.
     */
    public BreathFirstSearch(GridPanel mainGrid, Node start) {
	// Initializing all fields
	this.start = start;
	this.distances = new HashMap<>();
	this.parents = new HashMap<>();

	// For every Node, initialize default distance and parent
	for (int r = 0; r < GridPanel.ROWS; r++) {
	    for (int c = 0; c < GridPanel.COLUMNS; c++) {
		this.distances.put(mainGrid.getNode(r, c), -1);
		this.parents.put(mainGrid.getNode(r, c), null);
	    }
	}

	// Keep track of "visited" vertices
	Queue<Node> q = new LinkedList<>();
	this.distances.put(start, 0);
	q.add(start);

	// While we haven't reached END node yet
	while (distances.get(mainGrid.getEndNode()) == -1) {
	    Node u = q.remove();
	    // For every node adjacent to u
	    for (Node v : mainGrid.getAdjacencyNodes(u)) {
		// If v is not yet visited, then change its distance and parent accordingly
		if (this.distances.get(v) == -1) {
		    this.distances.put(v, this.distances.get(u) + 1);
		    this.parents.put(v, u);
		    q.add(v);

		    System.out.println("(" + v.getPosition()[0] + ", " + v.getPosition()[1] + ") = "
			    + distances.get(mainGrid.getNode(v.getPosition()[0], v.getPosition()[1])));
		}
	    }
	}

//	for (int r = 0; r < 31; r++) {
//	    for (int c = 0; c < 31; c++) {
//		System.out.println("(" + r + ", " + c + ") = " + distances.get(mainGrid.getNode(r, c)));
//	    }
//	}
    }

    /**
     * This instance method finds the distance between the vertex parameter and the
     * source node. E.g. if there are "X" nodes between "A" and "B", then when
     * calling this method on "B", it will return "X".
     */
    public int getDistanceTo(Node v) {
	return this.distances.get(v);
    }

    /**
     * This instance method finds the parent vertex to the given parameter vertex,
     * "v". E.g. if "A" is a parent to "B", then when calling this method on "B", it
     * will return "A".
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
