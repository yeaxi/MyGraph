package ua.dudka.dijkstra.service.impl;

import org.springframework.stereotype.Service;
import ua.dudka.dijkstra.model.Edge;
import ua.dudka.dijkstra.model.Graph;
import ua.dudka.dijkstra.model.Vertex;
import ua.dudka.dijkstra.service.DijkstraAlgorithm;
import ua.dudka.dijkstra.service.HomeService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {
    private List<Vertex> nodes;
    private List<Edge> edges;
    private Graph graph;

    private DijkstraAlgorithm dijkstraAlgorithm;

    @PostConstruct
    public void init() {
        List<Vertex> nodes = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
            Vertex vertex = new Vertex("N" + i);
            nodes.add(vertex);
        }


        edges.add(new Edge(nodes.get(0), nodes.get(1), 85));
        edges.add(new Edge(nodes.get(0), nodes.get(2), 217));
        edges.add(new Edge(nodes.get(0), nodes.get(4), 173));
        edges.add(new Edge(nodes.get(2), nodes.get(6), 186));
        edges.add(new Edge(nodes.get(2), nodes.get(7), 103));
        edges.add(new Edge(nodes.get(3), nodes.get(7), 183));
        edges.add(new Edge(nodes.get(5), nodes.get(8), 250));
        edges.add(new Edge(nodes.get(8), nodes.get(9), 84));
        edges.add(new Edge(nodes.get(7), nodes.get(9), 167));
        edges.add(new Edge(nodes.get(4), nodes.get(9), 502));
        edges.add(new Edge(nodes.get(9), nodes.get(10), 40));
        edges.add(new Edge(nodes.get(1), nodes.get(10), 600));
        this.graph = new Graph(nodes, edges);
    }


    @Override
    public Graph getGraph() {
        return graph;
    }

    @Override
    public void addNode(String name) {
        graph.addNode(name);
    }

    @Override
    public void removeNode(String name) {
        graph.removeNode(name);
    }
}
