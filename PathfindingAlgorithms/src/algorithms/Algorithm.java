package algorithms;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import sections.board.Node;

public class Algorithm {
    // Generic algorithm with features that all specific algorithms have in common
    // All specific algorithms extends from this class
    private Node[][] map;

    /*
     * Returns all the adjacent Nodes to Node v that are available (WHITE Nodes).
     */
    public Set<Node> getAdjacencyNodes(Node v) {
	// FOR NOW JUST DOING 4 ADJACENT NODES --> FUTURE MIGHT DO CORNERS OF NODE v TOO

	// v --> r=10 c=15 col=WHITE

	// 1. r = 9, c = 15
	// 2. r = 10, c = 14
	// 3. r = 10, c = 16
	// 4. r = 11, c = 15
	Set<Node> adjacentNodes = new HashSet<Node>();

	// Go through top and bottom Nodes
	for (int r = v.getPosition()[0] - 1; r <= v.getPosition()[0] + 1; r++) {
	    if (map[r][v.getPosition()[1]].isAvailable()) {
		adjacentNodes.add(map[r][v.getPosition()[1]]);
	    }
	}

	// Go through left and right Nodes
	for (int c = v.getPosition()[1] - 1; c <= v.getPosition()[1] + 1; c++) {
	    if (map[v.getPosition()[0]][c].isAvailable()) {
		adjacentNodes.add(map[v.getPosition()[0]][c]);
	    }
	}

	return adjacentNodes;
    }
}
