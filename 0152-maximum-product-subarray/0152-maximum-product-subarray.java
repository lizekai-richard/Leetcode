class Solution {
    private int[] maxProd;
    public int maxProduct(int[] nums) {
         int n = nums.length;
        int currentNumber = nums[0];
        int maxSoFar = nums[0];
        int minSoFar = nums[0];
        int ans = nums[0];
        
        for (int i = 1; i < n; ++i) {
            int v1 = nums[i];
            int v2 = maxSoFar * nums[i];
            int v3 = minSoFar * nums[i];
            maxSoFar = Math.max(v1, Math.max(v2, v3));
            minSoFar = Math.min(v1, Math.min(v2, v3));
            ans = Math.max(ans, maxSoFar);
        }
        
        return ans;
        
//         int ans = nums[0];
        
//         int product = 1;
//         for (int i = 0; i < n; ++i) {
//             product *= nums[i];
//             ans = Math.max(ans, product);
//             if (product == 0) {
//                 product = 1;
//             }
//         }
        
//         product = 1;
//         for (int i = n - 1; i >= 0; --i) {
//             product *= nums[i];
//             ans = Math.max(ans, product);
//             if (product == 0) {
//                 product = 1;
//             }
//         }
        
//         return ans;
    }
}