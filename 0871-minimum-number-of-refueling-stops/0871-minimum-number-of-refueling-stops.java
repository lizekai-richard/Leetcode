class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int numStations = 0;
        int prevLocation = 0;
        int tank = startFuel;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int[] station: stations) {
            int curLocation = station[0];
            int fuel = station[1];
            tank = tank - (curLocation - prevLocation);
            while (!pq.isEmpty() && tank < 0) {
                tank += pq.poll();
                numStations++;
            }
            if (tank < 0) {
                return -1;
            }
            prevLocation = curLocation;
            pq.add(fuel);
        }
        
        tank = tank - (target - prevLocation);
        while (!pq.isEmpty() && tank < 0) {
            tank += pq.poll();
            numStations++;
        }
        if (tank < 0) {
            return -1;
        }
        
        return numStations;
    }
}