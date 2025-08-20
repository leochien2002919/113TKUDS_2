import java.io.*;
import java.util.*;

public class M08_BSTRangedSum {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] vals = new int[n];
        for (int i = 0; i < n; i++) vals[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        TreeNode root = buildTree(vals);
        int sum = rangeSumBST(root, L, R);

        System.out.println("Sum: " + sum);
    }

    private static TreeNode buildTree(int[] vals) {
        if (vals.length == 0 || vals[0] == -1) return null;
        TreeNode root = new TreeNode(vals[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i = 1;
        while (!q.isEmpty() && i < vals.length) {
            TreeNode cur = q.poll();

            if (i < vals.length && vals[i] != -1) {
                cur.left = new TreeNode(vals[i]);
                q.offer(cur.left);
            }
            i++;

            if (i < vals.length && vals[i] != -1) {
                cur.right = new TreeNode(vals[i]);
                q.offer(cur.right);
            }
            i++;
        }
        return root;
    }

    private static int rangeSumBST(TreeNode node, int L, int R) {
        if (node == null) return 0;
        if (node.val < L) {
            return rangeSumBST(node.right, L, R);
        } else if (node.val > R) {
            return rangeSumBST(node.left, L, R);
        } else {
            return node.val + rangeSumBST(node.left, L, R) + rangeSumBST(node.right, L, R);
        }
    }
}
