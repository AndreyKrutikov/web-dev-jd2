package by.krutikov.util;
@Deprecated
public class RatingGenerator {
    public static Byte generateRating() {
        return (byte) (Math.random() * 5);
    }
}
