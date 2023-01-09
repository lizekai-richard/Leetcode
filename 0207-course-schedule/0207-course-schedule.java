class Solution {
    private HashMap<Integer, List<Integer>> adjList;
    private boolean[] checked;
    private boolean[] path;
    
    private boolean dfs(int node) {
        if (checked[node]) {
            return false;
        }
        if (path[node]) {
            return true;
        }
        if (!adjList.containsKey(node)) {
            return false;
        }
        path[node] = true;
        List<Integer> nbrNodes = adjList.get(node);
        boolean res = false;
        for (int u: nbrNodes) {
            res = dfs(u);
            if (res) {
                break;
            }
        }
        checked[node] = true;
        path[node] = false;
        return res;
    }
    
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        adjList = new HashMap<>();
        checked = new boolean[numCourses];
        path = new boolean[numCourses];
        
        for (int i = 0; i < prerequisites.length; ++i) {
            int[] edge = prerequisites[i];
            if (!adjList.containsKey(edge[1])) {
                adjList.put(edge[1], new ArrayList<>());
            }
            adjList.get(edge[1]).add(edge[0]);
        }
        
        for (int i = 0; i < numCourses; ++i) {
            if (dfs(i)) {
                return false;
            }
        }
        
        return true;
    }
}