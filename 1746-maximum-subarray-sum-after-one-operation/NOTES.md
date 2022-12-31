# Notes

This question is kind of like the combination of the "**Maximum Subarray Sum**" and "**Max Consecutive Ones II**". In this question, I made two mistakes:

1. Squaring element operation is for both positive and negative terms. It's quite common for us to think that squaring is only for negative elements. However, a very simple counter-example would be an array like this: `[-1, 1000]`. Be really careful.

2. When considering the state that we used squaring operation at the current element, specifically `dp[i][1]`, there are actually three scenarios to think about:

   1. `dp[i-1][1]+nums[i]`
   2. `dp[i-1][0]+nums[i]*nums[i]`
   3. `nums[i]*nums[i]`

   We should take the maximum of the three. I missed the last case which means `dp[i-1][0]` is actually negative so we should just restart at the current element.