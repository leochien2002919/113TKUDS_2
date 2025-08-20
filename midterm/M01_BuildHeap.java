import java.io.*;
import java.util.*;

/*
 * Time Complexity: O(n)
 * 自底向上建堆從最後一個非葉節點 (n/2 - 1) 開始做 heapifyDown。
 * 節點下沉成本與其高度成正比，所有節點的總高度和為 O(n)，因此建堆總成本為 O(n)。
 * Space Complexity: O(1)
 */

public class M01_BuildHeap {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String typeLine = readNonEmptyLine(br);
        String nLine = readNonEmptyLine(br);

        String type = typeLine.trim();
        int n = Integer.parseInt(nLine.trim());

        int[] a = new int[n];
        fillArray(br, a);

        boolean isMax = type.equalsIgnoreCase("max");
        buildHeap(a, isMax);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(' ');
            sb.append(a[i]);
        }
        System.out.println(sb.toString());
    }

    private static String readNonEmptyLine(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            if (!line.trim().isEmpty()) return line;
        }
        return "";
    }

    private static void fillArray(BufferedReader br, int[] a) throws IOException {
        int n = a.length, idx = 0;
        StringTokenizer st = null;
        while (idx < n) {
            if (st == null || !st.hasMoreTokens()) {
                String line = br.readLine();
                if (line == null) break;
                st = new StringTokenizer(line);
                continue;
            }
            a[idx++] = Integer.parseInt(st.nextToken());
        }
    }

    private static void buildHeap(int[] a, boolean isMax) {
        int n = a.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyDown(a, n, i, isMax);
        }
    }

    private static void heapifyDown(int[] a, int n, int i, boolean isMax) {
        while (true) {
            int left = 2 * i + 1;
            if (left >= n) break;
            int best = left;
            int right = left + 1;

            if (right < n && better(a[right], a[best], isMax)) {
                best = right;
            }
            if (better(a[best], a[i], isMax)) {
                swap(a, i, best);
                i = best;
            } else {
                break;
            }
        }
    }
    private static boolean better(int x, int y, boolean isMax) {
        return isMax ? (x > y) : (x < y);
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
}
