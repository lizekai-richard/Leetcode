class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        int n = coins.length;
        Arrays.sort(coins);
        
        if (amount == 0) {
            return 0;
        }
        if (coins[0] > amount) {
            return -1;
        }
        
        Arrays.fill(dp, 1000000);
        dp[0] = 0;
        for (int i = coins[0]; i <= amount; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i - coins[j] >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        if (dp[amount] >= 1000000) {
            return -1;
        }
        return dp[amount];
    }
}