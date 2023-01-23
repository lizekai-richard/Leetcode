class Solution {
    private int n;
    private Integer[] diffs;
    
    private int binarySearch(int target) {
        int l = 0, r = n - 1;
        int index = -1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (diffs[mid] > target) {
                index = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return index;
    }
    
    public int threeSumSmaller(int[] nums, int target) {
        n = nums.length;
        if (n == 0) {
            return 0;
        }
        diffs = new Integer[n];
        for (int i = 0; i < n; ++i) {
            diffs[i] = target - nums[i];
        }
        Arrays.sort(diffs, Collections.reverseOrder());
        
        int count = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int x = nums[i] + nums[j];
                int index = binarySearch(x);
                if (index == -1) {
                    continue;
                }
                count += (index + 1);
                if (2 * nums[i] + nums[j] < target) {
                    count--;
                }
                if (nums[i] + 2 * nums[j] < target) {
                    count--;
                }
            }
        }
        count = (int) count / 3;
        return count;
    }
}