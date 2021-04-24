package algorithms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Stack;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import sections.board.GridPanel;
import sections.board.Node;

public class DepthFirstSearch extends Algorithm {

    /**
     * This constructor takes a graph and source vertex as parameters and conducts a
     * depth-first search (DFS) on the graph.
     */
    public DepthFirstSearch(GridPanel mainGrid, Node start, boolean showSteps) {
	// Initializing all fields
	this.mainGrid = mainGrid;
	this.start = start;
	this.end = mainGrid.getEndNode();
	this.parents = new HashMap<>();
	Set<Node> visited = new HashSet<>(); // For visited Nodes

	// Initialize empty Stack listing all Nodes that need to be visited
	Stack<Node> nodesToVisit = new Stack<Node>();
	nodesToVisit.push(start);
	visited.add(start);

	// Solution will be found in 2 ways depending if user wants to see solution
	// 1. Using a timer that renders the Node every 0.01s until the END node is
	// reached
	// 2. Using a while loop that doesn't render anything except the solution path
	if (showSteps) {
	    Timer timer = new Timer(10, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    try {
			solveUsingDFS(nodesToVisit, visited, showSteps); // Solve grid using DFS

			if (nodesToVisit.isEmpty()) {
			    // If Stack is empty, then we ran out of options, so no solution found
			    ((Timer) e.getSource()).stop();
			    throw new EmptyStackException();
			} else if (visited.contains(end)) {
			    // If END Node has been visited, then we reached it, so draw solution
			    ((Timer) e.getSource()).stop();
			    drawPath(end);
			}
		    } catch (EmptyStackException e1) { // Dialog box if no solution exists
			JOptionPane.showMessageDialog(null, "The board has no solution.", "No Solutions",
				JOptionPane.INFORMATION_MESSAGE);
		    }
		}
	    });
	    timer.start();
	} else {
	    try {
		// For every vertex that hasn't been visited yet (it's not in visited set yet),
		// conduct DFS
		while (!visited.contains(end)) {
		    solveUsingDFS(nodesToVisit, visited, showSteps);
		}
		drawPath(end);
	    } catch (EmptyStackException e) { // Dialog box if no solution exists
		JOptionPane.showMessageDialog(null, "The board has no solution.", "No Solutions",
			JOptionPane.INFORMATION_MESSAGE);
	    }
	}
    }

    /**
     * This private helper method is called in the DFSTree constructor, and is the
     * main code that determines the connected components of the graph.
     */
    private void solveUsingDFS(Stack<Node> s, Set<Node> visited, boolean showSteps) {
	Node u = s.pop();
	visuallyCloseNode(u, showSteps);

	// For every Node adjacent to u
	for (Node w : mainGrid.getAdjacencyNodes(u)) {
	    System.out.println("Current Node " + w.getPosition()[0] + ", " + w.getPosition()[1]);
	    // If vertex "w" hasn't been visited yet, then add "u" back to
	    // stack, with "w" on top, and say that "w" has been visited
	    if (!visited.contains(w)) {
		this.parents.put(w, u);
		s.push(u);
		s.push(w);
		visited.add(w);
		visuallyOpenNode(w, showSteps);
		break;
	    }

	    // 6 LINES OF CODE BELOW CAN BE DELETED; JUST USED FOR PRINTLN
	    // STATEMENTS/TESTING
	    int curR = w.getPosition()[0];
	    int curC = w.getPosition()[1];
	    Node parentSpot = parents.get(mainGrid.getNode(curR, curC));
	    int parR = parentSpot.getPosition()[0];
	    int parC = parentSpot.getPosition()[1];
	    System.out.println("(" + curR + ", " + curC + ") = parent @ (" + parR + ", " + parC + ")");
	}
    }

}
