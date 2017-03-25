package ua.dudka.dijkstra.service.exception;

public class NodeExistsException extends RuntimeException {
    private static final String MESSAGE = "Вершина з ім'ям '%s' вже існує";

    public NodeExistsException(String name) {
        super(String.format(MESSAGE, name));
    }
}