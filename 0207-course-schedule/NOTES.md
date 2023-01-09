# Notes

This question can be considered as a good template for detecting cycles in a directed graph. As described in the question, we are asked to find out if we can complete all courses. We are given a list of pairs of course $[a_i, b_i]$ and $b_i$ is the prerequisite of $a_i$. Based on that, we can easily construct a directed graph and each edge represents this prerequisite relationship.

The next question is, how do we determine whether a cycle exists or not? Well there are mainly three methods:

1. **Bellman-Ford**: Recall that Bellman-Ford algorithm is one of the SSSP algorithms. One special thing about Bellman-Ford is that it can detect negative weight cycle. Well, that's exactly what we want. As for the negative weight, we just simply assign each edge with weight $-1$. However, the cost is high. Time complexity is $O(|E||V|)$

2. **DFS**: Now, let's think about what a cycle actually tells us. Consider a simple case of a three-node cycle. When we traverse on this graph, let's say start from node $0$, then we will arrive at node $0$ for the second time. Therefore, we can come up with a solution which maintain a boolean array representing if the node is currently on the path. If the value is true when we visit a new node, then it means this is the second time we visit it, and we find a cycle. Another detail is that we will be using backtracking since once a node is done, we need to remove it from the path. Time complexity is $O(|E| + |V|)$.

   ```java
   private boolean dfs(int node) {
       // if already checked, then return
       if (checked[node]) {
           return false;
       }
       // if aleady on the path, then cycle detected
       if (path[node]) {
           return true;
       }
       if (!adjList.containsKey(node)) {
           return false;
       }
       // mark the current node
       path[node] = true;
       List<Integer> nbrNodes = adjList.get(node);
       boolean res = false;
       for (int u: nbrNodes) {
           res = dfs(u);
           if (res) {
               break;
           }
       }
       checked[node] = true;
       // backtracking
       path[node] = false;
       return res;
   }
   ```

   

3. **Kahn's Algorithm**: This is doing the topological sort. We can compare the number of edges we deleted and the number of edges we have. If they don't match, then it means there is a cycle because apply Topological Sort on a DAG should delete all edges.