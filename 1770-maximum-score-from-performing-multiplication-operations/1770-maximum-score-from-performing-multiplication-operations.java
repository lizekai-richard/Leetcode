class Solution {
    public int maximumScore(int[] nums, int[] multipliers) {
        int n = nums.length;
        int m = multipliers.length;
        
        int[][] dp = new int[m + 1][m + 1];
        int ans = -100000000;
        for(int i = m - 1; i >= 0; --i) {
            for (int l = i; l >= 0; --l) {
                int r = n - i + l - 1;
                dp[i][l] = Math.max(dp[i + 1][l + 1] + nums[l] * multipliers[i], dp[i + 1][l] + nums[r] * multipliers[i]);
                ans = Math.max(ans, dp[i][l]);
            }
        }
        return dp[0][0];
    }
}