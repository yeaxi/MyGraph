package my.first.graph;

import java.util.ArrayList;

/**
 * Created by RASTA on 29.02.2016.
 * Class - structure.
 */
class Vertex {
    private int number;
    private char name;
    ArrayList<Vertex> edges = new ArrayList<Vertex>();

    public Vertex(int number, char name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public char getName() {
        return name;
    }

    @Override
    public String toString() {
        return "undirected.graph.Vertex{" +
                "name=" + name +
                ", number=" + number +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        if (name != vertex.name) return false;
        if (number != vertex.number) return false;
        if (edges != null ? !edges.equals(vertex.edges) : vertex.edges != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + (int) name;
        result = 31 * result + (edges != null ? edges.hashCode() : 0);
        return result;
    }
}
