import java.io.*;
import java.util.*;

/*
 * Time Complexity: O(n log K)
 * 共 n 筆輸入，每次可能插入或更新 Min-Heap（大小最多 K），
 * 單次操作 O(log K)，因此總時間 O(n log K)。
 * Space Complexity: O(n)
 */

public class M03_TopKConvenience {

    static class Item {
        String name;
        int qty;
        Item(String n, int q) {
            name = n; qty = q;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<Item> heap = new PriorityQueue<>(K, (a, b) -> {
            if (a.qty != b.qty) return a.qty - b.qty;
            return a.name.compareTo(b.name);
        });

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int qty = Integer.parseInt(st.nextToken());

            Item item = new Item(name, qty);

            if (heap.size() < K) {
                heap.offer(item);
            } else {
                Item min = heap.peek();
                if (compareBetter(item, min) > 0) {
                    heap.poll();
                    heap.offer(item);
                }
            }
        }

        List<Item> result = new ArrayList<>(heap);
        result.sort((a, b) -> {
            if (b.qty != a.qty) return b.qty - a.qty;
            return a.name.compareTo(b.name);
        });

        for (Item it : result) {
            System.out.println(it.name + " " + it.qty);
        }
    }

    private static int compareBetter(Item a, Item b) {
        if (a.qty != b.qty) return a.qty - b.qty;
        return a.name.compareTo(b.name);
    }
}
