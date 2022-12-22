class MergeIntervals {
    
    public int[][] merge(int[][] intervals) {
        List<int[]> mergedIntervals = new ArrayList<>();
        
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] i1, int[] i2) {
                return i1[1] - i2[1];
            }
        });
        
        mergedIntervals.add(intervals[intervals.length - 1]);
        for (int i = intervals.length - 2; i >= 0; --i) {
            // don't merge
            if (intervals[i][1] < mergedIntervals.get(mergedIntervals.size() - 1)[0]) {
                mergedIntervals.add(intervals[i]);
            } else {
                int[] newInterval = new int[2];
                newInterval[0] = Math.min(mergedIntervals.get(mergedIntervals.size() - 1)[0], intervals[i][0]);
                newInterval[1] = mergedIntervals.get(mergedIntervals.size() - 1)[1];
                mergedIntervals.set(mergedIntervals.size() - 1, newInterval);
            }
        }
        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }
}