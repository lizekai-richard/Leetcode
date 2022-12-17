class Solution {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<>(){
            @Override
            public int compare(int[] i1, int[] i2) {
                return i1[0] - i2[0];
            }
        });
        int numRooms = 1;
        PriorityQueue<Integer> rooms = new PriorityQueue<>();
        rooms.add(intervals[0][1]);
        
        for (int i = 1; i < intervals.length; ++i) {
            int earliestFreeRoom = rooms.peek();
            if (earliestFreeRoom <= intervals[i][0]) {
                rooms.poll();
            } else {
                numRooms += 1;
            }
            rooms.add(intervals[i][1]);
        }
        return numRooms;
    }
}