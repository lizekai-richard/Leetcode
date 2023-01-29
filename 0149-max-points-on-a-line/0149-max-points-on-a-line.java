class Solution {
    
    private double computeSlope(int[] p1, int[] p2) {
        int x1 = p1[0], y1 = p1[1];
        int x2 = p2[0], y2 = p2[1];
        if (x1 == x2) {
            return 1e5;
        }
        // IMPORTANT: must cast to double, unless will be zero if smaller than 1
        return (double)(y2 - y1) / (x2 - x1);
    }
    
    public int maxPoints(int[][] points) {
        int n = points.length;
        List<HashMap<Double, List<int[]> > > pointMaps = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            HashMap<Double, List<int[]> > map = new HashMap<>();
            pointMaps.add(map);
        }
        
        for (int i = 0; i < n; ++i) {
            int[] p1 = points[i];
            HashMap<Double, List<int[]> > mapI = pointMaps.get(i);
            for (int j = 0; j < n; ++j) {
                if (i == j) continue;
                int[] p2 = points[j];
                double slope = computeSlope(p1, p2);
                if (!mapI.containsKey(slope)) {
                    mapI.put(slope, new ArrayList<>());
                }
                mapI.get(slope).add(p2);
            }
        }
        
        int maxNumber = 0;
        for (int i = 0; i < n; ++i) {
            HashMap<Double, List<int[]> > mapI = pointMaps.get(i);
            for (double key: mapI.keySet()) {
                maxNumber = Math.max(maxNumber, mapI.get(key).size());
            }
        }
        return maxNumber + 1;
    }
}