package ua.dudka.dijkstra.model;

import lombok.*;
import ua.dudka.dijkstra.service.MathService;

import java.util.concurrent.ThreadLocalRandom;

@Getter
@EqualsAndHashCode(of = "id")
@ToString
public class Vertex {

    private final int size = 10;

    private static int counter = 0;
    private int id;

    private String name;
    private double x = MathService.getRandomX();
    private double y = MathService.getRandomY();

    public Vertex(String name) {
        this.name = name;
        this.id = counter++;
    }

    public String getLabel() {
        return this.name;
    }

    public void updateCoordinates(Double x, Double y) {
        this.x = x;
        this.y = y;
    }
}