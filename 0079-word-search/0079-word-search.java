class Solution {
    public int n;
    public int m;
    public String word;
    public char[][] board;
    public boolean[][] visited;
    public int[] dx = {1, 0, -1, 0};
    public int[] dy = {0, 1, 0, -1};
    
    public boolean dfs(int x, int y, int step) {
        if (step == this.word.length()) {
            return true;
        }
        
        boolean res = false;
        for (int i = 0; i < 4; ++i) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            if (newX >= 0 && newX < n && newY >= 0 && newY < m && !visited[newX][newY] && board[newX][newY] == word.charAt(step)) {
                visited[newX][newY] = true;
                res = res | dfs(newX, newY, step + 1);
                visited[newX][newY] = false;
            }
        }
        return res;
    }
    
    public boolean exist(char[][] board, String word) {
        this.n = board.length;
        this.m = board[0].length;
        this.word = word;
        this.board = board;
        this.visited = new boolean[n][m];
        
        boolean ans = false;
        for (int x = 0; x < n; ++x) {
            for (int y = 0; y < m; ++y) {
                if (board[x][y] == word.charAt(0)) {
                    visited[x][y] = true;
                    ans = ans | dfs(x, y, 1);
                    visited[x][y] = false;
                }
            }
        }
        return ans;
    }
}