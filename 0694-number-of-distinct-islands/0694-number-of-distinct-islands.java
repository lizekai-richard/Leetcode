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
        
        // int count = 0;
        // HashMap<Integer, Boolean> exists = new HashMap<>();
        // for (int i = 0; i < islands.size(); ++i) {
        //     List<Pair<Integer, Integer>> island1 = islands.get(i);
        //     int h1 = island1.hashCode();
        //     if (!exists.containsKey(h1)) {
        //         exists.put(h1, true);
        //         count++;
        //     }
        //     for (int j = i + 1; j < islands.size(); ++j) {
        //         List<Pair<Integer, Integer>> island2 = islands.get(j);
        //         if (island1.size() != island2.size()) continue;
        //         int h2 = island2.hashCode();
        //         if (!exists.containsKey(h2)) {
        //             exists.put(h2, true);
        //             count++;
        //         }
        //     }
        // }
        return islands.size();
    }
}