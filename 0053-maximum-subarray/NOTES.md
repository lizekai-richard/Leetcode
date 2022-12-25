# Notes

The Dynamic Programming solution to this question is quite straight forward. We are discussing another approach here, Divide and Conquer.

After we divide the array into halves, there will be three situations:

1. the maximum subarray sum lies completely in the left part
2. the maximum subarray sum lies completely in the right part
3. the maximum subarray sum lies in both left and right

The first two situations are just the sub problems and we only need to recurse. For the last scenario, we need to figure out what is the maximum subarray sum. 

Well, one important fact is we must include the middle element in the third scenario. Therefore, we can start from the middle element and $O(n)$ scan through the left an right to find the maximum sum.

```java
int curSum = 0;
int maximumLeftSum = 0;
for (int i = mid - 1; i >= 0; --i) {
	curSum += nums[i];
	maximumLeftSum = Math.max(maximumLeftSum, curSum);
}

int curSum = 0;
int maximumRightSum = 0;
for (int i = mid + 1; i < n; ++i) {
	curSum += nums[i];
	maximumRightSum = Math.max(maximumRightSum, curSum);
}

int maximumSum = maximumLeftSum + nums[mid] + maximumRightSum;
```

The final answer should be the maximum of results of three different scenarios.