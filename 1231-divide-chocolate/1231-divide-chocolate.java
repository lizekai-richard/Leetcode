class Solution {
    private int[] sweetness;
    private int k;
    private int n;
    private int sum;
    private int minSweetness;
    private int ans;
    
    private void preprocessBoundary() {
        sum = 0;
        minSweetness = sweetness[0];
        for (int i = 0; i < n; ++i) {
            sum += sweetness[i];
            minSweetness = Math.min(minSweetness, sweetness[i]);
        }
    }

    private boolean check(int x) {
        int cuts = 0;
        int pointer = 0;
        int currentSum = 0;
        while (pointer < n) {
            if (cuts == k) {
                break;
            }
            if (currentSum < x) {
                currentSum += sweetness[pointer];
                pointer++;
            } else {
                cuts++;
                currentSum = 0;
            }
        }
        currentSum = 0;
        for (int i = pointer; i < n; ++i) {
            currentSum += sweetness[i];
        }
        return currentSum >= x;
    }

    private void binarySearch() {
        int l = minSweetness, r = sum / (k + 1);
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                l = mid + 1;
                ans = mid;
            } else {
                r = mid - 1;
            }
        }
    }

    public int maximizeSweetness(int[] sweetness, int k) {
        this.sweetness = sweetness;
        this.k = k;
        this.n = sweetness.length;

        preprocessBoundary();
        binarySearch();
        return ans;
    }
}