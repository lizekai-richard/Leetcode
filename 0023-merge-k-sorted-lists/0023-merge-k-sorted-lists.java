/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode[] lists;
    
    public ListNode buildNode(List<Integer> values) {
        int l = values.size();
        if (l == 1) {
            return new ListNode(values.get(0), null);
        }
        ListNode nextNode = buildNode(values.subList(1, l));
        return new ListNode(values.get(0), nextNode);
    }
    
    public ListNode merge(ListNode leftNode, ListNode rightNode) {
        
        ListNode iter = new ListNode(0);
        ListNode mergedNode = iter;
        while (leftNode != null && rightNode != null) {
            int leftVal = leftNode.val;
            int rightVal = rightNode.val;
            if (leftVal <= rightVal) {
                iter.next = leftNode;
                leftNode = leftNode.next;
                iter = iter.next;
            } else {
                iter.next = rightNode;
                rightNode = rightNode.next;
                iter = iter.next;
            }
        }
        if (leftNode != null) {
            iter.next = leftNode;
        }
        if (rightNode != null) {
            iter.next = rightNode;
        }
        
        return mergedNode.next;
    }
    
    public ListNode divide_and_conquer(int l, int r) {
        if (l == r) {
            return lists[l];
        }
        int mid = (l + r) >> 1;
        ListNode leftNode = divide_and_conquer(l, mid);
        ListNode rightNode = divide_and_conquer(mid + 1, r);
        return merge(leftNode, rightNode);
        
    }
    public ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;
        if (n == 0) {
            return null;
        }
        
        int interval = 1;
        while (interval < n) {
            for (int i = 0; i + interval < n; i += 2 * interval) {
                lists[i] = merge(lists[i], lists[i + interval]);
            }
            interval *= 2;
        }
        return lists[0];
    }
}