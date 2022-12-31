class Solution {
    private double[][] probDP;
    public double probabilityOfHeads(double[] prob, int target) {
        int n = prob.length;
        int m = target;
        probDP = new double[n + 1][n + 1];
        
        // probDP[0][0] = 1 - prob[0];
        // probDP[0][1] = prob[0];
        Arrays.fill(probDP[0], 0);
        probDP[0][0] = 1;
        
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    probDP[i][j] = probDP[i - 1][j] * (1 - prob[i - 1]);
                } else {
                    probDP[i][j] = probDP[i - 1][j - 1] * prob[i - 1] + 
                        probDP[i - 1][j] * (1 - prob[i - 1]);
                }
            }
        }
        
        return probDP[n][m];
    }
}