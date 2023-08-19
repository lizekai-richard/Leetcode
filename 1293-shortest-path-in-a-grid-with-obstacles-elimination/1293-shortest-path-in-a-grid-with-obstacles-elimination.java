class Solution {
    
    class State {
        public int x;
        public int y;
        public int k;
        public int s;
        public State(int x, int y, int k, int s) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.s = s;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof State)) {
                return false;
            }
            State s = (State) o;
            return s.x == this.x && s.y == this.y && s.k == this.k;
        }
    }
    
    public int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        
        State initial_state = new State(0, 0, k, 0);
        Deque<State> q = new LinkedList<>();
        q.add(initial_state);
        
        boolean[][][] visited = new boolean[m][n][k + 1];
        visited[0][0][k] = true;
        
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        
        int ans = -1;
        
        while (!q.isEmpty()) {
            State s = q.pollFirst();
            
            if (s.x == m - 1 && s.y == n - 1) {
                ans = s.s;
                break;
            }
            
            for (int i = 0; i < 4; ++i) {
                int new_x = s.x + dx[i];
                int new_y = s.y + dy[i];
                
                if (new_x >= 0 && new_x < m && new_y >= 0 && new_y < n) {
                    if (grid[new_x][new_y] == 0) {
                        State new_state = new State(new_x, new_y, s.k, s.s + 1);
                        if (!visited[new_x][new_y][s.k]) {
                            q.add(new_state);
                            visited[new_x][new_y][s.k] = true;
                        }
                    } else if (s.k > 0) {
                        State new_state = new State(new_x, new_y, s.k - 1, s.s + 1);
                        if (!visited[new_x][new_y][s.k - 1]) {
                            q.add(new_state);
                            visited[new_x][new_y][s.k - 1] = true;
                        }
                    }
                }
            }
        }
        return ans;
    }
}