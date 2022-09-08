package by.krutikov.util;

import java.util.Random;

public class RatingGenerator {
    public static Byte generateRating() {
        return (byte) (Math.random() * 5);
    }
}
