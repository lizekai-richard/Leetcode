class Solution {
    private List<List<Integer>> twoSum(int[] nums, int start, int target) {
        int n = nums.length;
        int l = start, r = n - 1;
        List<List<Integer>> res = new ArrayList<>();
        while (l < r) {
            if (nums[l] + nums[r] < target) {
                l++;
            } else if (nums[l] + nums[r] == target) {
                List<Integer> pair = new ArrayList<>();
                pair.add(nums[l]);
                pair.add(nums[r]);
                res.add(pair);
                l++;
                r--;
            } else {
                r--;
            }
        }
        return res;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        List<List<Integer>> tempAns = new ArrayList<>();
        for (int i = 0; i < n - 1 && nums[i] <= 0; ++i) {
            if (i != 0 && nums[i - 1] == nums[i]) continue;
            int target = -nums[i];
            List<Integer> triplet;
            for (List pair: twoSum(nums, i + 1, target)) {
                triplet = new ArrayList<>();
                triplet.add(-target);
                triplet.addAll(pair);
                tempAns.add(triplet);
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        HashMap<List<Integer>, Boolean> existed = new HashMap<>();
        for (List triplet: tempAns) {
            if (!existed.containsKey(triplet)) {
                existed.put(triplet, true);
                ans.add(triplet);
            }
        }
        return ans;
    }
}