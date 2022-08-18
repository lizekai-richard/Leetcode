class Solution {
    public int findPivot(int[] nums) {
        int l = 0, r = nums.length - 1;
        if (nums[l] <= nums[r]) {
            return 0;
        }
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1]) {
                return mid + 1;
            } else if (nums[mid] < nums[l]) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }
    public int findAnswer(int[] nums, int beginIndex, int endIndex, int target) {
        int l = beginIndex, r = endIndex;
        while(l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if(nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }
    public int search(int[] nums, int target) {
        
        int pivot = findPivot(nums);
        System.out.println(pivot);
        if (pivot == 0) {
            return findAnswer(nums, 0, nums.length - 1, target);
        } else if (target >= nums[0]) {
            return findAnswer(nums, 0, pivot - 1, target);
        } else {
            return findAnswer(nums, pivot, nums.length - 1, target);
        }
        
    }
}