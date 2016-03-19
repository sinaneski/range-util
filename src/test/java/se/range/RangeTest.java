package se.range;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Range unit tests with Long implementation
 * @author  Sinan Eski
 */
public class RangeTest {

    @Test
    public void isEmpty_ShouldReturnTrue_WhenInitialize() {
        Range<Long> timeRange = new Range<>(0L, 0L);
        assertTrue(timeRange.isEmpty());
    }

    @Test
    public void isEmpty_ShouldReturnFalse_WhenInitializedWithProperValue() {
        Range<Long> timeRange = new Range<>(1L,5L);
        assertFalse(timeRange.isEmpty());
    }

    @Test
    public void after_ShouldReturnTrue_WhenRanges_5_6_and_3_4() {
        Range<Long> first = new Range<>(5L,6L);
        Range<Long> second = new Range<>(3L,4L);

        assertTrue(first.isAfter(second));
    }

    @Test
    public void after_ShouldReturnFalse_WhenRanges_3_6_and_3_4() {
        Range<Long> first = new Range<>(3L,6L);
        Range<Long> second = new Range<>(3L,4L);

        assertFalse(first.isAfter(second));
    }

    @Test
    public void after_ShouldReturnFalse_WhenRanges_3_4_and_5_6() {
        Range<Long> first = new Range<>(3L,4L);
        Range<Long> second = new Range<>(5L,6L);

        assertFalse(first.isAfter(second));
    }

    @Test
    public void before_ShouldReturnFalse_WhenRanges_5_6_and_3_4() {
        Range<Long> first = new Range<>(5L,6L);
        Range<Long> second = new Range<>(3L,4L);

        assertFalse(first.isBefore(second));
    }

    @Test
    public void before_ShouldReturnFalse_WhenRanges_3_6_and_3_4() {
        Range<Long> first = new Range<>(3L,6L);
        Range<Long> second = new Range<>(3L,4L);

        assertFalse(first.isBefore(second));
    }

    @Test
    public void before_ShouldReturnTrue_WhenRanges_3_4_and_5_6() {
        Range<Long> first = new Range<>(3L,4L);
        Range<Long> second = new Range<>(5L,6L);

        assertTrue(first.isBefore(second));
    }

    @Test
    public void contains_ShouldReturnTrue_WhenRanges_3_8_and_5_6() {
        Range<Long> first = new Range<>(3L,8L);
        Range<Long> second = new Range<>(5L,6L);

        assertTrue(first.isContain(second));
    }

    @Test
    public void contains_ShouldReturnFalse_WhenRanges_3_5_and_5_6() {
        Range<Long> first = new Range<>(3L,5L);
        Range<Long> second = new Range<>(5L,6L);

        assertFalse(first.isBefore(second));
    }

    @Test
    public void subset_ShouldReturnTrue_WhenRanges_3_4_and_1_6() {
        Range<Long> first = new Range<>(3L,4L);
        Range<Long> second = new Range<>(1L,6L);

        assertTrue(first.isSubset(second));
    }

    @Test
    public void subset_ShouldReturnFalse_WhenRanges_3_4_and_4_6() {
        Range<Long> first = new Range<>(3L,4L);
        Range<Long> second = new Range<>(4L,6L);

        assertFalse(first.isBefore(second));
    }

    @Test
    public void intersect_ShouldReturnTrue_WhenRanges_3_4_and_4_6() {
        Range<Long> first = new Range<>(3L,4L);
        Range<Long> second = new Range<>(4L,6L);

        assertTrue(first.isCross(second));
    }

    @Test
    public void intersect_ShouldReturnFalse_WhenRanges_3_4_and_5_6() {
        Range<Long> first = new Range<>(3L,4L);
        Range<Long> second = new Range<>(5L,6L);

        assertFalse(first.isCross(second));
    }

    @Test
       public void merge_ShouldReturnRange_3_6_WhenRanges_3_4_and_4_6() {
        Range<Long> first = new Range<>(3L,4L);
        Range<Long> second = new Range<>(4L,6L);
        Range<Long> expected = new Range<>(3L,6L);

        first.merge(second);

        assertEquals(expected, first);
    }

    @Test (expected = IllegalArgumentException.class)
    public void remove_ShouldThrowArgumentException_WhenRanges_3_4_and_5_8() {
        Range<Long> first = new Range<>(3L, 4L);
        Range<Long> second = new Range<>(5L,8L);

        first.merge(second);
    }

    @Test
    public void remove_ShouldReturnRange_3_5_WhenRanges_3_8_and_5_8() {
        Range<Long> first = new Range<>(3L,8L);
        Range<Long> second = new Range<>(5L,8L);
        Range<Long> expected = new Range<>(3L,5L);

        first.remove(second);

        assertEquals(expected, first);
    }

    @Test
    public void remove_ShouldReturnRange_3_5_WhenRanges_2_5_and_1_3() {
        Range<Long> first = new Range<>(2L,5L);
        Range<Long> second = new Range<>(1L,3L);
        Range<Long> expected = new Range<>(3L,5L);

        first.remove(second);

        assertEquals(expected, first);
    }

    @Test
    public void remove_ShouldReturnEmptyRange_WhenRanges_3_7_and_1_8() {
        Range<Long> first = new Range<>(3L,7L);
        Range<Long> second = new Range<>(1L,8L);

        first.remove(second);

        assertNull(first.getStart());
        assertNull(first.getEnd());
        assertTrue(first.isEmpty());
    }

    @Test (expected = IllegalArgumentException.class)
    public void remove_ShouldThrowArgumentException_WhenRanges_3_10_and_5_8() {
        Range<Long> first = new Range<>(3L,10L);
        Range<Long> second = new Range<>(5L,8L);

        first.remove(second);
    }

    @Test
    public void split_ShouldReturnRange_3_6_and_7_8_WhenRanges_3_8_and_6_7() {
        Range<Long> first = new Range<>(3L,8L);
        Range<Long> second = new Range<>(6L,7L);

        Range<Long> range3_6 = new Range<>(3L,6L);
        Range<Long> range7_8 = new Range<>(7L,8L);

        Range[] results = first.split(second);

        assertEquals(range3_6, results[0]);
        assertEquals(range7_8, results[1]);
    }

    @Test (expected = IllegalArgumentException.class)
    public void split_ShouldThrowException_WhenRanges_3_6_and_6_7() {
        Range<Long> first = new Range<>(3L,6L);
        Range<Long> second = new Range<>(6L,7L);

        first.split(second);

    }

    @Test
    public void split_ShouldReturnRange_Empty_and_7_8_WhenRanges_3_8_and_3_7() {
        Range<Long> first = new Range<>(3L,8L);
        Range<Long> second = new Range<>(3L,7L);

        Range<Long> range7_8 = new Range<>(7L,8L);

        Range[] results = first.split(second);

        assertTrue(results[0].isEmpty());
        assertEquals(range7_8, results[1]);
    }

    @Test
    public void split_ShouldReturnRange_3_7_and_7_8_WhenRanges_3_8_and_7_8() {
        Range<Long> first = new Range<>(3L,8L);
        Range<Long> second = new Range<>(7L,8L);

        Range<Long> range3_7 = new Range<>(3L,7L);

        Range[] results = first.split(second);

        assertEquals(range3_7, results[0]);
        assertTrue(results[1].isEmpty());
    }
}