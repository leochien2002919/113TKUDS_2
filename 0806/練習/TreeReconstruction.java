import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

public class TreeReconstruction {

    public TreeNode buildTreeFromPreIn(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            inMap.put(inorder[i], i);
        return buildPreIn(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    private TreeNode buildPreIn(int[] pre, int ps, int pe, int[] in, int is, int ie, Map<Integer, Integer> inMap) {
        if (ps > pe) return null;
        TreeNode root = new TreeNode(pre[ps]);
        int ri = inMap.get(pre[ps]);
        int leftSize = ri - is;
        root.left = buildPreIn(pre, ps + 1, ps + leftSize, in, is, ri - 1, inMap);
        root.right = buildPreIn(pre, ps + leftSize + 1, pe, in, ri + 1, ie, inMap);
        return root;
    }

    public TreeNode buildTreeFromPostIn(int[] postorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            inMap.put(inorder[i], i);
        return buildPostIn(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    private TreeNode buildPostIn(int[] post, int ps, int pe, int[] in, int is, int ie, Map<Integer, Integer> inMap) {
        if (ps > pe) return null;
        TreeNode root = new TreeNode(post[pe]);
        int ri = inMap.get(post[pe]);
        int leftSize = ri - is;
        root.left = buildPostIn(post, ps, ps + leftSize - 1, in, is, ri - 1, inMap);
        root.right = buildPostIn(post, ps + leftSize, pe - 1, in, ri + 1, ie, inMap);
        return root;
    }

    public TreeNode buildCompleteTreeFromLevelOrder(Integer[] levelOrder) {
        if (levelOrder.length == 0 || levelOrder[0] == null) return null;
        TreeNode root = new TreeNode(levelOrder[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (i < levelOrder.length) {
            TreeNode current = queue.poll();
            if (i < levelOrder.length && levelOrder[i] != null) {
                current.left = new TreeNode(levelOrder[i]);
                queue.add(current.left);
            }
            i++;
            if (i < levelOrder.length && levelOrder[i] != null) {
                current.right = new TreeNode(levelOrder[i]);
                queue.add(current.right);
            }
            i++;
        }
        return root;
    }

    public boolean isSameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null || a.val != b.val) return false;
        return isSameTree(a.left, b.left) && isSameTree(a.right, b.right);
    }

    public void printInOrder(TreeNode root) {
        if (root == null) return;
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }

    public static void main(String[] args) {
        TreeReconstruction tr = new TreeReconstruction();

        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        Integer[] levelOrder = {3, 9, 20, null, null, 15, 7};

        TreeNode preInTree = tr.buildTreeFromPreIn(preorder, inorder);
        TreeNode postInTree = tr.buildTreeFromPostIn(postorder, inorder);
        TreeNode levelTree = tr.buildCompleteTreeFromLevelOrder(levelOrder);

        System.out.print("Pre-In 重建中序：");
        tr.printInOrder(preInTree);
        System.out.println();

        System.out.print("Post-In 重建中序：");
        tr.printInOrder(postInTree);
        System.out.println();

        System.out.print("LevelOrder 重建中序：");
        tr.printInOrder(levelTree);
        System.out.println();

        System.out.println("Pre-In == Post-In? " + tr.isSameTree(preInTree, postInTree));
    }
}
