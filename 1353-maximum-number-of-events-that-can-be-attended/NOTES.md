# Notes

This is another great question about Heap, or Priority Queue.

First, we should sort the events by the start time because if an event starts early but we attend it late, then we probably will miss this event.

After that, consider every single day, which events should we attend? There might be many options, many available events, how do we decide? Well, if we use some greedy thought, we realize we should pick the one that ends earliest. But finding that event requires $O(N)$ time. TLE alert! That's why we are using Heap, which allows us dynamically add or delete elements in $O(\log n)$ time. 

On each day $d$, we push the end time of events that are available into the heap. At the same time, we pop all end time of events that are ealier than this day. This is because these events are not attendable anymore. Then, we just pop the top of the heap (which ends earliest) and attend that event on the day $d$, and plus $1$ to the answer.