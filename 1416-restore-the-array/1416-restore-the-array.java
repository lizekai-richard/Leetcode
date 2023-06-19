class Solution {
    public int numberOfArrays(String s, int k) {
        int mod = (int) 1e9 + 7;
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '0') continue;

            for (int j = i; j < n; ++j) {
                String sub_s = s.substring(i, j + 1);
                if (Long.parseLong(sub_s) > k) {
                    break;
                }
                dp[j + 1] = (dp[j + 1] + dp[i]) % mod;
            }
        }
        return dp[n];
    }
}