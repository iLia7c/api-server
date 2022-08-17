package olx;

public class Year {
    private static final int MIN_YEAR = 1850;
    private static final int CURRENT_YEAR = 2022;

    public static Year getDefaultYear() {
        return new Year(2000);
    }

    // TODO throws RuntimeException
    public static Year getYear(int year) {
        if (year >= MIN_YEAR && year <= CURRENT_YEAR) {
            return new Year(year);
        } else {
            throw new RuntimeException("Incorrect year");
        }
    }

    private Year(int year) {
    }

    private int year;
}
