import java.util.*;

public class KthSmallestElement {
    public static int kthSmallestMaxHeap(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : arr) {
            maxHeap.add(num);
            if (maxHeap.size() > k) maxHeap.poll();
        }
        return maxHeap.peek();
    }

    public static int kthSmallestMinHeap(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : arr) minHeap.add(num);
        int val = -1;
        for (int i = 0; i < k; i++) val = minHeap.poll();
        return val;
    }

    public static void main(String[] args) {
        int[] arr1 = {7, 10, 4, 3, 20, 15};
        System.out.println(kthSmallestMaxHeap(arr1, 3));
        System.out.println(kthSmallestMinHeap(arr1, 3));

        int[] arr2 = {1};
        System.out.println(kthSmallestMaxHeap(arr2, 1));
        System.out.println(kthSmallestMinHeap(arr2, 1));

        int[] arr3 = {3, 1, 4, 1, 5, 9, 2, 6};
        System.out.println(kthSmallestMaxHeap(arr3, 4));
        System.out.println(kthSmallestMinHeap(arr3, 4)); 
    }
}
