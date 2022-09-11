# Solution

The method used in this question is very similar to the one I used in the question **Trapping Water**. First, based on the question, we can easilty come up with a $O(nm^2)$ dynamic programming solution. The trasition function should be:
$$
dp[i][j]\ =\ \max\{dp[i-1][k] - abs(j-k)\} + points[i][j] \ \ \ \ j,k \in \{0,1,...,m-1\}
$$
The problem here is to get the maximum of state values from the last row, which takes $O(m)$ time. Now, our direction is how to make it $O(1)$.

Now, if you still remember the **Trapping Water** question, it won't be hard. We just need to maintain two auxiliary arrays: $forwardMax$ and $backwardMax$. They are aimed to keep track of the maximum points from both directions. Then, when we want to get the maximum points of the last row at position $j$, we only need to check:
$$
\max\{forwardMax[j], backwardMax[j]\}
$$
Maintaining these two arrays for each row takes $O(m)$ time and checking takes $O(1)$ time. So overall, it takes $O(nm)$ time.

Below is how we maintain these two arrays:
$$
forwardMax[j]\ =\ \max\{forwardMax[j-1],\ dp[i-1][j]+j\} \\
backwardMax[j]\ =\ \max\{backwardMax[j+1],\ dp[i-1][j]-j\}
$$
