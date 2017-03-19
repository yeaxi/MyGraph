package ua.dudka.dijkstra.service;

import ua.dudka.dijkstra.model.Graph;

public interface HomeService {
    Graph getGraph();

    void addNode(String name);

    void removeNode(String name);
}