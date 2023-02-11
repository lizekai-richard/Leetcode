class Solution {
    private int m;
    private int n;
    private int[][] grid;
    private boolean[][] visited;
    private int[] dx = new int[]{0, 1, 0, -1};
    private int[] dy = new int[]{1, 0, -1, 0};
    
    private void dfs(int x, int y, int[] min, List<Pair<Integer, Integer>> coors) {
        coors.add(new Pair<>(x, y));
        for (int i = 0; i < 4; ++i) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            if (newX < 0 || newY < 0 || newX >= m || newY >= n) continue;
            if (!visited[newX][newY] && grid[newX][newY] == 1) {
                visited[newX][newY] = true;
                min[0] = Math.min(min[0], newX);
                min[1] = Math.min(min[1], newY);
                dfs(newX, newY, min, coors);
            }
        }
    }
    
    private long hash(List<int[]> island) {
        long hashcode = 1;
        for (int[] coor: island) {
            hashcode = (31 * hashcode + 1l * Arrays.hashCode(coor));
        }
        return hashcode;
    }
    
    public int numDistinctIslands(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        this.visited = new boolean[m][n];
        
        Set<Set<Pair<Integer, Integer>>> islands = new HashSet<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    visited[i][j] = true;
                    List<Pair<Integer, Integer>> coors = new ArrayList<>();
                    int[] min = new int[]{i, j};
                    dfs(i, j, min, coors);
                    Set<Pair<Integer, Integer>> newCoors = new HashSet<>();
                    for (Pair<Integer, Integer> coor: coors) {
                        Pair<Integer, Integer> newCoor = new Pair<>(coor.getKey() - min[0], coor.getValue() - min[1]);
                        newCoors.add(newCoor);
                    }
                    islands.add(newCoors);
                }
            }
        }
        
        return islands.size();
    }
}