class Solution {
    private int n = 0;
    private int[] nums;
    private int[] keys;
    private HashMap<Integer, Integer> count;
    
    public void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    
    public void countFrequency() {
        count = new HashMap<>();
        for (int x: nums) {
            if (count.containsKey(x)) {
                count.put(x, count.get(x) + 1);
            } else {
                count.put(x, 1);
                n += 1;
            }
        }
    }
    
    public void getKeyArray() {
        keys = new int[n];
        int ptr = 0;
        for (int key: count.keySet()) {
            keys[ptr] = key;
            ptr++;
        }
    }
    
    public int partition(int left, int right, int pIndex) {
        int pivot = count.get(keys[pIndex]);
        swap(keys, pIndex, right);
        int storeIndex = left;
        
        for (int i = left; i <= right; ++i) {
            if (count.get(keys[i]) < pivot) {
                swap(keys, storeIndex, i);
                storeIndex++;
            }
        }
        swap(keys, storeIndex, right);
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
    
    public int[] topKFrequent(int[] nums, int k) {
        this.nums = nums;
        this.countFrequency();
        this.getKeyArray();
        this.quickSelect(0, n - 1, n - k);
        return Arrays.copyOfRange(keys, n - k, n);
    }
}