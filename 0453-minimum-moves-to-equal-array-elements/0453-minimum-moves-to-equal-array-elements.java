class Solution {
    public int minMoves(int[] nums) {
        int n = nums.length;
        int minNum = nums[0];
        int sum = 0;
        for (int i = 0; i < nums.length; ++i) {
            minNum = Math.min(minNum, nums[i]);
            sum += nums[i];
        }
        return sum - n * minNum;
    }
}