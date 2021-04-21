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
    private GridPanel mainGrid;
    private Node start;
    private Map<Node, Integer> distances; // Node to integers
    private Map<Node, Node> parents; // Node to Node

//    Timer timer;

    /**
     * This constructor takes a board and source vertex as parameters and conducts a
     * breath-first search (BFS) on the graph.
     */
    public BreathFirstSearch(GridPanel mainGrid, Node start, boolean showSteps) {
	// Initializing all fields
	this.start = start;
	this.mainGrid = mainGrid;
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

	// Keep track of "visited" vertices
	Queue<Node> q = new LinkedList<>();
	distances.put(start, 0);
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
			solveUsingBFS(q, showSteps);

			// If END Node's distance is not -1, then that means it has been reached so stop
			// timer
			if (distances.get(end) != -1 || q.isEmpty()) {
			    ((Timer) e.getSource()).stop();
			}

			// If timer has stopped running and a solution exists, then draw the path
			if (!((Timer) e.getSource()).isRunning() && distances.get(end) != -1) {
			    drawPath(end);
			} else if (!((Timer) e.getSource()).isRunning() && distances.get(end) == -1) {
			    // Else throw exception to go into catch block
			    throw new NoSuchElementException("Force catch block");
			}
		    } catch (NoSuchElementException e1) {
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
		while (distances.get(end) == -1) {
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

    private void drawPath(Node end) {
	// Change all Nodes part of solution accordingly
	Node currentNode = parents.get(end);
	while (currentNode != start) {
	    currentNode.makePath();
	    currentNode = parents.get(currentNode);
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
