class Solution {
    private static int INF = 1000000;
    private int n;
    private int k;
    private int ans;
    private int[][] minCost;
    
    public int minCostII(int[][] costs) {
        n = costs.length;
        k = costs[0].length;
        minCost = new int[n][k];
        
        for (int i = 0; i < k; ++i) {
            minCost[0][i] = costs[0][i];
        }
        
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < k; ++j) {
                int temp = Solution.INF;
                for (int l = 0; l < k; ++l) {
                    if (l == j) continue;
                    temp = Math.min(temp, minCost[i - 1][l]);
                }
                minCost[i][j] = temp + costs[i][j];
            }
        }
        
        ans = Solution.INF;
        for (int i = 0; i < k; ++i) {
            ans = Math.min(ans, minCost[n - 1][i]);
        }
        return ans;
    }
}