package algorithms;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import sections.board.GridPanel;
import sections.board.Node;

public class BreathFirstSearch extends Algorithm {
    private Node start;
    private Map<Node, Integer> distances; // Node to integers
    private Map<Node, Node> parents; // Node to Node

    Timer timer;

    /**
     * This constructor takes a board and source vertex as parameters and conducts a
     * breath-first search (BFS) on the graph.
     */
    public BreathFirstSearch(GridPanel mainGrid, Node start, boolean showSteps) {
	// Initializing all fields
	this.start = start;
	this.distances = new HashMap<>();
	this.parents = new HashMap<>();

	// For every Node, initialize default distance and parent
	for (int r = 0; r < GridPanel.ROWS; r++) {
	    for (int c = 0; c < GridPanel.COLUMNS; c++) {
		distances.put(mainGrid.getNode(r, c), -1);
		parents.put(mainGrid.getNode(r, c), null);
	    }
	}

	try {
	    // Keep track of "visited" vertices
	    Queue<Node> q = new LinkedList<>();
	    distances.put(start, 0);
	    q.add(start);

	    // While we haven't reached the END node yet
	    while (distances.get(mainGrid.getEndNode()) == -1) {
//		timer = new Timer(10, this);
//		timer.start();
		Node u = q.remove();
		if (!u.isStart() && showSteps) {
		    u.makeClosed();
		}

		// For every node adjacent to u
		for (Node v : mainGrid.getAdjacencyNodes(u)) {
		    // If v is not yet visited, then change its distance and parent accordingly
		    if (distances.get(v) == -1) {
			if (!v.isEnd() && showSteps) {
			    v.makeOpen();
			}
			distances.put(v, distances.get(u) + 1);
			parents.put(v, u);
			q.add(v);

			System.out.println("(" + v.getPosition()[0] + ", " + v.getPosition()[1] + ") = "
				+ distances.get(mainGrid.getNode(v.getPosition()[0], v.getPosition()[1])));
		    }
		}
	    }

	    // Change all Nodes part of solution accordingly
	    Node currentNode = parents.get(mainGrid.getEndNode());
	    while (currentNode != this.start) {
		currentNode.makePath();
		currentNode = parents.get(currentNode);
	    }
	} catch (NoSuchElementException e) { // Dialog box if no solution exists
	    JOptionPane.showMessageDialog(null, "The board has no solution.", "No Solutions",
		    JOptionPane.INFORMATION_MESSAGE);
	}
    }

    /**
     * This instance method finds the distance between the vertex parameter and the
     * source node.
     */
    public int getDistanceTo(Node v) {
	return this.distances.get(v);
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
