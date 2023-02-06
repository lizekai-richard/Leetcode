# Notes

First of all, let's summarize two ways of identidying cycles in a DAG.

### DFS

One ways is DFS. Different from undirected graph, we can't simply say "if a node is visited two times, then there is a cycle." because in a directed graph, the direction of the edge also matters! Instead, we define a color array which has three values:

- $0$: means the node hasn't been searched yet.
- $1$: means the node has been searched, but the search is not finished.
- $2$: means the node is done searching and all its neightboring nodes have been visited.

Now, if some neighboring node $v$ of node $u$ is already in the search stack, then we can confirm there is a cycle.

```java
boolean dfs(int u) {
    if (color[u] == 1) {
        return true;
    }
    color[u] = 1;
    for (int v: adjList[u]) {
        if (dfs(v)) {
            return true;
        }
    }
    color[u] = 2;
    return false;
}
```

### Topological Sort

Using BFS version of topological sort, we can also identify a cycle easily. Recall that main idea of BFS topological sort will pop a node with $0$ In-degree from the queue every time. And that node is done searching. Therefore, if the number of node we search is less than the total number, then it means there must be a cycle because there is some node that cannot be reduced to $0$ in-degree. If we draw a simple diagram, we will realize the cycle will actually become a seperate component.

```java
void BFS(int u, int n) {
    queue.push(u);
    int cnt = 0;
    while (!queue.empty()) {
        int v = queue.pop();
        cnt++;
        for (int w: adjList[v]) {
            inDegree[w]--;
            if (inDegree[w] == 0) {
                queue.push(w);
            }
        }
    }
    return cnt == n;
}
```



Now, as long as we know how to identify a cycle, the rest is just a DP with topological ordering. Since topological ordering can be implemented in two ways, both **post-order** DFS + DP and BFS + DP will work.