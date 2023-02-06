class CustomStack {
    private int[] stack;
    private int head;
    private int tail;
    private int maxSize;

    public CustomStack(int maxSize) {
        this.maxSize = maxSize;
        head = 0;
        tail = 0;
        stack = new int[maxSize + 1];
    }
    
    public void push(int x) {
        if (tail - head >= maxSize) {
            return;
        }
        stack[tail] = x;
        tail++;
    }
    
    public int pop() {
        if (tail == 0) {
            return -1;
        }
        int x = stack[tail - 1];
        tail--;
        return x;  
    }
    
    public void increment(int k, int val) {
        for (int i = head; i < Math.min(k, tail); ++i) {
            stack[i] += val;
        }
    }
}

/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack obj = new CustomStack(maxSize);
 * obj.push(x);
 * int param_2 = obj.pop();
 * obj.increment(k,val);
 */