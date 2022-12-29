# Notes

We introduce two solutions to this problem.

## Solution 1

Solution 1 is based on Dynamic Programming. This problem is tricker then "Maximum Subarry Sum" because of the existance of **zeros** and **negative numbers**. For negative numbers, the difficulty we encounter is that we are not sure whether to multiply or not. Even if multiplying a negative number to a positive result would make the new result negative, there is chance that we will have another negative number later which can help us reverse the result. 

So in this question, we not only consider the maximum product so far, but also consider the minimum product so far. The intuition comes from the fact that the maximum product could be a result of very large positive product times another positive value or very small negative product times another negative value. 

At each position, we consider the following three values:

1. The current number `nums[i]`
2. The product of `nums[i]` and `maxSoFar`
3. The product of `nums[i]` and `minSoFar`

Then, we update the `maxSoFar` and `minSoFar` using these three values:

$maxSoFar'\ =\ \max(nums[i], nums[i]\times maxSoFar,\ nums[i]\times minSoFar)$

$minSoFar'\ =\ \min(nums[i], nums[i]\times maxSoFar,\ nums[i]\times minSoFar)$

The final answer should be the maximum among all `maxSoFar`.

## Solution 2

This solution really gives me a shock since I never thought it could be this simple. It turns out we only need to compute the maximum product twice, forward and backward, and take the max. The reason is that the subarray we are looking for cannot lie in the middle of the original array. It has to include at least one of the ends. This can be proved considering three scenarios. 

1. +ve Subarray +ve: Obiviously, we can extend the subarray.
2. -ve Subarray -ve: Same as the first scenario.
3. +ve(-ve) Subarray -ve(+ve): Take one of the ends and extend the subarray.

