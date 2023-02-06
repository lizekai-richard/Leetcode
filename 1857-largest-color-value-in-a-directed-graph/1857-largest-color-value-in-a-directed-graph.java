class Solution {
    private int n;
    private HashMap<Integer, List<Integer>> adjList;
    private int[] nodeColor;
    private int[][] dp;
    private boolean[] inStack;
    private boolean[] visited;
    
    private boolean dfs(int u) {
        if (inStack[u]) {
            return true;
        }
        if (visited[u]) {
            return false;
        }
        visited[u] = true;
        inStack[u] = true;
        if (!adjList.containsKey(u)) {
            dp[u][nodeColor[u]]++;
            inStack[u] = false;
            return false;
        }
        List<Integer> neighbors = adjList.get(u);
        for (int v: neighbors) {
            if (dfs(v)) {
                return true;
            }
            for (int c = 0; c < 26; ++c) {
                dp[u][c] = Math.max(dp[u][c], dp[v][c]);
            }
        }
        dp[u][nodeColor[u]]++;
        inStack[u] = false;
        return false;
    }
    
    public int largestPathValue(String colors, int[][] edges) {
        adjList = new HashMap<>();
        n = colors.length();
        for (int[] edge: edges) {
            int u = edge[0], v = edge[1];
            if (!adjList.containsKey(u)) {
                adjList.put(u, new ArrayList<Integer>());
            }
            adjList.get(u).add(v);
        }
        nodeColor = new int[n];
        for (int i = 0; i < colors.length(); ++i) {
            nodeColor[i] = (int)colors.charAt(i) - (int)'a';
        }
        
        dp = new int[n][26];
        visited = new boolean[n];
        inStack = new boolean[n];
        Arrays.fill(visited, false);
        for (int i = 0; i < n; ++i){
            if (!visited[i]) {
                Arrays.fill(inStack, false);
                if(dfs(i)) {
                    return -1;
                }
            }
        }
        
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 26; ++j) {
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }
}