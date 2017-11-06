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

    public TimeRange intersect(TimeRange other) {
        if(!isIntersect(other)) return null;
        Long s = start, e = end;
        if(CompareUtil.greaterThan(other.start, start)) s = other.start;
        if(CompareUtil.lessThan(other.end, end)) e = other.end;
        return new TimeRange(s, e);
    }

}
