class Solution {
    private int compareSlot(int[] slot1, int[] slot2) {
        if (slot1[1] <= slot2[0]) {
            return 0;
        } 
        if (slot2[1] <= slot1[0]) {
            return 1;
        }
        return 2;
    }
    
    private int[] getIntersection(int[] slot1, int[] slot2) {
        if (slot1[0] <= slot2[0] && slot2[1] <= slot1[1]) {
            return slot2;
        }
        if (slot2[0] <= slot1[0] && slot1[1] <= slot2[1]) {
            return slot1;
        }
        if (slot1[1] >= slot2[1]) {
            return new int[]{slot1[0], slot2[1]};
        }
        if (slot2[1] >= slot1[1]) {
            return new int[]{slot2[0], slot1[1]};
        }
        return new int[]{};
    }
    
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        int n = slots1.length;
        int m = slots2.length;
        
        Arrays.sort(slots1, new Comparator<>(){
            @Override
            public int compare(int[] x, int[] y) {
                return x[0] - y[0];
            }
        });
        Arrays.sort(slots2, new Comparator<>(){
            @Override
            public int compare(int[] x, int[] y) {
                return x[0] - y[0];
            }
        });
        
        int p1 = 0, p2 = 0;
        while (p1 < n && p2 < m) {
            int res = compareSlot(slots1[p1], slots2[p2]);
            if (res == 2) {
                //int[] intersection = getIntersection(slots1[p1], slots2[p2]);
                int[] intersection = new int[]{Math.max(slots1[p1][0], slots2[p2][0]),
                                              Math.min(slots1[p1][1], slots2[p2][1])};
                if (intersection[1] - intersection[0] >= duration) {
                    return Arrays.asList(new Integer[]{intersection[0], intersection[0] + duration});
                } else {
                    if (slots1[p1][1] > slots2[p2][1]) {
                        p2++;
                    } else {
                        p1++;
                    }
                }
            } else if (res == 1) {
                p2++;
            } else if (res == 0) {
                p1++;
            }
        }
        return new ArrayList<Integer>();
    }
}