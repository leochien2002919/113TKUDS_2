public class TreeMirrorAndSymmetry {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    public static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
            && isMirror(t1.left, t2.right)
            && isMirror(t1.right, t2.left);
    }

    public static void mirror(TreeNode root) {
        if (root == null) return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirror(root.left);
        mirror(root.right);
    }

    public static boolean isSubtree(TreeNode t1, TreeNode t2) {
        if (t2 == null) return true;
        if (t1 == null) return false;
        if (isSameTree(t1, t2)) return true;
        return isSubtree(t1.left, t2) || isSubtree(t1.right, t2);
    }

    private static boolean isSameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return (a.val == b.val)
            && isSameTree(a.left, b.left)
            && isSameTree(a.right, b.right);
    }

    public static void main(String[] args) {
    
        TreeNode symmetric = new TreeNode(1);
        symmetric.left = new TreeNode(2);
        symmetric.right = new TreeNode(2);
        symmetric.left.left = new TreeNode(3);
        symmetric.left.right = new TreeNode(4);
        symmetric.right.left = new TreeNode(4);
        symmetric.right.right = new TreeNode(3);

        System.out.println("是否為對稱樹: " + isSymmetric(symmetric));

        TreeNode mirrorTree = new TreeNode(1);
        mirrorTree.left = new TreeNode(2);
        mirrorTree.right = new TreeNode(3);
        mirror(mirrorTree); 
        System.out.println("鏡像後左節點值: " + mirrorTree.left.val); // 原本是 2，鏡像後應為 3

        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(2);
        t1.right = new TreeNode(3);

        TreeNode t2 = new TreeNode(1);
        t2.left = new TreeNode(3);
        t2.right = new TreeNode(2);

        System.out.println("是否互為鏡像: " + isMirror(t1, t2));

        TreeNode big = new TreeNode(1);
        big.left = new TreeNode(2);
        big.right = new TreeNode(3);
        big.left.left = new TreeNode(4);
        big.left.right = new TreeNode(5);

        TreeNode small = new TreeNode(2);
        small.left = new TreeNode(4);
        small.right = new TreeNode(5);

        System.out.println("是否為子樹: " + isSubtree(big, small));
    }
}
