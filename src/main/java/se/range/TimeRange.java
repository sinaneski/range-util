package se.range;

/**
 * TimeRange which implements range as long type.
 *
 * @author  Sinan Eski
 */
public class TimeRange extends Range<Long> {

    public TimeRange(Long start, Long end) {
        super(start, end);
    }

    public long getDuration() {
        if(isEmpty()) return 0;
        return end - start;
    }
}
