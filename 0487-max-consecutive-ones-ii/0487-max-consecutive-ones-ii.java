class Solution {
    private int[] usedFlip;
    private int[] notUseFlip;
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        int ans = 0;
        usedFlip = new int[n + 1];
        notUseFlip = new int[n + 1];
        
        usedFlip[0] = 0;
        notUseFlip[0] = 0;
        for (int i = 1; i <= n; ++i) {
            if (nums[i - 1] == 0) {
                usedFlip[i] = notUseFlip[i - 1] + 1;
                notUseFlip[i] = 0;
            } else {
                usedFlip[i] = usedFlip[i - 1] + 1;
                notUseFlip[i] = notUseFlip[i - 1] + 1;
            }
            
            ans = Math.max(ans, usedFlip[i]);
        }
        return ans;
    }
}