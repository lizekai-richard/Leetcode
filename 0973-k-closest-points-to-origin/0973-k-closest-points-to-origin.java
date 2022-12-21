class Solution {
    private int[][] points;
    private HashMap<int[], Double> distance;
    
    public void swap(int[][] array, int a, int b) {
        int[] temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
    
    public void computeDistances() {
        distance = new HashMap<>();
        for (int[] point: points) {
            double d = Math.sqrt(point[0] * point[0] + point[1] * point[1]);
            distance.put(point, d);
        }
    }
    
    public int partition(int left, int right, int pIndex) {
        double pivotDistance = distance.get(points[pIndex]);
        swap(points, pIndex, right);
        int storeIndex = left;
        
        for (int i = left; i <= right; ++i) {
            if (distance.get(points[i]) < pivotDistance) {
                swap(points, i, storeIndex);
                storeIndex++;
            }
        }
        
        swap(points, storeIndex, right);
        return storeIndex;
    }
    
    public void quickSelect(int left, int right, int k) {
        if (left == right) return;
        
        Random rng = new Random();
        int pIndex = left + rng.nextInt(right - left);
        int p = partition(left, right, pIndex);
        
        if (p == k) {
            return;
        } else if (p < k) {
            quickSelect(p + 1, right, k);
        } else {
            quickSelect(left, p - 1, k);
        }
    }
    
    public int[][] kClosest(int[][] points, int k) {
        this.points = points;
        this.computeDistances();
        this.quickSelect(0, points.length - 1, k);
        
        int[][] answerPoints = new int[k][2];
        for (int i = 0; i < k; ++i) {
            answerPoints[i] = points[i];
        }
        return answerPoints;
    }
}