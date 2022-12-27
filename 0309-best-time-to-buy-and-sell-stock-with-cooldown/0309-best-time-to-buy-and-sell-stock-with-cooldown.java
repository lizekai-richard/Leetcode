class Solution {
    private int[] sold;
    private int[] held;
    private int[] reset;
    
    public int maxProfit(int[] prices) {
        int n = prices.length;
        
        sold = new int[n + 1];
        held = new int[n + 1];
        reset = new int[n + 1];
        
        sold[0] = Integer.MIN_VALUE;
        held[0] = Integer.MIN_VALUE;
        reset[0] = 0;
        
        for (int i = 1; i <= n; ++i) {
            sold[i] = held[i - 1] + prices[i - 1];
            held[i] = Math.max(held[i - 1], reset[i - 1] - prices[i - 1]);
            reset[i] = Math.max(reset[i - 1], sold[i - 1]);
        }
        
        return Math.max(sold[n], Math.max(held[n], reset[n]));
    }
}