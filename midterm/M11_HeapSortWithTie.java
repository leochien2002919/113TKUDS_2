import java.io.*;
import java.util.*;

/*
 * Time Complexity: O(n log n)
 * 建堆 Bottom-Up O(n)，每次 extract-max 調整堆 O(log n)，共 n 次 → O(n log n)
 * Space Complexity: O(n)
 */

public class M11_HeapSortWithTie {

    static class Item {
        int score;
        int index;
        Item(int s, int i) { score = s; index = i; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());

        Item[] arr = new Item[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Item(Integer.parseInt(st.nextToken()), i);
        }

        heapSort(arr);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(arr[i].score);
            if (i < n - 1) sb.append(" ");
        }
        System.out.println(sb.toString());
    }

    private static void heapSort(Item[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    private static void heapify(Item[] arr, int size, int i) {
        int largest = i;
        int left = 2 * i + 1, right = 2 * i + 2;

        if (left < size && compare(arr[left], arr[largest]) > 0) largest = left;
        if (right < size && compare(arr[right], arr[largest]) > 0) largest = right;

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, size, largest);
        }
    }

    private static int compare(Item a, Item b) {
        if (a.score != b.score) return a.score - b.score;
        return b.index - a.index;
    }

    private static void swap(Item[] arr, int i, int j) {
        Item tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
