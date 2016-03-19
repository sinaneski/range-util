package se.range;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * TimeRange unit tests
 * @author  Sinan Eski
 */
public class TimeRangeTest {

    @Test
    public void getDuration_ShouldReturn3_WhenRange_9_12() throws Exception {
        TimeRange timeRange = new TimeRange(9L, 12L);
        assertEquals (3, timeRange.getDuration());
    }

    @Test
    public void getDuration_ShouldReturn0_WhenRange_6_5() throws Exception {
        TimeRange timeRange = new TimeRange(6L,5L);
        assertEquals (0, timeRange.getDuration());
    }
}