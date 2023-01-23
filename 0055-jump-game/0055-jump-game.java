class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return true;
        }
        int[] maxStep = new int[n];
        maxStep[0] = nums[0];
        if (maxStep[0] == 0) {
            return false;
        }
        for (int i = 1; i < n - 1; ++i) {
            maxStep[i] = Math.max(maxStep[i - 1] - 1, nums[i]);
            if (maxStep[i] == 0) {
                return false;
            }
        }
        if (maxStep[n - 2] >= 1) {
            return true;
        } else {
            return false;
        }
    }
}