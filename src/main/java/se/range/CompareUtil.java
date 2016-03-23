package se.range;

/**
 * Basic compare operation for a Comparable class.
 * Operations:
 *
 * @author  Sinan Eski
 */
public class CompareUtil {

    public static <T extends Comparable<T>> boolean lessOrEqualTo(T first, T second) {
        return first.compareTo(second) <= 0;
    }

    public static <T extends Comparable<T>>  boolean lessThan(T first, T second) {
        return first.compareTo(second) < 0;
    }

    public static <T extends Comparable<T>> boolean greaterOrEqualTo(T first, T second) {
        return first.compareTo(second) >= 0;
    }

    public static <T extends Comparable<T>>  boolean greaterThan(T first, T second) {
        return first.compareTo(second) > 0;
    }

    public static <T extends Comparable<T>> boolean equal(T first, T second) {
        return first.compareTo(second) == 0;
    }

    public static <T extends Comparable<T>> T selectMinimum(T first, T second) {
        if(first.compareTo(second) < 0) return first;
        return second;
    }

    public static <T extends Comparable<T>>  T selectMaximum(T first, T second) {
        if(first.compareTo(second) > 0) return first;
        return second;
    }

}
