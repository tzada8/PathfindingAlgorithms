package algorithms;
/* Troy Zada 20845119
 * Assignment - Final Project
 * 15 December 2020
 * This class utilizes a graph object and a source node, and conducts breath-
 * first search (BFS) on the graph; it finds the distances and parents of each 
 * vertex from the source node. 
 * No input.
 * No output.
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BreathFirstSearch {
	private IntGraphList graph;
	private int source;
	private Map<Integer, Integer> distances; // Vertices to integers
	private Map<Integer, Integer> parents; // Vertices to vertices
	private int connectsToSource; // Number of actors that connect to source

	/**
	 * This constructor takes a graph and source vertex as parameters and
	 * conducts a breath-first search (BFS) on the graph.
	 */
	public BreathFirstSearch(IntGraphList graph, int source) {
		// Initializing all fields
		this.graph = graph;
		this.source = source;
		this.distances = new HashMap<>();
		this.parents = new HashMap<>();

		// For every vertex, initialize distance and parent
		for (int node : graph.getVertices()) {
			this.distances.put(node, -1);
			this.parents.put(node, null);
		}

		// Keep track of "visited" vertices
		Queue<Integer> q = new LinkedList<>();
		this.distances.put(source, 0);
		q.add(source);

		while (!q.isEmpty()) {
			int u = q.remove();
			// For every vertex adjacent to u
			for (int v : graph.getAdjacencyList(u)) {
				// If v is not yet visited, then change its distance and
				// parent accordingly
				if (this.distances.get(v) == -1) {
					this.distances.put(v, this.distances.get(u) + 1);
					this.connectsToSource++;
					this.parents.put(v, u);
					q.add(v);
				}
			}
		}
	}

	/**
	 * This instance method finds the distance between the vertex parameter and
	 * the source node. E.g. if there are "X" nodes between "A" and "B", then
	 * when calling this method on "B", it will return "X".
	 */
	public int getDistanceTo(int v) {
		return this.distances.get(v);
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
	 * This instance method returns the graph.
	 */
	public IntGraphList getGraph() {
		return this.graph;
	}

	/**
	 * This instance method returns the source node.
	 */
	public int getSource() {
		return this.source;
	}
	
	/**
	 * This instance method returns the number of actors connected to the 
	 * source.
	 */
	public int getConnectsToSource() {
		return this.connectsToSource;
	}
}
