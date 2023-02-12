class Solution {
    private int n;
    private int[] nums;
    
    private boolean twoPointerCheck(int sum) {
        int p = 0, q = n - 1;
        while (p < q) {
            if (nums[p] + nums[q] > sum) {
                q--;
            } else if (nums[p] + nums[q] == sum) {
                return true;
            } else {
                p++;
            }
        }
        return false;
    }
    
    public int twoSumLessThanK(int[] nums, int k) {
        this.nums = nums;
        this.n = nums.length;
        Arrays.sort(this.nums);
        
        int sum = -1;
        for (int i = 0; i < n - 1; ++i) {
            int a = nums[i];
            int l = i + 1, r = n - 1;
            int target = -1;
            while (l <= r) {
                int mid = (l + r) >> 1;
                if (nums[mid] < k - a) {
                    l = mid + 1;
                    target = mid;
                } else {
                    r = mid - 1;
                }
            }
            if (target != -1) {
                sum = Math.max(sum, a + nums[target]);
            }
        }
        return sum;
    }
}