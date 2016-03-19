package se.range;

/**
 * TimeRangeContainer.
 *
 * @author  Sinan Eski
 */
public class TimeRangeContainer extends RangeContainer<Long, TimeRange> {

    public long getTotalDuration() {
        long totalDiff = 0;
        for(TimeRange range : rangeList) {
            totalDiff += range.getDuration();
        }
        return totalDiff;
    }
}
