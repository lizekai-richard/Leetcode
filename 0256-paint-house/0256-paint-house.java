class Solution {
    private int[][] minCost;
    public int minCost(int[][] costs) {
        int n = costs.length;
        minCost = new int[n][3];
        minCost[0][0] = costs[0][0];
        minCost[0][1] = costs[0][1];
        minCost[0][2] = costs[0][2];
        
        for (int i = 1; i < n; ++i) {
            minCost[i][0] = Math.min(minCost[i - 1][1], minCost[i - 1][2]) + costs[i][0];
            minCost[i][1] = Math.min(minCost[i - 1][0], minCost[i - 1][2]) + costs[i][1];
            minCost[i][2] = Math.min(minCost[i - 1][0], minCost[i - 1][1]) + costs[i][2];
        }
        return Math.min(minCost[n - 1][0], Math.min(minCost[n - 1][1], minCost[n - 1][2]));
    }
}