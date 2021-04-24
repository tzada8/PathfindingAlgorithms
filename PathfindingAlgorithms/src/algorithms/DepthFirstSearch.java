package algorithms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import javax.swing.Timer;

import sections.board.GridPanel;
import sections.board.Node;

/**
 * 
 * The following DepthFirstSearch class conducts the Depth-First Search (DFS)
 * pathfinding algorithm on a given grid, going depth-first when solving; not
 * beneficial for pathfinding, but useful for determining number of connected
 * components.
 * 
 * @author Troy Zada
 *
 */

public class DepthFirstSearch extends Algorithm {

    /**
     * This constructor conducts a Depth-First Search (DFS) on the provided grid,
     * where the solution can either be viewed as it unfolds or just the final
     * answer will be displayed.
     * 
     * @param mainGrid  - The board that will have BFS conducted on it.
     * @param start     - The source Node where BFS will begin.
     * @param showSteps - Whether the solution can be seen or just final answer.
     */
    public DepthFirstSearch(GridPanel mainGrid, Node start, boolean showSteps) {
	this.mainGrid = mainGrid;
	this.start = start;
	this.end = mainGrid.getEndNode();
	this.parents = new HashMap<>();
	Set<Node> visited = new HashSet<>(); // Set of all visited Nodes

	// Initialize empty Stack listing all Nodes that will be visited
	Stack<Node> nodesToVisit = new Stack<Node>();
	nodesToVisit.push(start);
	visited.add(start);

	// Display solution in 1 of 2 ways, depending on showSteps boolean
	if (showSteps) { // Using a timer that renders a Node every 0.01s until End Node is reached
	    Timer timer = new Timer(10, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    solveUsingDFS(nodesToVisit, visited, showSteps);

		    // Displays specific solution (either MAGENTA path or "no solution" dialog box)
		    showSolutionOutputWithSteps(nodesToVisit.isEmpty(), e, visited.contains(end));
		}
	    });
	    timer.start();
	} else { // Using a while loop that doesn't render anything except the solution path
	    // While End Node hasn't been reached yet and there's still Nodes to view
	    while (!visited.contains(end) && !nodesToVisit.isEmpty()) {
		solveUsingDFS(nodesToVisit, visited, showSteps);
	    }
	    // Displays specific solution (either MAGENTA path or "no solution" dialog box)
	    showSolutionOutputWithoutSteps(nodesToVisit.isEmpty());
	}
    }

    /**
     * Private helper method that performs DFS algorithm. This method will be called
     * both if the user wants to see the steps unfold or if they just want to see
     * the solution.
     * 
     * @param s         - Contains all the Nodes that still need to be visited.
     * @param visited   - Contains all the Nodes that have been visited so far.
     * @param showSteps - Whether the solution can be seen or just final answer.
     */
    private void solveUsingDFS(Stack<Node> s, Set<Node> visited, boolean showSteps) {
	Node current = s.pop();
	visuallyCloseNode(current, showSteps);

	// For every Node adjacent to the current Node
	for (Node v : mainGrid.getAdjacencyNodes(current)) {
	    // If Node v has not been visited, then put back current Node into Stack with
	    // Node v on top, saying v has been visited
	    if (!visited.contains(v)) {
		visuallyOpenNode(v, showSteps);
		this.parents.put(v, current);
		s.push(current);
		s.push(v);
		visited.add(v);
		break;
	    }
	}
    }

}
