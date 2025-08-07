
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) {
        val = x;
    }
}

public class BSTValidationAndRepair {

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode node, Integer min, Integer max) {
        if (node == null) return true;
        if ((min != null && node.val <= min) || (max != null && node.val >= max)) return false;
        return isValidBST(node.left, min, node.val) &&
               isValidBST(node.right, node.val, max);
    }

    TreeNode first = null, second = null, prev = new TreeNode(Integer.MIN_VALUE);

    public void findSwappedNodes(TreeNode root) {
        first = second = null;
        prev = new TreeNode(Integer.MIN_VALUE);
        inOrderFind(root);
    }

    private void inOrderFind(TreeNode node) {
        if (node == null) return;
        inOrderFind(node.left);
        if (first == null && prev.val >= node.val) {
            first = prev;
        }
        if (first != null && prev.val >= node.val) {
            second = node;
        }
        prev = node;
        inOrderFind(node.right);
    }

    public void recoverBST(TreeNode root) {
        findSwappedNodes(root);
        if (first != null && second != null) {
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }

    public int minRemovalsToValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE).removalCount;
    }

    class Result {
        boolean isValid;
        int removalCount;
        Result(boolean isValid, int removalCount) {
            this.isValid = isValid;
            this.removalCount = removalCount;
        }
    }

    private Result dfs(TreeNode node, long min, long max) {
        if (node == null) return new Result(true, 0);

        boolean validHere = node.val > min && node.val < max;

        Result left = dfs(node.left, min, Math.min(max, node.val));
        Result right = dfs(node.right, Math.max(min, node.val), max);

        if (validHere && left.isValid && right.isValid) {
            return new Result(true, left.removalCount + right.removalCount);
        } else {

            int removeNode = 1 + left.removalCount + right.removalCount;
            return new Result(false, removeNode);
        }
    }

    public static void main(String[] args) {
        BSTValidationAndRepair solver = new BSTValidationAndRepair();

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2); 

        System.out.println("原始是否合法 BST: " + solver.isValidBST(root));
        solver.recoverBST(root);
        System.out.println("修復後是否合法 BST: " + solver.isValidBST(root));

        TreeNode broken = new TreeNode(10);
        broken.left = new TreeNode(5);
        broken.right = new TreeNode(8);

        System.out.println("需要移除的節點數: " + solver.minRemovalsToValidBST(broken));
    }
}
