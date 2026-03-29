package task_2;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Task 2: Shortest Path Problem.
 * This class implements Dijkstra's algorithm to find the minimum cost
 * between nodes in a weighted directed graph.
 */
public final class RouteFinder {

    static void main(String[] args) {

        final int numCities = 4; // the number of cities in the graph

        // Map to convert city names to their corresponding indices (1-based)
        final Map<String, Integer> cityIndexByName = new HashMap<>();
        cityIndexByName.put("gdansk", 1);
        cityIndexByName.put("bydgoszcz", 2);
        cityIndexByName.put("torun", 3);
        cityIndexByName.put("warszawa", 4);

        // Adjacency list representation of the graph
        final List<List<Edge>> graph = new ArrayList<>();
        IntStream.rangeClosed(0, numCities).forEach(i -> graph.add(new ArrayList<>()));

        // Input graph data (edges and their costs)

        // 1: gdansk
        graph.get(1).add(new Edge(2, 1)); // bydgoszcz (index 2, cost 1)
        graph.get(1).add(new Edge(3, 3)); // torun (index 3, cost 3)

        // 2: bydgoszcz
        graph.get(2).add(new Edge(1, 1)); // gdansk (index 1, cost 1)
        graph.get(2).add(new Edge(3, 1)); // torun (index 3, cost 1)
        graph.get(2).add(new Edge(4, 4)); // warszawa (index 4, cost 4)

        // 3: torun
        graph.get(3).add(new Edge(1, 3)); // gdansk (index 1, cost 3)
        graph.get(3).add(new Edge(2, 1)); // bydgoszcz (index 2, cost 1)
        graph.get(3).add(new Edge(4, 1)); // warszawa (index 4, cost 1)

        // 4: warszawa
        graph.get(4).add(new Edge(2, 4)); // bydgoszcz (index 2, cost 4)
        graph.get(4).add(new Edge(3, 1)); // torun (index 3, cost 1)

        // Output

        // Test 1: gdansk warszawa
        int start1 = cityIndexByName.get("gdansk");
        int end1 = cityIndexByName.get("warszawa");
        int cost1 = findShortestPath(start1, end1, numCities, graph);

        // Print the cost of the shortest path from gdansk to warszawa
        System.out.println("the shortest path from gdansk to warszawa: " + cost1);

        // Test 2: bydgoszcz warszawa
        int start2 = cityIndexByName.get("bydgoszcz");
        int end2 = cityIndexByName.get("warszawa");
        int cost2 = findShortestPath(start2, end2, numCities, graph);

        // Print the cost of the shortest path from bydgoszcz to warszawa
        System.out.println("the shortest path from bydgoszcz to warszawa: " + cost2);
    }

    // Dijkstra's algorithm implementation
    public static int findShortestPath(int start, int end, int n, List<List<Edge>> adj) {
        // Array to store the minimum distance from 'start' to each node
        final int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        // Min-heap priority queue to always process the node with the smallest known distance
        final PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            final Edge current = pq.poll();

            // If we already found a shorter path to this node, skip processing
            if (current.cost > dist[current.to]) {
                continue;
            }

            // If we reached the destination node
            if (current.to == end) {
                return dist[end];
            }

            // Explore neighbors and perform edge relaxation
            for (Edge neighbor : adj.get(current.to)) {
                int newDist = dist[current.to] + neighbor.cost;

                // If a shorter path to the neighbor is found, update it
                if (newDist < dist[neighbor.to]) {
                    dist[neighbor.to] = newDist;
                    pq.add(new Edge(neighbor.to, newDist));
                }
            }
        }

        return -1; // Destination is unreachable
    }

    /**
     * Helper class representing a directed edge to another node with a specific cost.
     */
    public static class Edge {
        // Destination node
        int to;
        // Cost of the edge
        int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}