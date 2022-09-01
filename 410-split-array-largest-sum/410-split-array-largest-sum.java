class Solution {
    public boolean check(int sum, int m, int[] nums) {
        int numIntervals = 1;
        int pointer = 0;
        int currentSum = 0;
        while (pointer < nums.length) {
            if (nums[pointer] > sum) {
                return false;
            }
            if (currentSum + nums[pointer] <= sum) {
                currentSum += nums[pointer];
            } else {
                currentSum = nums[pointer];
                numIntervals += 1;
            }
            pointer++;
        }
        return numIntervals <= m;
    }
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int totalSum = 0;
        for (int i = 0; i < n; ++i) {
            totalSum += nums[i];
        }
        
        int l = 0, r = totalSum, ans = -1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (check(mid, m, nums)) {
                r = mid - 1;
                ans = mid;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
}