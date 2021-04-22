package algorithms;

import java.util.HashMap;
import java.util.Map;

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

	// For every Node, initialize default distance and parent
	for (int r = 0; r < GridPanel.ROWS; r++) {
	    for (int c = 0; c < GridPanel.COLUMNS; c++) {
		distances.put(mainGrid.getNode(r, c), -1);
		parents.put(mainGrid.getNode(r, c), null);
	    }
	}
	Node end = mainGrid.getEndNode();

	// Distance to source is always 0
	distances.put(start, 0);

	// Find shortest path for all Nodes
	for (int r = 0; r < GridPanel.ROWS; r++) {
	    for (int c = 0; c < GridPanel.COLUMNS; c++) {

		// Find Node with minimum distance value from Set of Nodes not currently on
		// shortest path tree
		int min;
		int minIndex = -1;
		for (int v = 0; v < 5; v++) {

		}

	    }
	}

    }

}
