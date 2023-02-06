class Solution {
    private boolean[] visited;
    private int[][] dp;
    private List<Integer>[] adj;
    public int largestPathValue(String colors, int[][] edges) {
        if(edges.length == 0) 
            return 1;
        
        adj = new List[colors.length()];
        for(int[] edge: edges) {
            if(adj[edge[0]] == null) {
                adj[edge[0]] = new ArrayList<>();
            }
            
            adj[edge[0]].add(edge[1]);
        }
        
        visited = new boolean[colors.length()];
        dp = new int[colors.length()][26]; 
        
        int max = 0;
        for(int i = 0; i < colors.length(); i++) {
            if(!visited[i]) {
                if(dfs(colors, new boolean[colors.length()], i)) {
                    return -1;
                }
                
                // dp[i][c] = max number of times we see color c
                // in any given path starting from node i
                for(int c = 0; c < 26; c++) {
                    max = Math.max(dp[i][c], max);
                }
            }
        }
        
        return max;
    }
    
    // return true if cycle detected.
    private boolean dfs(
        String colors, 
        boolean[] inStack,
        int node
    ) {
        
        if(inStack[node]) {
            return true;
        }
        
        if(visited[node]) {
            return false;
        }
        
        inStack[node] = true;
        visited[node] = true;
        
        if(adj[node] != null) {
            for(int nbr: adj[node]) {
                if(dfs(colors, inStack, nbr)) {
                    return true;
                }

                for(int c = 0; c < 26; c++) {
                    dp[node][c] = Math.max(dp[node][c], dp[nbr][c]);
                }
            }
        }
        
        dp[node][colors.charAt(node) - 'a']++;
        inStack[node] = false;
        
        return false;
    }    
}