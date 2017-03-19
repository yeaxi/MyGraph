package ua.dudka.dijkstra.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

public class MathService {

    private static final int ROUND_SCALE = 4;

    private static double getRandomValue(double origin, double bound) {
        double value = ThreadLocalRandom.current().nextDouble(origin, bound);
        return roundToFourDigits(value);
    }


    private static double roundToFourDigits(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(ROUND_SCALE, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


    public static double getRandomX() {
        return getRandomValue(-60, 60);
    }


    public static double getRandomY() {
        return getRandomValue(-80, 120);
    }
}