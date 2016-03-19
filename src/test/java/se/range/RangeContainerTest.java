package se.range;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * RangeContainer unit tests with long implementation
 * @author  Sinan Eski
 */
public class RangeContainerTest {

    private final RangeContainer<Long, Range<Long>> rangeContainer = new RangeContainer<>();

    @Before
    public void setUp () {
        rangeContainer.add(new Range<>(2L, 3L));
        rangeContainer.add(new Range<>(6L, 9L));
    }

    @After
    public void tearDown () {
        rangeContainer.clear();
    }

    @Test
    public void add_ShouldAddToIndex_1_AsRange_4_5_WhenGivenRange_4_5() throws Exception {

        Range<Long> newRange = new Range<>(4L, 5L);
        rangeContainer.add(newRange);
        assertEquals(3, rangeContainer.size());
        assertEquals(newRange, rangeContainer.get(1));

    }

    @Test
    public void add_ShouldAddToIndex_0_AsRange_0_5_WhenGivenRange_3_5() throws Exception {

        Range<Long> mergedRange = new Range<>(2L, 5L);
        rangeContainer.add(new Range<>(3L, 5L));
        assertEquals(2, rangeContainer.size());
        assertEquals(mergedRange, rangeContainer.get(0));

    }

    @Test
    public void add_ShouldAddToIndex_0_AsRange_0_1_WhenGivenRange_0_1() throws Exception {

        Range<Long> newRange = new Range<>(0L, 1L);
        rangeContainer.add(newRange);
        assertEquals(3, rangeContainer.size());
        assertEquals(newRange, rangeContainer.get(0));

    }

    @Test
    public void intersect_ShouldChangeIndex_1_AsRange_8_9_WhenGivenRange_8_15() throws Exception {
        Range<Long> givenRangeToIntersect = new Range<>(8L, 15L);
        Range<Long> resultRange = new Range<>(8L, 9L);
        rangeContainer.intersect(givenRangeToIntersect);
        assertEquals(2, rangeContainer.size());
        assertEquals(resultRange, rangeContainer.get(1));
    }

    @Test
    public void intersect_ShouldChangeList_AsRange_8_9_WhenGivenRange_1_2_and_8_15() throws Exception {
        List<Range<Long>> givenRangeToIntersectList = new ArrayList<>();
        givenRangeToIntersectList.add(new Range<>(1L, 2L));
        givenRangeToIntersectList.add(new Range<>(8L, 15L));

        Range<Long> firstIndexRange = new Range<>(8L, 9L);

        rangeContainer.intersect(givenRangeToIntersectList);

        assertEquals(1, rangeContainer.size());
        assertEquals(firstIndexRange, rangeContainer.get(0));
    }
}