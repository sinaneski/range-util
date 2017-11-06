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

    public  TimeRangeContainer intersect(TimeRangeContainer secondRangeList) {
         TimeRangeContainer results = new TimeRangeContainer();
        int j = 0;
        int i = 0;

        while( i < rangeList.size() && j < secondRangeList.size()) {
            TimeRange current = rangeList.get(i);
            TimeRange range = secondRangeList.get(j);
            if (range.isBefore(current)) {
                j++;
                continue;
            }
            if (range.isAfter(current)) {
                i++;
                continue;
            }
            TimeRange result = current.intersect(range);

            if (!result.isEmpty()) {
                results.add(result);
            }

            if (CompareUtil.greaterThan(current.getEnd(), result.getEnd())) {
                j++;
                continue;
            }
            i++;
        }

        return results;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(TimeRange t: rangeList) {
            sb.append(t.toString());
        }
        return sb.toString();
    }

}
