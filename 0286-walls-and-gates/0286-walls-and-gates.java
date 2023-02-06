class Solution {
    private int[] dx = new int[]{0, 1, 0, -1};
    private int[] dy = new int[]{1, 0, -1, 0};
    private List<int[]> gates;
    
    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        int n = rooms[0].length;
        gates = new ArrayList<int[]>();
        
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (rooms[i][j] == 0) {
                    gates.add(new int[]{i, j});
                }
            }
        }
        
        for (int[] gate: gates) {
            Queue<int[]> q = new LinkedList<>();
            boolean[][] visited = new boolean[m][n];
            q.add(gate);
            boolean flag = false;
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int curx = cur[0];
                int cury = cur[1];
                for (int i = 0; i < 4; ++i) {
                    int newx = curx + dx[i];
                    int newy = cury + dy[i];
                    if (newx < 0 || newy < 0 || newx >= m || newy >= n) continue;
                    if (rooms[newx][newy] == -1) continue;
                    if (!visited[newx][newy]) {
                        if (rooms[newx][newy] > rooms[curx][cury] + 1) {
                            rooms[newx][newy] = rooms[curx][cury] + 1;
                            visited[newx][newy] = true;
                            q.add(new int[]{newx, newy});
                        }
                    }  
                }
                // if (flag) {
                //     break;
                // }
            }
        }
    }
}