package algorithms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private Map<Node, Double> distances; // Node to integers

    /**
     * This constructor conducts a Breath-First Search (BFS) on the provided grid,
     * where the solution can either be viewed as processed or just the final answer
     * will be displayed.
     * 
     * @param mainGrid  - The board that will have BFS conducted on it.
     * @param start     - The source Node where BFS will begin.
     * @param showSteps - Whether the solution can be seen or just final answer.
     */
    public BreathFirstSearch(GridPanel mainGrid, Node start, boolean showSteps) {
	this.mainGrid = mainGrid;
	this.start = start;
	this.end = mainGrid.getEndNode();
	this.parents = new HashMap<>();
	this.distances = new HashMap<>();

	// For every Node that isn't a barrier, initialize a default distance and parent
	fillDefaultValues(distances, DEFAULT_VALUE);

	// Keep track of visited Nodes
	Queue<Node> q = new LinkedList<>();
	distances.put(start, 0.0);
	q.add(start);

	// Display solution in 1 of 2 ways, depending on showSteps boolean
	if (showSteps) { // Using a timer that renders a Node every 0.01s until End Node is reached
	    Timer timer = new Timer(10, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    solveUsingBFS(q, showSteps);

		    // Displays specific solution (either MAGENTA path or "no solution" dialog box)
		    showSolutionOutputWithSteps(q.isEmpty(), e, distances.get(end) != DEFAULT_VALUE);
		}
	    });
	    timer.start();
	} else { // Using a while loop that doesn't render anything except the solution path
	    // While End Node hasn't been reached yet and there's still Nodes to view
	    while (distances.get(end) == DEFAULT_VALUE && !q.isEmpty()) {
		solveUsingBFS(q, showSteps);
	    }
	    // Displays specific solution (either MAGENTA path or "no solution" dialog box)
	    showSolutionOutputWithoutSteps(q.isEmpty());
	}

    }

    /**
     * Private helper method that performs BFS algorithm. This method will be called
     * both if the user wants to see the steps unfold or if they just want to see
     * the solution.
     * 
     * @param q         - Contains all the Nodes that still need to be visited.
     * @param showSteps - Whether the solution can be seen or just final answer.
     */
    private void solveUsingBFS(Queue<Node> q, boolean showSteps) {
	Node current = q.remove();
	visuallyCloseNode(current, showSteps);

	// For every node adjacent to the current Node
	for (Node v : mainGrid.getAdjacencyNodes(current)) {
	    // If Node v has not been visited, then visit it and adjust its distance and
	    // parent
	    if (distances.get(v) == DEFAULT_VALUE) {
		visuallyOpenNode(v, showSteps);
		distances.put(v, distances.get(current) + 1);
		parents.put(v, current);
		q.add(v);
	    }
	}
    }

}
