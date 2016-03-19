package se.range;

/**
 * Basic compare operation for a Comparable class.
 * Operations:
 *      ge: Greater or Equal
 *      gt: GreaterThan
 *      le: Lower or Equal
 *      lt: LowerThan
 *      min: Minimum
 *      max: Maximum
 *
 * @author  Sinan Eski
 */
public class CompareUtil {

    public static <T extends Comparable<T>> boolean le (T first, T second) {
        return first.compareTo(second) <= 0;
    }

    public static <T extends Comparable<T>>  boolean lt (T first, T second) {
        return first.compareTo(second) < 0;
    }

    public static <T extends Comparable<T>> boolean ge (T first, T second) {
        return first.compareTo(second) >= 0;
    }

    public static <T extends Comparable<T>>  boolean gt (T first, T second) {
        return first.compareTo(second) > 0;
    }

    public static <T extends Comparable<T>> boolean eq (T first, T second) {
        return first.compareTo(second) == 0;
    }

    public static <T extends Comparable<T>> T min(T first, T second) {
        if(first.compareTo(second) < 0) return first;
        return second;
    }

    public static <T extends Comparable<T>>  T max( T first, T second) {
        if(first.compareTo(second) > 0) return first;
        return second;
    }

}
