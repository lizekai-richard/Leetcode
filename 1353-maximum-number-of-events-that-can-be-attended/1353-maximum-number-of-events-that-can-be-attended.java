class Solution {
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (e1, e2) -> e1[0] - e2[0] == 0 ? 
                         e1[1] - e2[1] : e1[0] - e2[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int count = 0;
        int i = 0;
        for (int d = 1; d <= 100000; ++d) {
            while (!pq.isEmpty() && pq.peek() < d) {
                pq.poll();
            }
            while (i < events.length && events[i][0] <= d) {
                pq.add(events[i][1]);
                i++;
            }
            if (!pq.isEmpty()) {
                pq.poll();
                count++;
            }
        }
        return count;
    }
}