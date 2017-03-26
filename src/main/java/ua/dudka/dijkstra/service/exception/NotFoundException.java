package ua.dudka.dijkstra.service.exception;

import lombok.Getter;

public class NotFoundException extends RuntimeException {

    private static final String message = "%s з %s '%s' не існує!";

    public NotFoundException(NotFoundEntity entity, String name) {
        super(String.format(message, entity.getName(), entity.getId(), name));
    }

    @Getter
    public enum NotFoundEntity {
        VERTEX("Вершина", "назвою"),
        EDGE("Ребро", "номером");

        private final String name;
        private final String id;

        NotFoundEntity(String name, String id) {
            this.name = name;
            this.id = id;
        }
    }

}
