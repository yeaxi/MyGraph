package ua.dudka.dijkstra.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ua.dudka.dijkstra.service.RandomGenerator;

@Getter
@EqualsAndHashCode(of = "id")
@ToString
@NoArgsConstructor
public class Vertex {

    private final int size = 10;

    private static int counter = 0;

    private int id;
    private String name;
    private double x = RandomGenerator.getRandomX();
    private double y = RandomGenerator.getRandomY();

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