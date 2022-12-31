class Solution {
    private int[] minSoFar;
    private int ans;
    
    public int maxProfit(int[] prices) {
        int n = prices.length;
        minSoFar = new int[n];

        minSoFar[0] = prices[0];
        ans = 0;
        for (int i = 1; i < n; ++i) {
            ans = Math.max(ans, prices[i] - minSoFar[i - 1]);
            minSoFar[i] = Math.min(minSoFar[i - 1], prices[i]);
        }
        return ans;
    }
}