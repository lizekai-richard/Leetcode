class DLLNode {
    public DLLNode prev;
    public DLLNode next;
    public int key;
    public int value;

    public DLLNode(int key, int value) {
        this.key = key;
        this.value = value;
        this.prev = null;
        this.next = null;
    }

    public void setPrev(DLLNode prev) {
        this.prev = prev;
    }

    public void setNext(DLLNode next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DLLNode)) {
            return false;
        }
        DLLNode node = (DLLNode) o;
        return node.key == this.key && node.value == this.value;
    }
}

public class LRUCache {

    private int capacity;
    private HashMap<Integer, DLLNode> cache;
    private int numOfNode;
    private DLLNode head;
    private DLLNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<Integer, DLLNode>();
        numOfNode = 0;
        head = new DLLNode(-1, -1);
        tail = new DLLNode(-1, -1);
        head.setNext(tail);
        tail.setPrev(head);
    }

    public void updateDLL(DLLNode node) {
        if (node.equals(head)) {
            if (numOfNode > 1) {
                head = head.next;
            }
        } else {
            DLLNode prevNode = node.prev;
            DLLNode nextNode = node.next;
            prevNode.setNext(nextNode);
            nextNode.setPrev(prevNode);
        }

        DLLNode lastNode = tail.prev;
        lastNode.setNext(node);
        node.setPrev(lastNode);
        node.setNext(tail);
        tail.setPrev(node);
    }

    public void updateCache(int key, DLLNode node) {
        cache.remove(key);
        cache.put(key, node);
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        DLLNode node = cache.get(key);
        updateDLL(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLLNode node;
        if (!cache.containsKey(key)) {
            if (head.key == -1) {
                head.key = key;
                head.value = value;
                node = head;
            } else {
                node = new DLLNode(key, value);
                DLLNode lastNode = tail.prev;

                lastNode.setNext(node);
                node.setPrev(lastNode);
                node.setNext(tail);
                tail.setPrev(node);
            }

            cache.put(key, node);
            numOfNode++;
            if (numOfNode > capacity) {
                cache.remove(head.key);
                head = head.next;
                numOfNode--;
            }
        } else {
            node = cache.get(key);
            node.value = value;
            updateDLL(node);
            updateCache(key, node);
        }
    }

    // public static void main(String[] args) {
    //     LRUCache cache = new LRUCache(1);
    //     cache.put(2, 1);
    //     System.out.println(cache.get(2));
    //     cache.put(3, 2);
    //     System.out.println(cache.get(2));
    //     System.out.println(cache.get(3));
    // }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */