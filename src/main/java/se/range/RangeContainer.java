package se.range;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic range container to hold sequential ranges as list.
 *
 * @author  Sinan Eski
 * @param <T> range type which implements Comparable
 * @param <E> container elements type which implements Range<T>
 */
public class RangeContainer <T extends Comparable<T>, E extends Range<T>> {

    protected List<E> rangeList = new ArrayList<>();

    public void add(E newRange) {
        if(rangeList.isEmpty()) {
            rangeList.add(newRange);
            return;
        }

        if(insertHead(newRange)) return;
        if(insertTail(newRange)) return;

        insertMiddle(newRange);
    }

    private boolean insertHead(E newRange) {
        if(newRange.isBefore(getFirst())) {
            rangeList.add(0, newRange);
            return true;
        }
        return false;
    }

    private E getFirst() {
        return rangeList.isEmpty() ? null : rangeList.get(0);
    }


    private boolean insertTail(E newRange) {
        if(newRange.isAfter(getLast())) {
            rangeList.add(newRange);
            return true;
        }
        return false;
    }


    private E getLast() {
        return rangeList.isEmpty() ? null : rangeList.get(rangeList.size() - 1);
    }

    private void insertMiddle(E newRange) {

        boolean isFound = false;

        for(int i = 0; i < rangeList.size(); i++) {

            isFound = false;

            E current = rangeList.get(i);

            if(CompareUtil.lessThan(newRange.getEnd(), current.getStart())) {
                rangeList.add(i, newRange);
                isFound = true;
                break;
            }

            if(newRange.isSubset(current) ) {
                isFound = true;
                break;
            }

            if(CompareUtil.greaterThan(newRange.getStart(), current.getEnd())) {
                continue;
            }

            if(newRange.isContain(current)) {
                rangeList.remove(i);
                i--;
                continue;
            }

            if(newRange.isCross(current)) {
                newRange.merge(current);
                rangeList.remove(i);
                i--;
            }
        }

        if(!isFound)
            rangeList.add(newRange);
    }

    public void intersect(RangeContainer<T, E> ranges) {
        intersect(ranges.rangeList);
    }

    private void intersect(List<E> ranges) {
        ranges.forEach(this::intersect);
    }

    public void intersect(E range) {

        if(range.isBefore(getFirst())) return;

        if(range.isAfter(getLast())) return;

        for (int i = 0; i < rangeList.size(); i++) {
            E current = rangeList.get(i);
            if (range.isBefore(current)) break;
            current.intersect(range);
            if(current.isEmpty()) {
                rangeList.remove(i--);
            }
        }
    }

    public void clear() {
        rangeList.clear();
    }

    public int size() {
        return rangeList.size();
    }

    public E get(int index) {
        return rangeList.get(index);
    }
}
