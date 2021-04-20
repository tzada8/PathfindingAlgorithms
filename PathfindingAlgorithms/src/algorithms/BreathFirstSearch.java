package algorithms;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import sections.board.GridPanel;
import sections.board.Node;

public class BreathFirstSearch {
    private Node[][] map;
    private Node start;
    private Map<Node, Integer> distances; // Node to integers
    private Map<Node, Node> parents; // Node to Node

    /**
     * This constructor takes a board and source vertex as parameters and conducts a
     * breath-first search (BFS) on the graph.
     */
    public BreathFirstSearch(Node[][] map, Node start) {
	// Initializing all fields
	this.map = map;
	this.start = start;
	this.distances = new HashMap<>();
	this.parents = new HashMap<>();

	// For every Node, initialize default distance and parent
	for (int r = 0; r <= GridPanel.ROWS; r++) {
	    for (int c = 0; c <= GridPanel.COLUMNS; c++) {
		this.distances.put(map[r][c], -1);
		this.parents.put(map[r][c], null);
	    }
	}

	// Keep track of "visited" vertices
	Queue<Node> q = new LinkedList<>();
	this.distances.put(start, 0);
	q.add(start);

	while (!q.isEmpty()) {
	    Node u = q.remove();
	    // For every vertex adjacent to u
	    for (int v : graph.getAdjacencyList(u)) {
		// If v is not yet visited, then change its distance and
		// parent accordingly
		if (this.distances.get(v) == -1) {
		    this.distances.put(v, this.distances.get(u) + 1);
		    this.parents.put(v, u);
		    q.add(v);
		}
	    }
	}
    }

    /**
     * This instance method finds the distance between the vertex parameter and the
     * source node. E.g. if there are "X" nodes between "A" and "B", then when
     * calling this method on "B", it will return "X".
     */
    public int getDistanceTo(int v) {
	return this.distances.get(v);
    }

    /**
     * This instance method finds the parent vertex to the given parameter vertex,
     * "v". E.g. if "A" is a parent to "B", then when calling this method on "B", it
     * will return "A".
     */
//    public int getParent(int v) {
//	return this.parents.get(v);
//    }

    /**
     * This instance method returns the graph.
     */
    public int[][] getBoard() {
	return this.map;
    }

    /**
     * This instance method returns the source node.
     */
    public Node getStart() {
	return this.start;
    }

}
