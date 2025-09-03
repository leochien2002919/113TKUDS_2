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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Step 1: 創建一個虛擬頭節點，簡化邏輯。
        // 這避免了在處理第一個節點時需要進行特殊判斷。
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // Step 2: 遍歷兩個鏈結串列，直到其中一個為空。
        while (list1 != null && list2 != null) {
            // 比較 list1 和 list2 當前節點的值。
            if (list1.val <= list2.val) {
                // 如果 list1 的值較小或相等，將 list1 的節點接到新列表。
                current.next = list1;
                // 移動 list1 指標到下一個節點。
                list1 = list1.next;
            } else {
                // 否則，將 list2 的節點接到新列表。
                current.next = list2;
                // 移動 list2 指標到下一個節點。
                list2 = list2.next;
            }
            // 移動新列表的 current 指標到剛加入的節點。
            current = current.next;
        }

        // Step 3: 處理剩下未遍歷完的節點。
        // 如果 list1 還有剩餘，直接將其全部接到新列表的末端。
        if (list1 != null) {
            current.next = list1;
        } 
        // 否則，如果 list2 還有剩餘，也將其全部接到新列表的末端。
        else if (list2 != null) {
            current.next = list2;
        }

        // Step 4: 返回新列表的頭節點，即虛擬節點的下一個。
        return dummy.next;
    }
}