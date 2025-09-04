import java.util.*;

public class LC23_MergeKLists_Hospitals {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        sc.nextLine();

        ListNode[] lists = new ListNode[k];

        for (int i = 0; i < k; i++) {
            String[] line = sc.nextLine().trim().split(" ");
            ListNode dummy = new ListNode(0), curr = dummy;
            for (String s : line) {
                int val = Integer.parseInt(s);
                if (val == -1) break;
                curr.next = new ListNode(val);
                curr = curr.next;
            }
            lists[i] = dummy.next;
        }
        sc.close();

        ListNode merged = mergeKLists(lists);

        ListNode curr = merged;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print(" ");
            curr = curr.next;
        }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode node : lists) {
            if (node != null) pq.offer(node);
        }

        ListNode dummy = new ListNode(0), tail = dummy;

        while (!pq.isEmpty()) {
            ListNode minNode = pq.poll();
            tail.next = minNode;
            tail = tail.next;
            if (minNode.next != null) pq.offer(minNode.next);
        }

        return dummy.next;
    }
}
