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

public class DepthFirstSearch {
    private GridPanel mainGrid;
    private Node start;
    private Map<Node, Node> parents; // Parent of each vertex

    /**
     * This constructor takes a graph and source vertex as parameters and conducts a
     * depth-first search (DFS) on the graph.
     */
    public DepthFirstSearch(GridPanel mainGrid, Node start, boolean showSteps) {
	// Initializing all fields
	this.mainGrid = mainGrid;
	this.start = start;
	this.parents = new HashMap<>();
	Set<Node> visited = new HashSet<>(); // For visited Nodes

	Node end = mainGrid.getEndNode();

	// Initialize empty stack listing all vertices that need to be visited
	Stack<Node> verticesToVisit = new Stack<Node>();
	verticesToVisit.push(start);
	visited.add(start);

	if (showSteps) {
	    Timer timer = new Timer(10, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    try {
			dfsVisit(verticesToVisit, visited, showSteps);

			// If END Node has been visited, then that means it has been reached so stop
			// timer
			if (visited.contains(end) || verticesToVisit.isEmpty()) {
			    ((Timer) e.getSource()).stop();
			}

			// If timer has stopped running and a solution exists, then draw the path
			if (!((Timer) e.getSource()).isRunning() && visited.contains(end)) {
			    drawPath(end);
			} else if (!((Timer) e.getSource()).isRunning() && !visited.contains(end)) {
			    // Else throw exception to go into catch block
			    throw new NoSuchElementException("Force catch block");
			}
		    } catch (NoSuchElementException | EmptyStackException e1) {
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
		    dfsVisit(verticesToVisit, visited, showSteps);
		}
		drawPath(end);
	    } catch (NoSuchElementException e) {
		JOptionPane.showMessageDialog(null, "The board has no solution.", "No Solutions",
			JOptionPane.INFORMATION_MESSAGE);
	    }
	}

//	for (int v : this.graph.getVertices()) {
//	    if (!visited.contains(v)) {
//		dfsVisit(v, visited);
//	    }
//	}
    }

    /**
     * This private helper method is called in the DFSTree constructor, and is the
     * main code that determines the connected components of the graph.
     */
    private void dfsVisit(Stack<Node> s, Set<Node> visited, boolean showSteps) {
	Node u = s.pop();
	if (!u.isStart() && showSteps) {
	    u.makeClosed();
	}
	
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
		if (!w.isEnd() && showSteps) {
		    w.makeOpen();
		}
		break;
	    }

	    int curR = w.getPosition()[0];
	    int curC = w.getPosition()[1];
	    Node parentSpot = parents.get(mainGrid.getNode(curR, curC));
	    int parR = parentSpot.getPosition()[0];
	    int parC = parentSpot.getPosition()[1];
	    System.out.println("(" + curR + ", " + curC + ") = parent @ (" + parR + ", " + parC + ")");
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
