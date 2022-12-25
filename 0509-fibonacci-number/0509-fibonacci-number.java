class Solution {
    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] fibNumbers = new int[n + 1];
        fibNumbers[0] = 0;
        fibNumbers[1] = 1;
        for (int i = 2; i <= n; ++i) {
            fibNumbers[i] = fibNumbers[i - 1] + fibNumbers[i - 2];
        }
        return fibNumbers[n];
    }
}