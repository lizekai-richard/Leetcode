class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        
        int n = nums.length;
        int[] moneyRobbed = new int[n];
        int ans = 0;
        
        Arrays.fill(moneyRobbed, 0);
        moneyRobbed[0] = nums[0];
        moneyRobbed[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; ++i) {
            // for (int j = 0; j < i; ++j) {
            //     if (i - j > 1) {
            //         moneyRobbed[i] = Math.max(moneyRobbed[i], moneyRobbed[j] 
            //                                   + nums[i]);
            //     }
            // }
            moneyRobbed[i] = Math.max(moneyRobbed[i - 1], moneyRobbed[i - 2] + nums[i]);
        }
        return moneyRobbed[n - 1];
    }
}