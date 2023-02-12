class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        List<Integer>[] adjList = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; ++i) {
            adjList[i] = new ArrayList<>();
        }
        Arrays.fill(indegree, 0);
        for (int[] edge: prerequisites) {
            int a = edge[0];
            int b = edge[1];
            adjList[b].add(a);
            indegree[a]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        int[] order = new int[numCourses];
        int count = 0;
        for (int i = 0; i < numCourses; ++i) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        
        while (!queue.isEmpty()) {
            int u = queue.poll();
            order[count] = u;
            count++;
            for (int v: adjList[u]) {
                indegree[v]--;
                if (indegree[v] == 0) {
                    queue.add(v);
                }
            }
        }
        
        if (count < numCourses) {
            return new int[]{};
        }
        
        return order;
    }
}