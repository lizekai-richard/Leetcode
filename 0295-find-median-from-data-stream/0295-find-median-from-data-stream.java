class MedianFinder {
    PriorityQueue<Integer> left;
    PriorityQueue<Integer> right;
    
    public MedianFinder() {
        left = new PriorityQueue<>(Collections.reverseOrder());
        right = new PriorityQueue<>();
    }
    
    public void balance() {
        if (left.size() > right.size()) {
            while (left.size() - right.size() > 1) {
                int t = left.poll();
                right.add(t);
            }
        } else {
            while (right.size() - left.size() > 1) {
                int t = right.poll();
                left.add(t);
            }
        }   
    }
    
    public void addNum(int num) {
        if (left.isEmpty() || num <= left.peek()) {
            left.add(num);
        } else {
            right.add(num);
        }
        
        balance();
    }
    
    public double findMedian() {
        int n = left.size();
        int m = right.size();
        if ((n + m) % 2 == 0) {
            return (double) (left.peek() + right.peek()) / 2;
        } else {
            if (n > m) {
                return left.peek();
            } else {
                return right.peek();
            }
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */