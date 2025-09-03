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
    public ListNode reverseKGroup(ListNode head, int k) {
        // Step 1: 創建虛擬頭節點，簡化處理鏈結串列開頭的邏輯。
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;

        while (true) {
            // Step 2: 找到當前 k 個節點的組的結尾。
            ListNode end = pre;
            for (int i = 0; i < k; i++) {
                end = end.next;
                // 如果剩餘節點不足 k 個，終止迴圈，因為最後的節點不需要反轉。
                if (end == null) {
                    return dummy.next;
                }
            }

            // 定義需要反轉的組的頭和尾。
            ListNode start = pre.next;
            ListNode nextGroupHead = end.next;

            // Step 3: 反轉 start 到 end 之間的 k 個節點。
            ListNode prev = null;
            ListNode curr = start;
            while (curr != nextGroupHead) {
                ListNode nextTemp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextTemp;
            }

            // Step 4: 重新連接反轉後的鏈結串列。
            // 1. 將前一個組的尾部（pre）連接到新反轉組的頭部（end）。
            pre.next = end;
            // 2. 將新反轉組的尾部（start）連接到下一組的頭部。
            start.next = nextGroupHead;

            // Step 5: 更新 pre 指標，使其指向新反轉組的尾部，為下一輪迴圈做準備。
            pre = start;
        }
    }
}