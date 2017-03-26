package ua.dudka.dijkstra.service.exception;

public class NodesNotConnectedException extends RuntimeException {
    private static final String message = "Вершини '%s' '%s' не зв'язані!";

    public NodesNotConnectedException(String sourceName, String targetName) {
        super(String.format(message, sourceName, targetName));
    }
}