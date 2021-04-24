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

    private static final double DEFAULT_VALUE = Double.MAX_VALUE;

    private Map<Node, Double> distances; // Node to integers

    /**
     * This constructor takes a board and source vertex as parameters and conducts a
     * breath-first search (BFS) on the graph.
     */
    public BreathFirstSearch(GridPanel mainGrid, Node start, boolean showSteps) {
	// Initializing all fields
	this.mainGrid = mainGrid;
	this.start = start;
	this.end = mainGrid.getEndNode();
	this.distances = new HashMap<>();
	this.parents = new HashMap<>();

	// For every Node that isn't a barrier, initialize a default distance and parent
	fillDefaultValues(distances, DEFAULT_VALUE);

	// Keep track of "visited" vertices
	Queue<Node> q = new LinkedList<>();
	distances.put(start, 0.0);
	q.add(start);

	// Solution will be found in 2 ways depending if user wants to see solution
	// 1. Using a timer that renders the Node every 0.01s until the END node is
	// reached
	// 2. Using a while loop that doesn't render anything except the solution path
	if (showSteps) {
	    Timer timer = new Timer(10, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    try {
			solveUsingBFS(q, showSteps); // Solve grid using BFS

			if (q.isEmpty()) {
			    // If Queue is empty, then we ran out of options, so no solution found
			    ((Timer) e.getSource()).stop();
			    throw new NoSuchElementException("Force catch block");
			} else if (distances.get(end) != DEFAULT_VALUE) {
			    // If END Node's distance is not default -1, then we reached it, so draw
			    // solution
			    ((Timer) e.getSource()).stop();
			    drawPath(end);
			}
		    } catch (NoSuchElementException e1) { // Dialog box if no solution exists
			JOptionPane.showMessageDialog(null, "The board has no solution.", "No Solutions",
				JOptionPane.INFORMATION_MESSAGE);
		    }
		}
	    });
	    timer.start();
	} else {
	    // Try solving board, but if it has no solutions, then catch as error
	    try {
		// While we haven't reached the END node yet
		while (distances.get(end) == DEFAULT_VALUE) {
		    solveUsingBFS(q, showSteps);
		}
		drawPath(end);
	    } catch (NoSuchElementException e) { // Dialog box if no solution exists
		JOptionPane.showMessageDialog(null, "The board has no solution.", "No Solutions",
			JOptionPane.INFORMATION_MESSAGE);
	    }
	}

    }

    /**
     * Helper method to do main algorithm; will be called both if user wants to see
     * steps or does not want to see steps
     */
    private void solveUsingBFS(Queue<Node> q, boolean showSteps) {
	Node u = q.remove();
	visuallyCloseNode(u, showSteps);

	// For every node adjacent to u
	for (Node v : mainGrid.getAdjacencyNodes(u)) {
	    // If v is not yet visited, then change its distance and parent accordingly
	    if (distances.get(v) == DEFAULT_VALUE) {
		visuallyOpenNode(v, showSteps);
		distances.put(v, distances.get(u) + 1);
		parents.put(v, u);
		q.add(v);

		System.out.println("(" + v.getPosition()[0] + ", " + v.getPosition()[1] + ") = "
			+ distances.get(mainGrid.getNode(v.getPosition()[0], v.getPosition()[1])));
	    }
	}
    }

}
