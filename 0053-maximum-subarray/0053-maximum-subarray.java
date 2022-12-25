class Solution {
    
    public int maxSubArray(int[] nums) {
        // define subArraySum[i] as the maximum subarray sum ending at i
        int[] subArraySum = new int[nums.length];
        int maxSum = nums[0];
        
        subArraySum[0] = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            if (subArraySum[i - 1] <= 0) {
                subArraySum[i] = nums[i];
            } else {
                subArraySum[i] = subArraySum[i - 1] + nums[i];
            }
            maxSum = Math.max(maxSum, subArraySum[i]);
        }
        return maxSum;
    }
}