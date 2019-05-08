package utils;

import java.util.Random;

public class Utils {

    public static int randomize(int max, int min) {
        return new Random().nextInt(max) + min;
    }

    public static double exponent() {
        return new Random().nextDouble() + 1.0D;
    }
}
