package ua.dudka.dijkstra.model;

import java.util.LinkedList;
import java.util.stream.Collectors;


public class Answer {
    public final int distance;
    public final LinkedList<Vertex> path;

    public Answer() {
        distance = 0;
        path = new LinkedList<>();
    }

    public Answer(int distance, LinkedList<Vertex> path) {
        this.distance = distance;
        this.path = path;
    }

    public String getPathStr() {
        return path.stream()
                .map(Vertex::getLabel)
                .collect(Collectors.joining(","));
    }
}