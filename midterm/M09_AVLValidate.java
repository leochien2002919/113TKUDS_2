import java.io.*;
import java.util.*;

/*
 * Time Complexity: O(n)
 * 每個節點只被拜訪一次，在同一次遞迴中同時檢查 BST 與 AVL，
 * 各節點的操作為 O(1)，因此總時間複雜度為 O(n)，
 * 其中 n 為節點數。
 */

public class M09_AVLValidate {

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

        TreeNode root = buildTree(vals);

        if (!isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("Invalid BST");
            return;
        }

        if (checkAVL(root) == -2) {
            System.out.println("Invalid AVL");
            return;
        }

        System.out.println("Valid");
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

    private static boolean isBST(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return isBST(node.left, min, node.val) && isBST(node.right, node.val, max);
    }

    private static int checkAVL(TreeNode node) {
        if (node == null) return 0;

        int left = checkAVL(node.left);
        if (left == -2) return -2;
        int right = checkAVL(node.right);
        if (right == -2) return -2;

        if (Math.abs(left - right) > 1) return -2;

        return Math.max(left, right) + 1;
    }
}
