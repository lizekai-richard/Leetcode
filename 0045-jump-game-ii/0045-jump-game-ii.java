class Solution {
    public int jump(int[] nums) {
        // dp[i] dp[j], i < j
        // dp[j] = min(dp[j], dp[i] + 1) if j - i <= nums[i]
        // dp[i] [i, i + nums[i]]
        
        int n = nums.length;
        int[] dp = new int[n];
        
        for (int i = 0; i < n; ++i) dp[i] = Integer.MAX_VALUE;
        
        dp[0] = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 1; j <= nums[i] && i + j < n; ++j) {
                dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
            }
        }
        return dp[n - 1];
    }
}