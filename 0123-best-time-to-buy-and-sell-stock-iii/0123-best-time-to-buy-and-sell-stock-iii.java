class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int t1Cost = Integer.MAX_VALUE;
        int t2Cost = Integer.MAX_VALUE;
        int t1Profit = 0;
        int t2Profit = 0;
        
        for (int i = 0; i < n; ++i) {
            t1Cost = Math.min(t1Cost, prices[i]);
            t1Profit = Math.max(t1Profit, prices[i] - t1Cost);
            t2Cost = Math.min(t2Cost, prices[i] - t1Profit);
            t2Profit = Math.max(t2Profit, prices[i] - t2Cost);
        }
        
        return t2Profit;
    }
}