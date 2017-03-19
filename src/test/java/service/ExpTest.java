package service;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;

public class ExpTest {

    private static final double TARGET_X = 1.1646;
    private static final double TARGET_Y = 1.1568;

    private static final double X_BEFORE = 218.0;
    private static final double Y_BEFORE = 144.0;


    private static final double MAGIC_CONSTANT = 0.628;

    @Test
    public void name() throws Exception {

        assertEquals(TARGET_X, normalize(X_BEFORE), 1);
        assertEquals(TARGET_Y, normalize(Y_BEFORE), 1);
    }

    private double normalize(double value) {
        double v = -value / 100;
        double answer = 1 / (MAGIC_CONSTANT + Math.pow(Math.E, v));
        return round(answer, 4);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


}
