class Solution {
    private static int MOD = (int)1e9 + 7;
    private int n;
    private int m;
    private int k;
    private String[] pizza;
    private int[][][] dp;

    private boolean hasApple(int r1, int c1, int r2, int c2) {
        for (int i = r1; i <= r2; ++i) {
            String s = pizza[i];
            for (int j = c1; j <= c2; ++j) {
                if (s.charAt(j) == 'A') {
                    return true;
                }
            }
        }
        return false;
    }

    private int memo_search(int r, int c, int cnt) {
        if (dp[r][c][cnt] != 0) {
            return dp[r][c][cnt] % MOD;
        }
        if (cnt == k - 1) {
            return 1;
        }
        int horizontal_cnt = 0;
        int vertical_cnt = 0;
        for (int i = r; i < n - 1; ++i) {
            if (hasApple(r, c, i, m - 1) && hasApple(i + 1, c, n - 1, m - 1)) {
                horizontal_cnt = (horizontal_cnt + memo_search(i + 1, c, cnt + 1)) % MOD;
            }
        }
        for (int j = c; j < m - 1; ++j) {
            if (hasApple(r, c, n - 1, j) && hasApple(r, j + 1, n - 1, m - 1)) {
                vertical_cnt = (vertical_cnt + memo_search(r, j + 1, cnt + 1)) % MOD;
            } 
        }
        dp[r][c][cnt] = (horizontal_cnt + vertical_cnt) % MOD;
        return dp[r][c][cnt];
    }

    public int ways(String[] pizza, int k) {
        this.n = pizza.length;
        this.m = pizza[0].length();
        this.k = k;
        this.dp = new int[n][m][k];
        this.pizza = pizza;

        int ans = memo_search(0, 0, 0);
        return ans;
    }
}