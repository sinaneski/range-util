
package se.range;


/**
 * Generic range implementation. Boundaries between [start, end] and not circular.
 *
 * @author  Sinan Eski
 * @param <T> range type which implements Comparable
 */
public class Range <T extends Comparable<T>> {

    protected T start;

    protected T end;

    public Range(T start, T end){
        this.start = start;
        this.end = end;
    }

    public T getStart() {
        return start;
    }

    public T getEnd() {
        return end;
    }

    public void merge(Range<T> other) throws IllegalArgumentException {

        if(!isIntersect(other))
            throw new IllegalArgumentException(toString() + " have no intersect " + other.toString() + "!");

        if(CompareUtil.lessThan(other.start, start))
            start = other.start;

        if(CompareUtil.greaterThan(other.end, end))
            end = other.end;

    }

    public Range<T> intersect(Range<T> other) {

        if(!isIntersect(other)) return null;
        T s = start, e = end;
        if(CompareUtil.greaterThan(other.start, start)) s = other.start;
        if(CompareUtil.lessThan(other.end, end)) e = other.end;
        return new Range<>(s, e);
    }

    public void remove(Range<T> other) {

        if(isContain(other) && !isCross(other))
            throw new IllegalArgumentException(toString() + " contains " + other.toString() + "!");

        if(isSubset(other)) {
            start =  null;
            end =  null;
            return;
        }

        if(CompareUtil.greaterOrEqualTo(other.start,start) && CompareUtil.lessOrEqualTo(other.start, end) )
            end = other.start;

        if(CompareUtil.greaterOrEqualTo(other.end,  start)  && CompareUtil.lessOrEqualTo(other.end, end))
            start = other.end;
    }

    public Range[] split(Range<T> other) {

        if(!isContain(other))
            throw new IllegalArgumentException(toString() + " does not contain " + other.toString() + "!");

        Range[] timeRanges = new Range[2];

        timeRanges[0] = new Range<>(CompareUtil.selectMinimum(start, other.start), CompareUtil.selectMaximum(start, other.start));
        timeRanges[1] = new Range<>(CompareUtil.selectMinimum(end, other.end), CompareUtil.selectMaximum(end, other.end));

        return timeRanges;
    }

    public boolean isEmpty() {
        return start == null || end == null || CompareUtil.greaterOrEqualTo(start, end);
    }

    public boolean isBefore(Range<T> other) {
        //  current:    |---|
        //  other:           |-------|
        return other != null && CompareUtil.lessThan(end, other.start);
    }

    public boolean isAfter(Range<T> other) {
        //  current:            |---|
        //  other:    |-------|
        return other != null && CompareUtil.lessThan(other.end, start);
    }

    public boolean isIntersect(Range<T> other) {
        return isContain(other) || isSubset(other) || isCross(other);
    }

    public boolean isContain(Range<T> other) {
        //  current:  |-------|
        //  other:      |---|
        return other != null && CompareUtil.greaterOrEqualTo(other.start, start) && CompareUtil.lessOrEqualTo(other.end, end);
    }

    public boolean isSubset(Range<T> other) {
        //  current:    |---|
        //  other:   |-------|
        return other != null && CompareUtil.lessOrEqualTo(other.start, start) && CompareUtil.greaterOrEqualTo(other.end, end);
    }


    public boolean isCross(Range<T> other) {
        return isPrePartCross(other) || isPostPartCross(other);
    }

    public boolean isPrePartCross(Range<T> other) {
        //  1-current:     |-------|   2-current:|------| 3-current:  |----| 4-current:   |-----|
        //  1-other   |----|           2-other:  |--|     3-other: |-------| 4-other:  |-----|
        //
        return other != null && CompareUtil.lessOrEqualTo(other.start, start)&&
                CompareUtil.greaterOrEqualTo( other.end, start) && CompareUtil.lessOrEqualTo(other.end, end);
    }

    public boolean isPostPartCross(Range<T> other) {
        //  1-current: |-------|        2-current:  |---|   3-current:    |---|  4-current:|----|
        //  1-other:           |----|   2-other:    |-----|  3-other:|--------|  4-other:    |----|
        //
        return other != null && CompareUtil.greaterOrEqualTo(other.end,end) &&
                CompareUtil.greaterOrEqualTo(other.start, start) && CompareUtil.lessOrEqualTo(other.start, end);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Range<T> range = (Range<T>) o;

        return CompareUtil.equal(start, range.start) && CompareUtil.equal(end, range.end);
    }

    @Override
    public String toString() {
        return "[" + start + "-" + end + "]";
    }
}
