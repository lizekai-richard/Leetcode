class Solution {
    public int[] prefixSum;
    public int[] nums;
    public int n;
    public int target;
    
    public boolean check(int x) {
        for (int i = 0; i < n - x + 1; ++i) {
            if (prefixSum[i + x] - prefixSum[i] >= target) {
                return true;
            }
        }
        return false;
    }
    public int minSubArrayLen(int target, int[] nums) {
        this.n = nums.length;
        this.target = target;
        this.nums = nums;
        this.prefixSum = new int[this.n + 1];
        
        prefixSum[0] = 0;
        for (int i = 1; i <= n; ++i) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        
        int l = 0, r = n + 1;
        int ans = 0;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid - 1;
                ans = mid;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
}