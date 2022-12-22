class MaximumNumberOfPointsWithCost {

    public long maxPoints(int[][] points) {
        int n = points.length;
        int m = points[0].length;
        long ans = Long.MIN_VALUE;
        long[][] dp = new long[n][m];
        long[] forwardMax = new long[m];
        long[] backwardMax = new long[m];
        for (int j = 0; j < m; ++j) {
            dp[0][j] = points[0][j];
        }
        for (int i = 1; i < n; ++i) {
            forwardMax[0] = dp[i - 1][0];
            for (int j = 1; j < m; ++j) {
                forwardMax[j] = Math.max(forwardMax[j - 1], dp[i - 1][j] + j);
            }
            
            backwardMax[m - 1] = dp[i - 1][m - 1] - (m - 1);
            for (int j = m - 2; j >= 0; --j) {
                backwardMax[j] = Math.max(backwardMax[j + 1], dp[i - 1][j] - j);
            }
            
            for (int j = 0; j < m; ++j) {
                dp[i][j] = Math.max(forwardMax[j] - j, backwardMax[j] + j) + points[i][j];
            }
        }
        for (int j = 0; j < m; ++j) {
            ans = Math.max(ans, dp[n - 1][j]);
        }
        return ans;
    }
}