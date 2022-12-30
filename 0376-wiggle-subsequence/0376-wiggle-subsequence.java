class Solution {
    private int[][] wiggleSubsequence;
    
    public int wiggleMaxLength(int[] nums) {
        
        int n = nums.length;
        if (n == 1) {
            return 1;
        }
        
        wiggleSubsequence = new int[n][2];
        wiggleSubsequence[0][0] = 1;
        wiggleSubsequence[0][1] = 1;
        int ans = 1;
        
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[i] - nums[j] < 0) {
                    wiggleSubsequence[i][0] = Math.max(wiggleSubsequence[i][0], 
                                                  wiggleSubsequence[j][1] + 1);
                } else if (nums[i] - nums[j] > 0) {
                    wiggleSubsequence[i][1] = Math.max(wiggleSubsequence[i][1], 
                                                  wiggleSubsequence[j][0] + 1);
                }
            }
            ans = Math.max(ans, Math.max(wiggleSubsequence[i][0], 
                                        wiggleSubsequence[i][1]));
        }
        return ans;
    }
}