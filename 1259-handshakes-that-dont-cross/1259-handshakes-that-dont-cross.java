class Solution {
    public int numberOfWays(int numPeople) {
        long[] dp = new long[numPeople + 1];
        int mod = (int)1e9 + 7;
        dp[0] = 1;
        
        for (int i = 2; i <= numPeople; i+=2) {
            for (int j = 2; j <= i; j++) {
                if ((i - j) % 2 == 0 && (j - 2) % 2 == 0) {
                    dp[i] = (dp[i] + dp[j - 2] * dp[i - j]) % mod;
                }
            }
        }
        return (int)dp[numPeople];
    }
}