package ua.dudka.dijkstra.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Graph {
    private final List<Vertex> nodes;
    private final List<Edge> edges;
}