package ua.dudka.dijkstra.service;

import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator {

    private static final int INITIAL_ORIGIN = -130;
    private static final int INITIAL_BOUND = 120;

    private static int origin = INITIAL_ORIGIN;
    private static int bound = INITIAL_BOUND;

    public static double getRandomX() {
        setNewValues();
        return getRandom(origin, bound);
    }

    private static void setNewValues() {
        if (origin >= -200 || bound <= 200) {
            origin -= 10;
            bound += 10;
        }
    }

    public static double getRandomY() {
        setNewValues();
        return getRandom(origin, bound);
    }

    public static int getRandom(int origin, int bound) {
        return ThreadLocalRandom.current().nextInt(origin, bound);
    }

    public static void resetRandom() {
        origin = INITIAL_ORIGIN;
        bound = INITIAL_BOUND;
    }
}