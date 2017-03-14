package ua.dudka.dijkstra.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(of = "id")
public class Edge {
    private static int counter = 0;

    private final String type = "arrow";
    private int id, weight, size = 25;

    private final Vertex start, end;

    public Edge(Vertex start, Vertex end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
        this.id = counter++;
    }

    public String getSource() {
        return start.getId() + "";
    }

    public String getTarget() {
        return end.getId() + "";
    }

    public String getName() {
        return getLabel();
    }

    public String getLabel() {
        return weight + " № :" + id;
    }

}