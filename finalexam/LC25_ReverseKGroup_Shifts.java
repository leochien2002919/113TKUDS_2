import java.util.*;

public class LC25_ReverseKGroup_Shifts {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        sc.nextLine();
        String line = sc.nextLine().trim();
        sc.close();

        if (line.isEmpty()) return;

        String[] parts = line.split(" ");
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (String s : parts) {
            curr.next = new ListNode(Integer.parseInt(s));
            curr = curr.next;
        }

        ListNode head = reverseKGroup(dummy.next, k);

        curr = head;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print(" ");
            curr = curr.next;
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroup = dummy;

        while (true) {
            ListNode kth = prevGroup;
            for (int i = 0; i < k && kth != null; i++) kth = kth.next;
            if (kth == null) break;

            ListNode groupStart = prevGroup.next;
            ListNode nextGroup = kth.next;

            ListNode prev = nextGroup;
            ListNode curr = groupStart;
            while (curr != nextGroup) {
                ListNode tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }

            prevGroup.next = kth;
            prevGroup = groupStart;
        }

        return dummy.next;
    }
}
