# Notes

For this question, we summarize two approaches.

## Approach 1

Note that a naive fact is that we should arrange conferences by the ascending order of their start time. So the first step is always sort all intervals by the start time. Next, how to complete the allocation? Consider for each meeting, there are two possible scenarios:

1. All previous meetings are ongoing, and there are no available rooms. 
2. Some previous meeting has ended and a room has been freed up.

In the first scenario, we have no choice but to allocate a new room. In the second scenario, the number of rooms remains the same. However, the point is how do we know if there is a free room? Well, one fact is that we only need to consider the meeting that ends earliest (use a little greedy algorithm), and we just need to compare the ending time of that meeting with the starting time of te current meeting. Therefore, we can implement a **Min-Heap** or a **Priority Queue** that stores ending time of each ongoing meeting that occupies different rooms. Every time a new meeting comes, we do the checking using the top element of the queue or heap. The time complexity is $O(NlogN)$.

## Approach 2

This approach is interesting. In fact, we can sort start time and end time seperately. 

<img src="NOTES.assets/Screenshot 2022-12-17 at 11.07.17 AM.png" alt="Screenshot 2022-12-17 at 11.07.17 AM" style="zoom:50%;" />

Whenever we have a meeting about to end, we don't care which one it is. What we care is that there is a room about to be available and the next meeting can be allocated to that room. Then, consider how we determine if a meeting is about to end or not? Well, in this case, if some start time is larger than some end time, then it means there is one ending meeting because we are using start times as our timeline. The solution goes as follows:

1. Separate out the start times and the end times in their separate arrays.
2. Sort the start times and the end times separately. Note that this will mess up the original correspondence of start times and end times. They will be treated individually now.
3. We consider two pointers: `s_ptr` and `e_ptr` which refer to start pointer and end pointer. The start pointer simply iterates over all the meetings and the end pointer helps us track if a meeting has ended and if we can reuse a room.
4. When considering a specific meeting pointed to by `s_ptr`, we check if this start timing is greater than the meeting pointed to by `e_ptr`. If this is the case then that would mean some meeting has ended by the time the meeting at `s_ptr` had to start. So we can reuse one of the rooms. Otherwise, we have to allocate a new room.
5. If a meeting has indeed ended i.e. if `start[s_ptr] >= end[e_ptr]`, then we increment `e_ptr`.
6. Repeat this process until `s_ptr` processes all of the meetings.