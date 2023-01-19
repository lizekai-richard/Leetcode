class Solution {
    public int consecutiveNumbersSum(int n) {
        int count = 0;
        int bound = (int) (Math.sqrt(2 * n + 1 / 4) - 1 / 2);
        for (int k = 1; k <= bound; ++k) {
            if ((2 * n - k * (k + 1)) % (2 * k) == 0) {
                count++;
            }
        }
        return count;
    }
}