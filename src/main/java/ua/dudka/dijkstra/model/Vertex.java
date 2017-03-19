package ua.dudka.dijkstra.model;

import lombok.*;

import java.util.concurrent.ThreadLocalRandom;

@Getter
@EqualsAndHashCode(of = "id")
@ToString
public class Vertex {

    private final int size = 3;

    private static int counter = 0;
    private int id;

    private String name;
    private double x = ThreadLocalRandom.current().nextDouble(0.4, 1.5);
    private double y = ThreadLocalRandom.current().nextDouble(0.4, 1.5);

    public Vertex(String name) {
        this.name = name;
        this.id = counter++;
    }

    public String getLabel() {
        return this.name;
    }

}