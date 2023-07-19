class Solution {
    public int[][] grid;
    public int[][][] dp;
    public int n;
    public int m;
    
    public int dfs(int x, int y1, int y2) {
        if (y1 < 0 || y2 < 0 || y1 >= m || y2 >= m || x == n) {
            return 0;
        }

        if (dp[x][y1][y2] != -1) {
            return dp[x][y1][y2];
        }
        int new_c = (y1 == y2 ? grid[x][y1] : grid[x][y1] + grid[x][y2]);
        
        int max = 0;
        for (int _y1 = y1 - 1; _y1 <= y1 + 1; _y1++) {
            for (int _y2 = y2 - 1; _y2 <= y2 + 1; _y2++) {
                max = Math.max(max, dfs(x + 1, _y1, _y2));
            }
        } 
        dp[x][y1][y2] = (max + new_c);
        return dp[x][y1][y2];
        
    }
    public int cherryPickup(int[][] grid) {
        this.grid = grid;
        n = grid.length;
        m = grid[0].length;
        this.dp = new int[n][m][m];
        
        for (int x = 0; x < n; ++x) {
            for (int y1 = 0; y1 < m; ++y1) {
                for (int y2 = 0; y2 < m; ++y2) {
                    dp[x][y1][y2] = -1;
                }
            }
        }
        return dfs(0, 0, m - 1);
    }
}