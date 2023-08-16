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
    public ListNode sortLinkedList(ListNode head) {
        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();
        
        ListNode iter = head;
        while (iter != null) {
            if (iter.val >= 0) {
                positive.add(iter.val);
            } else {
                negative.add(iter.val);
            }
            iter = iter.next;
        }
        
        ListNode next = new ListNode(-1);
        ListNode ans = next;
        Collections.reverse(negative);
        for (int i: negative) {
            next.next = new ListNode(i);
            next = next.next;
        }
        for (int i: positive) {
            next.next = new ListNode(i);
            next = next.next;
        }
        return ans.next;
    }
}