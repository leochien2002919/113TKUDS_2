import java.util.*;

public class BSTRangeQuerySystem {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static List<Integer> rangeQuery(TreeNode root, int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQueryHelper(root, min, max, result);
        return result;
    }

    private static void rangeQueryHelper(TreeNode node, int min, int max, List<Integer> result) {
        if (node == null) return;

        if (node.val > min) {
            rangeQueryHelper(node.left, min, max, result);
        }
        if (node.val >= min && node.val <= max) {
            result.add(node.val);
        }
        if (node.val < max) {
            rangeQueryHelper(node.right, min, max, result);
        }
    }

    public static int rangeCount(TreeNode root, int min, int max) {
        if (root == null) return 0;
        if (root.val < min) {
            return rangeCount(root.right, min, max);
        } else if (root.val > max) {
            return rangeCount(root.left, min, max);
        } else {
            return 1 + rangeCount(root.left, min, max) + rangeCount(root.right, min, max);
        }
    }

    public static int rangeSum(TreeNode root, int min, int max) {
        if (root == null) return 0;
        if (root.val < min) {
            return rangeSum(root.right, min, max);
        } else if (root.val > max) {
            return rangeSum(root.left, min, max);
        } else {
            return root.val + rangeSum(root.left, min, max) + rangeSum(root.right, min, max);
        }
    }

    public static int closestValue(TreeNode root, int target) {
        int closest = root.val;
        TreeNode current = root;
        while (current != null) {
            if (Math.abs(current.val - target) < Math.abs(closest - target)) {
                closest = current.val;
            }
            if (target < current.val) {
                current = current.left;
            } else if (target > current.val) {
                current = current.right;
            } else {

                return current.val;
            }
        }
        return closest;
    }

    public static void main(String[] args) {
      
        TreeNode root = new TreeNode(15);
        root.left = new TreeNode(10);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(8);
        root.left.right = new TreeNode(12);
        root.right.left = new TreeNode(17);
        root.right.right = new TreeNode(25);

        int min = 10, max = 20;
        System.out.println("範圍查詢節點值: " + rangeQuery(root, min, max));
        System.out.println("範圍計數: " + rangeCount(root, min, max));
        System.out.println("範圍總和: " + rangeSum(root, min, max));

        int target = 16;
        System.out.println("最接近 " + target + " 的節點值: " + closestValue(root, target));
    }
}
