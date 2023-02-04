# Notes

Honestly, if there is nobody offereing any hint about using DP, I won't think of DP at the very beginning. However, after reading the first hint and knowing we can use DP to solve this problem, life becomes much easier.

An important fact is that no matter how you cut the pizza, the remaining piece will always have the bottom right corner of $(n - 1, m - 1)$. Based on that, the state representation is quite straight forward. Define $dp[r][c][k]$ to be the number of ways to cut a pizza with top left corner of $(r, c)$ in $k$ cuts. The implementation is much easier if we apply a memoization search. 

For every state, we recursively compute all possible horizontal cuts and vertical cuts. The result of current state should be the sum of all possible horizontal cuts and vertical cuts. Don't forget to check if the cut is valid, which means both the piece you give out and the piece you keep should contain at least one apple. The time complexity is $O((n + m)mnk)$.