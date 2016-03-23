# range-util

range-util is generic **Range** and **RangeContainer** implementation. 

**Range** holds boundaries between [start, end]. It could be used for time periods, measurements, intervals, ... etc. 
**RangeContainer**  has a list of sequential ranges and supplies basic operations. 

**Supported Range operations**

methods | Definition
------- | -------
merge(Range other)          | If there is an intersection, merge this range with the other using union operation.
intersect(Range other)      | If there is an intersection, update boundaries according to overlapped parts between ranges.
remove(Range other)         | Remove intersection part from this range.
split(Range other)          | If this range is subset of other, create two range by removing other from this range.
isEmpty()                   | Check if this range is empty; has valid boundry values.
isBefore(Range other)       | Check this range is totally smaller than the other.
isAfter(Range other)        | Check this range is totally greater than the other.
isIntersect(Range other)    | Check this range has any kind of intersection (contaion, cross, subset).
isContain(Range other)      | Check whether this range totally contains the other range or not
isSubset(Range other)       | Check whether the other range totally contains this range or not
isCross(Range other)        | Check the intersection with other is partially.
isPrePartCross(Range other) | Check this range partially overlap with other from start side.
isPostPartCross(Range other)| Check this range partially overlap with other from end side.

**Supported RangeContainer operations**

Methods | Definition
------- | -------
add(Range newRange)           | Add a new range to the container. If there is an intersection, apply union operation.
intersect(Range range)        | Find the ranges which have intersection with given range and apply intersect operation.
intersect(List<Range> ranges) | Apply intersect operation for all given ranges.

**TimeRange** is an implementation of *Range<Long>* for time interval operations. This class support *getDuration()* method to measure elapsed time.  
**TimeRangeContainer** is an implementation of *RangeContainer<TimeRange, Range<Long>>* for sequential time interval operations. This class support *getTotalDuration()* method to measure total elapsed time.
  
  
## LICENCE
  
This project is licensed under the terms of the MIT license.