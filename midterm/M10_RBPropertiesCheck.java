import java.io.*;
import java.util.*;

public class M10_RBPropertiesCheck {

    static class Node {
        int val;
        char color;
        Node(int v, char c) { val = v; color = c; }
    }

    static Node[] nodes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        nodes = new Node[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int val = Integer.parseInt(st.nextToken());
            String colorStr = st.nextToken();
            char color = (val == -1) ? 'B' : colorStr.charAt(0);
            nodes[i] = new Node(val, color);
        }

        if (nodes[0].color != 'B') {
            System.out.println("RootNotBlack");
            return;
        }

        for (int i = 0; i < n; i++) {
            Node node = nodes[i];
            if (node.val == -1 || node.color == 'B') continue;
            int left = 2 * i + 1, right = 2 * i + 2;
            if (left < n && nodes[left].val != -1 && nodes[left].color == 'R') {
                System.out.println("RedRedViolation at index " + i);
                return;
            }
            if (right < n && nodes[right].val != -1 && nodes[right].color == 'R') {
                System.out.println("RedRedViolation at index " + i);
                return;
            }
        }

        if (!checkBlackHeight(0).ok) {
            System.out.println("BlackHeightMismatch");
            return;
        }

        System.out.println("RB Valid");
    }

    static class BHResult {
        boolean ok;
        int blackHeight;
        BHResult(boolean o, int bh) { ok = o; blackHeight = bh; }
    }

    static BHResult checkBlackHeight(int idx) {
        if (idx >= nodes.length || nodes[idx].val == -1) return new BHResult(true, 1);
        Node node = nodes[idx];
        BHResult left = checkBlackHeight(2 * idx + 1);
        if (!left.ok) return new BHResult(false, 0);
        BHResult right = checkBlackHeight(2 * idx + 2);
        if (!right.ok) return new BHResult(false, 0);
        if (left.blackHeight != right.blackHeight) return new BHResult(false, 0);
        int bh = left.blackHeight + ((node.color == 'B') ? 1 : 0);
        return new BHResult(true, bh);
    }
}
