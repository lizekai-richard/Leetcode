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
    
    public ListNode add(int carry, ListNode l1, ListNode l2) {
        ListNode node = new ListNode();
        if (l1 == null && l2 == null) {
            if (carry == 0) {
                return null;
            } else {
                node.val = carry;
                node.next = add(0, null, null);
                return node;
            }
        }
        
        if (l1 == null) {
            int newCarry, digit;
            newCarry = (l2.val + carry) / 10;
            digit = (l2.val + carry) % 10;
            node.val = digit;
            node.next = add(newCarry, null, l2.next);
        } else if (l2 == null) {
            int newCarry, digit;
            newCarry = (l1.val + carry) / 10;
            digit = (l1.val + carry) % 10;
            node.val = digit;
            node.next = add(newCarry, l1.next, null);
        } else {
            int newCarry, digit;
            newCarry = (l1.val + l2.val + carry) / 10;
            digit = (l1.val+ l2.val + carry) % 10;
            node.val = digit;
            node.next = add(newCarry, l1.next, l2.next);
        }
        return node;
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return add(0, l1, l2);
    }
}