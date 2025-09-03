/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        // Create a dummy node to simplify edge cases, like swapping the original head.
        // The dummy node points to the head of the list.
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;

        // The loop continues as long as there's a pair to swap.
        // A pair exists if current.next and current.next.next are not null.
        while (current.next != null && current.next.next != null) {
            // Identify the two nodes to be swapped.
            ListNode firstNode = current.next;
            ListNode secondNode = current.next.next;

            // Perform the swap by modifying the pointers.
            
            // 1. Link the first node to the node after the pair.
            firstNode.next = secondNode.next;
            
            // 2. Point the second node back to the first node.
            secondNode.next = firstNode;
            
            // 3. Link the previous part of the list to the new head of the swapped pair.
            current.next = secondNode;

            // Move the current pointer forward by two nodes to the next pair.
            current = firstNode;
        }

        // The new head of the list is the node after the dummy node.
        return dummy.next;
    }
}