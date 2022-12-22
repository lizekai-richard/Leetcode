class KthLargestElementInAnArray {
    public void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
    
    // public int partition(int[] nums, int left, int right, int pIndex) {
    //     int pivot = nums[pIndex];
    //     swap(nums, left, pIndex);
    //     int low = left;
    //     int high = right;
    //     while (low < high) {
    //         while (nums[low] < pivot && low < high) low++;
    //         while (nums[high] > pivot && low < high) high--;
    //         if (low < high) {
    //             swap(nums, low, high);
    //         }
    //     }
    //     swap(nums, left, low);
    //     return low;
    // }
    public int partition(int[] nums, int left, int right, int pivot_index) {
    int pivot = nums[pivot_index];
    // 1. move pivot to end
    swap(nums, pivot_index, right);
    int store_index = left;

    // 2. move all smaller elements to the left
    for (int i = left; i <= right; i++) {
      if (nums[i] < pivot) {
        swap(nums, store_index, i);
        store_index++;
      }
    }

    // 3. move pivot to its final place
    swap(nums, store_index, right);

    return store_index;
  }
    
    public int findKthLargestHelper(int[] nums, int left, int right, int k) {
        Random rng = new Random();
        int randomPIndex = left + rng.nextInt(right - left + 1);
        int pIndex = partition(nums, left, right, randomPIndex);
        if (pIndex == k) {
            return nums[pIndex];
        } else if (pIndex > k) {
            return findKthLargestHelper(nums, left, pIndex - 1, k);
        } else {
            return findKthLargestHelper(nums, pIndex + 1, right, k);
        }
    }
    public int findKthLargest(int[] nums, int k) {
        return findKthLargestHelper(nums, 0, nums.length - 1, nums.length - k);
    }
}