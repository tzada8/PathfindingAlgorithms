package algorithms;

import java.util.Set;

import sections.board.GridPanel;
import sections.board.Node;

public class AStar extends Algorithm {

    public AStar(GridPanel mainGrid, Node start) {
	System.out.println("In A* Class");

	Set<Node> test = mainGrid.getAdjacencyNodes(start);
	for (Node node : test) {
	    System.out.println(node.getPosition()[0] + ", " + node.getPosition()[1]);
	}
    }

}
