# Notes

A very interesting question, especially the dynamic programming solution. Define $dp[i]$ to be the longest valid sequence ending at index $i$. Similar to the decision problem, we only need to update when we reach a $)$. Let's say $s_i$ is a right parenthese, then there are two scenarios to consider:

1. $s_{i-1}$ is a left parenthese: In this case, we can directly find the optimal sub-problem, which is $dp[i - 2]$. So $dp[i] = dp[i-2]+2$
2. $s_{i-1}$ is a right parenthese: In this case, we also need to find the optimal sub-problem. Now, think about what $dp[i-1]$ means. It is the longest valid sequence ending at $i-1$. Therefore, we can check if $i-dp[i-1]-1$ is a left parenthese. If it is, we find the optimal sub-problem, which is $dp[i-dp[i-1]-2]$ and can update $dp[i] = dp[i-1] + dp[i-dp[i-1]-2] + 2$.  

It is rare to see DP value inside the aray indexing. However, this question broadens your horizon.