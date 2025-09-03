import java.util.PriorityQueue;
import java.util.Comparator;

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
    public ListNode mergeKLists(ListNode[] lists) {
        // Step 1: 處理邊界情況，如果輸入的鏈結串列陣列為空，直接返回空。
        if (lists == null || lists.length == 0) {
            return null;
        }

        // 創建一個最小堆積（Priority Queue），用於存放鏈結串列的頭節點。
        // 使用一個自定義的比較器，使其根據節點的 val 進行排序。
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));

        // Step 2: 遍歷所有鏈結串列，將每個非空的頭節點加入最小堆積。
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.add(list);
            }
        }

        // 創建一個虛擬頭節點，方便構建新的合併鏈結串列。
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // Step 3: 當堆積不為空時，持續取出最小節點並構建新鏈結串列。
        while (!minHeap.isEmpty()) {
            // 從堆積中取出值最小的節點。
            ListNode smallest = minHeap.poll();

            // 將這個最小節點接到新鏈結串列的末尾。
            current.next = smallest;
            
            // 移動 current 指標。
            current = current.next;

            // 如果取出的節點還有下一個節點，將其加入堆積中，以便下一輪比較。
            if (smallest.next != null) {
                minHeap.add(smallest.next);
            }
        }

        // Step 4: 返回虛擬頭節點的下一個節點，即為最終合併好的鏈結串列的頭。
        return dummy.next;
    }
}