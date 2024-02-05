package simple;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) {
		Graph graph = new Graph();

		graph.addNode("0");
		graph.addNode("1");
		graph.addNode("2");
		graph.addNode("3");
		graph.addNode("4");
		graph.addNode("5");
		graph.addNode("6");
		graph.addNode("7");

		graph.addDirectedEdge("0", "1", 4);
		graph.addDirectedEdge("0", "7", 8);

		graph.addDirectedEdge("1", "7", 11);
		graph.addDirectedEdge("1", "0", 4);
		graph.addDirectedEdge("1", "2", 8);

		graph.addDirectedEdge("2", "1", 8);
		graph.addDirectedEdge("2", "8", 2);
		graph.addDirectedEdge("2", "3", 7);
		graph.addDirectedEdge("2", "5", 4);

		graph.addDirectedEdge("3", "2", 7);
		graph.addDirectedEdge("3", "4", 9);
		graph.addDirectedEdge("3", "5", 14);

		graph.addDirectedEdge("4", "3", 9);
		graph.addDirectedEdge("4", "5", 10);

		graph.addDirectedEdge("5", "6", 2);
		graph.addDirectedEdge("5", "2", 4);
		graph.addDirectedEdge("5", "3", 14);
		graph.addDirectedEdge("5", "4", 10);

		graph.addDirectedEdge("6", "7", 1);
		graph.addDirectedEdge("6", "8", 6);
		graph.addDirectedEdge("6", "5", 2);

		graph.addDirectedEdge("7", "0", 8);
		graph.addDirectedEdge("7", "1", 11);
		graph.addDirectedEdge("7", "8", 7);
		graph.addDirectedEdge("7", "6", 1);

		Algo.findShortestPaths(graph, "0");
	}
}

class Algo {
	public static void findShortestPaths(Graph graph, String sourceNode) {
		Map<String, Integer> distances = new HashMap<>();
		Map<String, String> predecessors = new HashMap<>();
		PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));

		for (String node : graph.adjacencyList.keySet()) {
			distances.put(node, Integer.MAX_VALUE);
			predecessors.put(node, null);
		}

		distances.put(sourceNode, 0);
		priorityQueue.add(new Edge(sourceNode, 0));

		while (!priorityQueue.isEmpty()) {
			String currentNode = priorityQueue.poll().getDestination();

			for (Edge neighbor : graph.getNeighbors(currentNode)) {
				Integer currentDistance = distances.get(currentNode);
				if (currentDistance == null) {
					continue;
				}

				int newDistance = currentDistance + neighbor.getWeight();
				Integer neighborDistance = distances.get(neighbor.getDestination());

				if (neighborDistance == null || newDistance < neighborDistance) {
					distances.put(neighbor.getDestination(), newDistance);
					predecessors.put(neighbor.getDestination(), currentNode);
					priorityQueue.add(new Edge(neighbor.getDestination(), newDistance));
				}
			}
		}

		System.out.println("Shortest paths from node " + sourceNode + ":");
		for (String node : distances.keySet()) {
			System.out
					.println("To node " + node + ": " + distances.get(node) + " with predecessor: " + predecessors.get(node));
		}
	}

}

class Graph {
	public HashMap<String, ArrayList<Edge>> adjacencyList;

	public Graph() {
		this.adjacencyList = new HashMap<>();
	}

	public void addNode(String node) {
		adjacencyList.put(node, new ArrayList<>());
	}

	public void addDirectedEdge(String source, String destination, int weight) {
		adjacencyList.get(source).add(new Edge(destination, weight));
	}

	public ArrayList<Edge> getNeighbors(String node) {
		ArrayList<Edge> neighbors = adjacencyList.get(node);
		return neighbors != null ? neighbors : new ArrayList<>();
	}
}

class Edge {
	@Override
	public String toString() {
		return "Edge [destination=" + destination + ", weight=" + weight + "]";
	}

	String destination;
	int weight;

	public Edge(String destination, int weight) {
		this.destination = destination;
		this.weight = weight;
	}

	public String getDestination() {
		return destination;
	}

	public int getWeight() {
		return weight;
	}
}