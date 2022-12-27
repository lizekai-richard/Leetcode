class Solution {
    private int[][] maxProfit;
    
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        // define dp[i][0] to represent the maximum profit with a stock on the ith day
        // define dp[i][1] to represent the maximum profit without a stock on the ith day
        maxProfit = new int[n][2];
        
        maxProfit[0][0] = 0;
        maxProfit[0][1] = -prices[0];
        
        for (int i = 1; i < n; ++i) {
            maxProfit[i][0] = Math.max(maxProfit[i - 1][0], maxProfit[i - 1][1] + prices[i] - fee);
            maxProfit[i][1] = Math.max(maxProfit[i - 1][1], maxProfit[i - 1][0] - prices[i]);
        }
        
        return Math.max(maxProfit[n - 1][0], maxProfit[n - 1][1]);
    }
}