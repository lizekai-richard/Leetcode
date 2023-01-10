class Solution {
    private HashMap<Integer, List<Integer>> adjList;
    private int[] size;
    private int[] dp;
    private int n;
    
    private void postOrderDFS(int u, int parent) {
        List<Integer> nbrs = adjList.get(u);
        for (int v: nbrs) {
            if (v != parent) {
                postOrderDFS(v, u);
                size[u] += size[v];
                dp[u] = dp[u] + dp[v] + size[v];
            }
        }
    }
    
    private void preOrderDFS(int u, int parent) {
        List<Integer> nbrs = adjList.get(u);
        for (int v: nbrs) {
            if (v != parent) {
                dp[v] = dp[u] - size[v] + (n - size[v]);
                preOrderDFS(v, u);
            }
        }
    }
    
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        if (n == 1) {
            return new int[]{0};
        }
        
        this.n = n;
        adjList = new HashMap<>();
        size = new int[n];
        dp = new int[n];
        Arrays.fill(size, 1);
        
        for (int i = 0; i < edges.length; ++i) {
            int[] edge = edges[i];
            if (!adjList.containsKey(edge[0])) {
                adjList.put(edge[0], new ArrayList<>());
            }
            if (!adjList.containsKey(edge[1])) {
                adjList.put(edge[1], new ArrayList<>());
            }
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        
        postOrderDFS(0, -1);
        preOrderDFS(0, -1);
        return dp;
    }
}