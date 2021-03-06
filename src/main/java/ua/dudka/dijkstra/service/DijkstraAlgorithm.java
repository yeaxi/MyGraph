package ua.dudka.dijkstra.service;

import ua.dudka.dijkstra.model.Answer;
import ua.dudka.dijkstra.model.Edge;
import ua.dudka.dijkstra.model.Graph;
import ua.dudka.dijkstra.model.Vertex;
import ua.dudka.dijkstra.service.exception.NodesNotConnectedException;

import java.util.*;

public class DijkstraAlgorithm {

    private List<Edge> edges;
    private Vertex target;
    private Set<Vertex> settledNodes;
    private Set<Vertex> unSettledNodes;
    private Map<Vertex, Vertex> predecessors;

    private Map<Vertex, Integer> distance;

    public DijkstraAlgorithm() {
    }

    public Answer execute(Graph graph, String sourceName, String targetName) {
        this.edges = new ArrayList<>(graph.getEdges());
        this.target = graph.findVertexByName(targetName);

        Vertex source = graph.findVertexByName(sourceName);

        settledNodes = new HashSet<>();
        unSettledNodes = new HashSet<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Vertex node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }

        Answer answer = new Answer(getShortestDistance(target), getPath());
        if (answer.path.isEmpty()) {
            throw new NodesNotConnectedException(sourceName, targetName);
        }
        return answer;
    }

    private void findMinimalDistances(Vertex node) {
        List<Vertex> adjacentNodes = getNeighbors(node);
        for (Vertex target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private int getDistance(Vertex node, Vertex target) {
        for (Edge edge : edges) {
            if (edge.getStart().equals(node)
                    && edge.getEnd().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<Vertex> getNeighbors(Vertex node) {
        List<Vertex> neighbors = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.getStart().equals(node)
                    && !isSettled(edge.getEnd())) {
                neighbors.add(edge.getEnd());
            }
        }
        return neighbors;
    }

    private Vertex getMinimum(Set<Vertex> vertexes) {
        Vertex minimum = null;
        for (Vertex vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(Vertex vertex) {
        return settledNodes.contains(vertex);
    }

    private int getShortestDistance(Vertex destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    private LinkedList<Vertex> getPath() {
        LinkedList<Vertex> path = new LinkedList<>();
        Vertex step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return path;
        }

        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }
}