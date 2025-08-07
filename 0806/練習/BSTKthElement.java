import java.util.*;

public class BSTKthElement {

    static class TreeNode {
        int val, size; 
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
            this.size = 1;
        }
    }

    private static int getSize(TreeNode node) {
        return node == null ? 0 : node.size;
    }

    private static void updateSize(TreeNode node) {
        if (node != null) {
            node.size = getSize(node.left) + getSize(node.right) + 1;
        }
    }

    public static TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insert(root.left, val);
        else root.right = insert(root.right, val);
        updateSize(root);
        return root;
    }

    public static TreeNode delete(TreeNode root, int val) {
        if (root == null) return null;
        if (val < root.val) root.left = delete(root.left, val);
        else if (val > root.val) root.right = delete(root.right, val);
        else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            TreeNode minNode = findMin(root.right);
            root.val = minNode.val;
            root.right = delete(root.right, minNode.val);
        }
        updateSize(root);
        return root;
    }

    private static TreeNode findMin(TreeNode root) {
        while (root.left != null) root = root.left;
        return root;
    }

    public static int kthSmallest(TreeNode root, int k) {
        int leftSize = getSize(root.left);
        if (k <= leftSize) {
            return kthSmallest(root.left, k);
        } else if (k == leftSize + 1) {
            return root.val;
        } else {
            return kthSmallest(root.right, k - leftSize - 1);
        }
    }

    public static int kthLargest(TreeNode root, int k) {
        int total = getSize(root);
        return kthSmallest(root, total - k + 1);
    }

    public static List<Integer> rangeKtoJ(TreeNode root, int k, int j) {
        List<Integer> result = new ArrayList<>();
        inOrderRange(root, k, j, result);
        return result;
    }

    private static int counter = 0;
    private static void inOrderRange(TreeNode node, int k, int j, List<Integer> result) {
        if (node == null) return;
        inOrderRange(node.left, k, j, result);
        counter++;
        if (counter >= k && counter <= j) {
            result.add(node.val);
        }
        if (counter > j) return; 
        inOrderRange(node.right, k, j, result);
    }

    public static void main(String[] args) {

        TreeNode root = null;
        for (int val : new int[]{10, 5, 15, 3, 7, 18}) {
            root = insert(root, val);
        }

        System.out.println("第 3 小元素: " + kthSmallest(root, 3)); 
        System.out.println("第 2 大元素: " + kthLargest(root, 2)); 

        counter = 0;
        System.out.println("第 2 小到第 4 小的元素: " + rangeKtoJ(root, 2, 4));

        root = insert(root, 6);
        System.out.println("插入 6 後第 4 小元素: " + kthSmallest(root, 4));

        root = delete(root, 7);
        System.out.println("刪除 7 後第 4 小元素: " + kthSmallest(root, 4));
    }
}
