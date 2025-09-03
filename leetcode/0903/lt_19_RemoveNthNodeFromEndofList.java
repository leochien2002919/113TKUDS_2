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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Step 1: 創建一個虛擬頭節點，方便處理移除頭節點的情況。
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Step 2: 設置兩個指標，都從虛擬頭節點開始。
        ListNode fast = dummy;
        ListNode slow = dummy;

        // Step 3: 快指標先向前移動 n+1 步。
        // 這樣在迴圈開始時，fast 和 slow 之間就保持 n 個節點的距離。
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // Step 4: 快慢指標同時向前移動，直到快指標到達末尾。
        // 當 fast 變為 null 時，slow 正好位於要移除節點的前一個節點。
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Step 5: 執行移除操作。
        // 將 slow 的 next 指標直接指向要移除節點的下一個節點。
        // 例如，如果 slow 位於節點 A，而要移除節點 B，我們將 A.next = B.next。
        slow.next = slow.next.next;

        // Step 6: 返回虛擬頭節點的下一個節點，即為更新後的鏈結串列頭。
        return dummy.next;
    }
}