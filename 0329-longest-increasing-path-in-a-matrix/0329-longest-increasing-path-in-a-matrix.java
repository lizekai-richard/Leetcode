class Solution {
    private int m;
    private int n;
    private int[] dp;
    private int[][] matrix;
    private int[] indegree;
    private HashMap<Integer, List<Integer>> adjList;
    
    public int longestIncreasingPath(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        dp = new int[m * n];
        this.matrix = matrix;
        adjList = new HashMap<>();
        indegree = new int[n * m];
        
        Arrays.fill(indegree, 0);
        Arrays.fill(dp, 1);
        int ans = 0;
        
        for (int i = 0; i < m * n; ++i) {
            int x = i / n;
            int y = i % n;
            adjList.put(i, new ArrayList<>());
            if (x - 1 >= 0 && matrix[x][y] < matrix[x - 1][y]) {
                int j = (x - 1) * n + y;
                adjList.get(i).add(j);
                indegree[j]++;
            }
            if (y - 1 >= 0 && matrix[x][y] < matrix[x][y - 1]) {
                int j = x * n + y - 1;
                adjList.get(i).add(j);
                indegree[j]++;
            }
            if (x + 1 < m && matrix[x][y] < matrix[x + 1][y]) {
                int j = (x + 1) * n + y;
                adjList.get(i).add(j);
                indegree[j]++;
            }
            if (y + 1 < n && matrix[x][y] < matrix[x][y + 1]) {
                int j = x * n + y + 1;
                adjList.get(i).add(j);
                indegree[j]++;
            }
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < m * n; ++i) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v: adjList.get(u)) {
                indegree[v]--;
                dp[v] = Math.max(dp[v], dp[u] + 1);
                if (indegree[v] == 0) {
                    queue.add(v);
                }
            }
        }
        
        for (int i = 0; i < m * n; ++i) {
            ans = Math.max(ans, dp[i]);
        }
        
        return ans;
    }
}