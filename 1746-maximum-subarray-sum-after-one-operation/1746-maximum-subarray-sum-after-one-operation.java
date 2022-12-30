class Solution {
    private int n;
    private int[] nums;
    private int[][] maxSum;
    
    public int maxSumAfterOperation(int[] nums) {
        this.n = nums.length;
        this.nums = nums;
        this.maxSum = new int[n + 1][2];
        
        int ans = -1;
        maxSum[0][0] = 0;
        maxSum[0][1] = 0;
        
        for (int i = 1; i <= n; ++i) {
            maxSum[i][0] = Math.max(maxSum[i - 1][0] + nums[i - 1], nums[i - 1]);
            maxSum[i][1] = Math.max(nums[i - 1] * nums[i - 1], Math.max(maxSum[i - 1][0] + nums[i - 1] * nums[i - 1], maxSum[i - 1][1] + nums[i - 1]));
            ans = Math.max(ans, maxSum[i][1]);
        }
        return ans;
    }
}