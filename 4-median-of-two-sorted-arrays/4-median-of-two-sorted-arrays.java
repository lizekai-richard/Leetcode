class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        if(n > m) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int midInMergedArray = (n + m + 1) / 2;
        int l = 0, r = n;
        while(l <= r) {
            int leftASize = (l + r) / 2;
            int leftBSize = midInMergedArray - leftASize;
            int LA = leftASize <= 0 ? Integer.MIN_VALUE : nums1[leftASize - 1];
            int LB = leftBSize <= 0 ? Integer.MIN_VALUE : nums2[leftBSize - 1];
            int RA = leftASize >= n ? Integer.MAX_VALUE : nums1[leftASize];
            int RB = leftBSize >= m ? Integer.MAX_VALUE : nums2[leftBSize];
            
            if(LA <= RB && LB <= RA) {
                if((m + n) % 2 == 1) {
                    return Math.max(LA, LB);
                } else {
                    return (Math.max(LA, LB) + Math.min(RA, RB)) / 2.0;
                }
            } else if (LA > RB) {
                r = leftASize - 1;
            } else {
                l = leftASize + 1;
            }
        }
        return 0.0;
    }
}