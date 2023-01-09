class Solution {
    private int numCourses;
    private int[][] prerequisites;
    private int[] est;
    
    private boolean bellmanFord() {
        for (int i = 0; i < numCourses; ++i) {
            for (int j = 0; j < prerequisites.length; ++j) {
                int[] edge = prerequisites[j];
                int u = edge[0];
                int v = edge[1];
                if (est[u] > est[v] - 1) {
                    est[u] = est[v] - 1;
                }
            }
        }
        for (int j = 0; j < prerequisites.length; ++j) {
            int[] edge = prerequisites[j];
            int u = edge[0];
            int v = edge[1];
            if (est[u] > est[v] - 1) {
                return false;
            }
        }
        return true;
    }
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        this.numCourses = numCourses;
        this.prerequisites = prerequisites;
        this.est = new int[numCourses];
        Arrays.fill(est, Integer.MAX_VALUE);
        est[0] = 0;
        return bellmanFord();
    }
}