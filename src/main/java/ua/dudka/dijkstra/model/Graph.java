package ua.dudka.dijkstra.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ua.dudka.dijkstra.service.RandomGenerator;
import ua.dudka.dijkstra.service.exception.NodeExistsException;
import ua.dudka.dijkstra.service.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static ua.dudka.dijkstra.service.exception.NotFoundException.NotFoundEntity.*;

@Getter
@AllArgsConstructor()
public class Graph {
    private final List<Vertex> nodes;
    private final List<Edge> edges;

    public Graph() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();

    }

    public void addNode(String name) {
        try {
            findVertexByName(name);
            throw new NodeExistsException(name);
        } catch (NotFoundException e) {
            nodes.add(new Vertex(name));
        }
    }

    public void removeNode(String name) {
        findVertexByName(name);
        nodes.removeIf(v -> v.getName().equals(name));
        edges.removeIf(e -> e.getStart().getName().equals(name) || e.getEnd().getName().equals(name));
    }

    public void updateNodeCoordinates(String name, Double x, Double y) {
        Vertex vertex = findVertexByName(name);
        vertex.updateCoordinates(x, y);
    }

    public void addEdge(String start, String end, int weight) {
        findVertexByName(start);
        findVertexByName(end);
        Vertex startVertex = findVertexByName(start);
        Vertex endVertex = findVertexByName(end);

        edges.add(new Edge(startVertex, endVertex, weight));
    }

    public Vertex findVertexByName(String name) {
        return nodes.stream()
                .filter(n -> n.getName().equals(name))
                .findAny().orElseThrow(() -> new NotFoundException(VERTEX, name));
    }

    public void removeEdge(Integer id) {
        edges.removeIf(e -> e.getId() == id);
    }

    public void clear() {
        this.nodes.clear();
        this.edges.clear();
        RandomGenerator.resetRandom();
    }

    public void fillRandomly() {
        clear();
        int origin = 0, bound = RandomGenerator.getRandom(4, 12);
        IntStream.range(origin, bound)
                .forEachOrdered(i -> nodes.add(new Vertex("N" + i)));

        for (int i = origin; i <= bound + 3; i++) {
            Vertex start = nodes.get(RandomGenerator.getRandom(origin, bound));
            Vertex end = nodes.get(RandomGenerator.getRandom(origin, bound));
            if (!start.equals(end)) {
                edges.add(new Edge(start, end, RandomGenerator.getRandom(10, 100)));
            }
        }
    }

    public void createFrom(Graph graph) {
        clear();
        nodes.addAll(graph.getNodes());
        edges.addAll(graph.getEdges());
    }
}