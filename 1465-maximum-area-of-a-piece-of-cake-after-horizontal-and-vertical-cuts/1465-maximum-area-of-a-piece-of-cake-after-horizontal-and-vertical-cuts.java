class Solution {
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        int numH = horizontalCuts.length, numV = verticalCuts.length;
        
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        
        int maxHDiff = Math.max(verticalCuts[0], w - verticalCuts[numV - 1]);
        for (int i = 0; i < numV - 1; ++i) {
            maxHDiff = Math.max(maxHDiff, verticalCuts[i + 1] - verticalCuts[i]);
        }
        
        int maxVDiff = Math.max(horizontalCuts[0], h - horizontalCuts[numH - 1]);
        for (int i = 0; i < numH - 1; ++i) {
            maxVDiff = Math.max(maxVDiff, horizontalCuts[i + 1] - horizontalCuts[i]);
        }
        
        int mod = (int) (1e9 + 7);
        int ans = (int) (1l * maxHDiff * maxVDiff % mod);
        
        return ans;
    }
}