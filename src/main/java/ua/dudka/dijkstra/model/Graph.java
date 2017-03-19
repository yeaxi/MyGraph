package ua.dudka.dijkstra.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Graph {
    private final List<Vertex> nodes;
    private final List<Edge> edges;

    public void addNode(String name) {
        nodes.add(new Vertex(name));
    }

    public void removeNode(String name) {
        nodes.removeIf(v -> v.getName().equals(name));
    }
}