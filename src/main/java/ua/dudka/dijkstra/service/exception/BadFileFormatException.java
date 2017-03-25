package ua.dudka.dijkstra.service.exception;

public class BadFileFormatException extends RuntimeException {
    public BadFileFormatException(String message) {
        super(message);
    }
}
