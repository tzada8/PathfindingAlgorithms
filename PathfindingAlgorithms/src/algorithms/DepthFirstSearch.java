package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class DepthFirstSearch {
	private IntGraphList graph;
	private Map<Integer, Integer> parents; // Parent of each vertex
	private List<Integer> componentSizes; // the size of each component

	/**
	 * This constructor takes a graph and source vertex as parameters and
	 * conducts a depth-first search (DFS) on the graph.
	 */
	public DepthFirstSearch(IntGraphList graph) {
		// Initializing all fields
		this.graph = graph;
		this.parents = new HashMap<>();
		this.componentSizes = new ArrayList<>();
		Set<Integer> visited = new HashSet<>(); // If vertex has been visited

		int i = 0; // keeps track of index of componentSizes List
		int valuesBefore = 0; // keeps track of adding all values before "i"
		// For every vertex that hasn't been visited yet (it's not in visited
		// set yet), conduct DFS
		for (int v : this.graph.getVertices()) {
			if (!visited.contains(v)) {
				dfsVisit(v, visited);
				// Determines what value to add to componentSizes List; if
				// adding first component, then just add it to List, then any
				// other component, add (value - value at index before)
				if (this.componentSizes.isEmpty()) {
					this.componentSizes.add(visited.size());
				} else {
					valuesBefore = valuesBefore + this.componentSizes.get(i);
					i++;
					this.componentSizes.add(visited.size() - valuesBefore);
				}
			}
		}
	}

	/**
	 * This private helper method is called in the DFSTree constructor, and is
	 * the main code that determines the connected components of the graph.
	 */
	private void dfsVisit(int u, Set<Integer> visited) {
		// Initialize empty stack listing all vertices that need to be visited
		Stack<Integer> verticesToVisit = new Stack<Integer>();
		verticesToVisit.push(u);
		visited.add(u);

		while (!verticesToVisit.isEmpty()) { // When stack is not empty
			u = verticesToVisit.pop();
			for (int w : this.graph.getAdjacencyList(u)) {
				// If vertex "w" hasn't been visited yet, then add "u" back to
				// stack, with "w" on top, and say that "w" has been visited
				if (!visited.contains(w)) {
					this.parents.put(w, u);
					verticesToVisit.push(u);
					verticesToVisit.push(w);
					visited.add(w);
					break;
				}
			}
		}
	}

	/**
	 * This instance method returns the graph.
	 */
	public IntGraphList getGraph() {
		return this.graph;
	}

	/**
	 * This instance method finds the parent vertex to the given parameter
	 * vertex, "v". E.g. if "A" is a parent to "B", then when calling this
	 * method on "B", it will return "A".
	 */
	public int getParent(int v) {
		return this.parents.get(v);
	}

	/**
	 * This instance method returns a List of the sizes for each component. E.g.
	 * if List has size of 2, then there are 2 components and the value at each
	 * index is the size of each component.
	 */
	public List<Integer> getComponentSizes() {
		return this.componentSizes;
	}
}
