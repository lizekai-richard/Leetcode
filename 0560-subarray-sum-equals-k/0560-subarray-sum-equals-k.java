class Solution {

    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        prefixSum[0] = nums[0];
        for (int i = 1; i < n; ++i) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
        
        int count = 0;
        HashMap<Integer, Integer> table = new HashMap<>();
        table.put(0, 1);
        for (int i = 0; i < n; ++i) {
            int target = prefixSum[i] - k;
            if (table.containsKey(target)) {
                count += table.get(target);
            }
            table.put(prefixSum[i], table.getOrDefault(prefixSum[i], 0) + 1);
        }
        
        return count;
    }
}