import java.io.*;
import java.util.*;

public class M12_MergeKTimeTables {

    static class Node implements Comparable<Node> {
        int time;
        int listIdx;
        int idxInList;
        Node(int t, int l, int i) { time = t; listIdx = l; idxInList = i; }
        public int compareTo(Node other) { return this.time - other.time; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine().trim());
        List<int[]> lists = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            int len = Integer.parseInt(br.readLine().trim());
            int[] arr = new int[len];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < len; j++) arr[j] = Integer.parseInt(st.nextToken());
            lists.add(arr);
        }

        List<Integer> merged = mergeKLists(lists);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < merged.size(); i++) {
            sb.append(merged.get(i));
            if (i < merged.size() - 1) sb.append(" ");
        }
        System.out.println(sb.toString());
    }

    private static List<Integer> mergeKLists(List<int[]> lists) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).length > 0)
                pq.offer(new Node(lists.get(i)[0], i, 0));
        }

        List<Integer> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            res.add(cur.time);
            int[] arr = lists.get(cur.listIdx);
            int nextIdx = cur.idxInList + 1;
            if (nextIdx < arr.length) {
                pq.offer(new Node(arr[nextIdx], cur.listIdx, nextIdx));
            }
        }
        return res;
    }
}
