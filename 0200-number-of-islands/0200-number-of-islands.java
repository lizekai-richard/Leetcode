class Solution {
    private int m;
    private int n;
    private int[] dx = new int[]{1, 0, -1, 0};
    private int[] dy = new int[]{0, 1, 0, -1};
    private char[][] grid;
    private boolean[][] visited;
    private int numIslands = 0;
    
    public void dfs(int x, int y) {
        for (int i = 0; i < 4; ++i) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            if (newX >= 0 && newY >= 0 && newX < m && newY < n) {
                if (grid[newX][newY] == '1' && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    dfs(newX, newY);
                }
            }
        }
    }
    public int numIslands(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        visited = new boolean[m][n];
        
        for (int i = 0; i < m; ++i) {
            Arrays.fill(visited[i], false);
        }
        
        for (int x = 0; x < m; ++x) {
            for (int y = 0; y < n; ++y) {
                if(grid[x][y] == '1' && !visited[x][y]) {
                    dfs(x, y);
                    numIslands++;
                }
            }
        }
        
        return numIslands;
    }
}