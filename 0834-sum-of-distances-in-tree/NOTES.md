# Notes

This question is a typical DP on tree. However, what makes it "hard" is the trick to reduce the time complexity from $O(N^2)$ to $O(N)$. 

First, consider the answer for the tree rooted at node $u$. We can easily come up with a DP solution and the transition would be:
$$
dp[u]\ =\ \sum_{v \in N(u)}dp[v] + size[v]
$$
Basically, as long as we know the answers of its subtrees, we can update the answer of node $u$ by adding them with the number of nodes in the subtree. For example, as shown below, if we already know that the sum of distances in the tree rooted at node $2$, then for node $0$, we just need to consider how many times we should add the edge $(0, 2)$. And that number is exactly the size of the subtree.

<img src="NOTES.assets/Screenshot 2023-01-10 at 1.03.10 PM.png" alt="Screenshot 2023-01-10 at 1.03.10 PM" style="zoom:50%;" />

But now, the issue is we need to compute the sum of distances for every node in the tree. If we simply add an outer loop, then the time complexity would be $O(N^2)$. Here comes the trick: in the previous part, we are applying a post-order DFS that first computes the result of subtrees then updates for the current node. Actually, after we know the result of the root, we can use it to update it children following the same relationship we just found. 

Let's think about one edge $(u, v)$. If we compute the answer for $u$, then 
$$
dp[u]\ =\ f[u] + f[v] + size[v]
$$
$f$ is the sum of distances in the subtree. Similar, for $v$, we have
$$
dp[v]\ =\ f[v] + f[u] + size[u]
$$
Subtract equation (2) and (3), we get:
$$
dp[v]\ =\ dp[u]\ -\ size[v] + (n - size[v])
$$
Notice that by running the post-order DFS, we already know $dp[u]$, so we can run another pre-order DFS to update all the children.