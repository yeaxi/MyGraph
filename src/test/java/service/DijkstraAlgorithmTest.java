package service;

import org.junit.Test;
import ua.dudka.dijkstra.model.Edge;
import ua.dudka.dijkstra.model.Graph;
import ua.dudka.dijkstra.model.Vertex;
import ua.dudka.dijkstra.service.DijkstraAlgorithm;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;

public class DijkstraAlgorithmTest {
    private List<Vertex> nodes;
    private List<Edge> edges;

    @Test
    public void testExcute() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            Vertex location = new Vertex("Node_" + i);
            nodes.add(location);
        }

        addLane("Edge_0", 0, 1, 85);
        addLane("Edge_1", 0, 2, 217);
        addLane("Edge_2", 0, 4, 173);
        addLane("Edge_3", 2, 6, 186);
        addLane("Edge_4", 2, 7, 103);
        addLane("Edge_5", 3, 7, 183);
        addLane("Edge_6", 5, 8, 250);
        addLane("Edge_7", 8, 9, 84);
        addLane("Edge_8", 7, 9, 167);
        addLane("Edge_9", 4, 9, 502);
        addLane("Edge_10", 9, 10, 40);
        addLane("Edge_11", 1, 10, 600);

        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);

        dijkstra.execute(nodes.get(0));

        List<Vertex> path = dijkstra.getPath(nodes.get(10));

        assertFalse(path.isEmpty());

        for (Vertex vertex : path) {
            System.out.println(vertex);
        }

        System.out.println("distance: " + dijkstra.getShortestDistance(nodes.get(10)));

    }

    private void addLane(String laneId, int sourceLocNo, int destLocNo,
                         int duration) {
        Edge lane = new Edge(nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
        edges.add(lane);
    }

}