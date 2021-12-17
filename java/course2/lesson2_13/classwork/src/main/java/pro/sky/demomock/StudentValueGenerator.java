package pro.sky.demomock;

public class StudentValueGenerator {
    public static final int DEFAULT_MAX = 100;
    public static final int DEFAULT_MIN = 1;

    public int generateAgeInRange(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    public int generateAge() {
        return generateAgeInRange(DEFAULT_MAX, DEFAULT_MIN);
    }

}
