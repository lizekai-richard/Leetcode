class MaxSumOfRectangleNoLargerThanK {
    private int answer = Integer.MIN_VALUE;

    private void updateAnswer(int[] rowSum, int k) {
        int sum = 0;
        TreeSet<Integer> orderedSet = new TreeSet<>();
        orderedSet.add(0);
        for (int s: rowSum) {
            sum += s;
            Integer x = orderedSet.ceiling(sum - k);
            if (x != null) {
                answer = Math.max(answer, sum - x);
            }
            orderedSet.add(sum);
        }
    }

    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] rowSum = new int[n];

        for (int r = 0; r < m; ++r) {
            Arrays.fill(rowSum, 0);
            for (int row = r; row < m; ++row) {
                for (int col = 0; col < n; ++col) {
                    rowSum[col] = rowSum[col] + matrix[row][col];
                }
                updateAnswer(rowSum, k);
            }
        }
        return answer;
    }
}