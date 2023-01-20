class MPair<S extends Comparable<S>, T extends Comparable<T> > implements Comparable<MPair<S, T> > {
    S key;
    T value;
    public MPair(S key, T value) {
        this.key = key;
        this.value = value;
    }
    public S getKey() {
        return key;
    }
    public T getValue() {
        return value;
    }
    @Override
    public int compareTo(MPair<S, T> p) {
        return value.compareTo(p.getValue()) == 0 ? key.compareTo(p.getKey()) :
        value.compareTo(p.getValue());
    }
}

class Solution {
    public int[] getOrder(int[][] tasks) {
        Pair<Integer, int[]>[] tasksWithId = new Pair[tasks.length];
        for (int i = 0; i < tasks.length; ++i) {
            tasksWithId[i] = new Pair<Integer, int[]>(i, tasks[i]);
        }
        Arrays.sort(tasksWithId, (t1, t2) -> t1.getValue()[0] - t2.getValue()[0]
                == 0 ? t1.getValue()[1] - t2.getValue()[1] : t1.getValue()[0] - t2.getValue()[0]);
        
        
        PriorityQueue<MPair<Integer, Integer>> pq = new PriorityQueue<>();
        int[] order = new int[tasks.length];
        
        int j = 1;
        int time = tasksWithId[0].getValue()[0] + tasksWithId[0].getValue()[1];
        order[0] = tasksWithId[0].getKey();
        
        for (int i = 1; i < tasks.length; ++i) {
            while(j < tasks.length && tasksWithId[j].getValue()[0] <= time) {
                pq.add(new MPair<Integer, Integer>(tasksWithId[j].getKey(), tasksWithId[j].getValue()[1]));
                j++;
            }
            if (!pq.isEmpty()) {
                MPair<Integer, Integer> task = pq.poll();
                order[i] = task.getKey();
                time += task.getValue();
            } else {
                order[i] = tasksWithId[j].getKey();
                time = tasksWithId[j].getValue()[0] + tasksWithId[j].getValue()[1];
                j++;
            }
        }
        return order;
    }
}