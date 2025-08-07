public class BSTConversionAndBalance {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    static class DoublyListNode {
        int val;
        DoublyListNode prev, next;
        DoublyListNode(int val) {
            this.val = val;
        }
    }

    static DoublyListNode prevNode = null;
    static DoublyListNode headNode = null;

    public static DoublyListNode bstToDoublyLinkedList(TreeNode root) {
        prevNode = null;
        headNode = null;
        inorderToList(root);
        return headNode;
    }

    private static void inorderToList(TreeNode node) {
        if (node == null) return;

        inorderToList(node.left);

        DoublyListNode curr = new DoublyListNode(node.val);
        if (prevNode == null) {
            headNode = curr;
        } else {
            prevNode.next = curr;
            curr.prev = prevNode;
        }
        prevNode = curr;

        inorderToList(node.right);
    }

    public static TreeNode sortedArrayToBST(int[] arr) {
        return buildBSTFromArray(arr, 0, arr.length - 1);
    }

    private static TreeNode buildBSTFromArray(int[] arr, int left, int right) {
        if (left > right) return null;
        int mid = (left + right) / 2;
        TreeNode node = new TreeNode(arr[mid]);
        node.left = buildBSTFromArray(arr, left, mid - 1);
        node.right = buildBSTFromArray(arr, mid + 1, right);
        return node;
    }

    public static boolean isBalanced(TreeNode root) {
        return checkBalance(root) != -1;
    }

    private static int checkBalance(TreeNode node) {
        if (node == null) return 0;
        int left = checkBalance(node.left);
        int right = checkBalance(node.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }

    public static int getBalanceFactor(TreeNode node) {
        if (node == null) return 0;
        return height(node.left) - height(node.right);
    }

    private static int height(TreeNode node) {
        if (node == null) return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    static int greaterSum = 0;

    public static void convertToGreaterTree(TreeNode root) {
        greaterSum = 0;
        reverseInorder(root);
    }

    private static void reverseInorder(TreeNode node) {
        if (node == null) return;
        reverseInorder(node.right);
        greaterSum += node.val;
        node.val = greaterSum;
        reverseInorder(node.left);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);

        DoublyListNode head = bstToDoublyLinkedList(root);
        System.out.print("Doubly Linked List: ");
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();

        int[] sortedArr = {1, 2, 3, 4, 5, 6, 7};
        TreeNode bst = sortedArrayToBST(sortedArr);
        System.out.println("Is new BST balanced? " + isBalanced(bst));

        System.out.println("Balance Factor of root: " + getBalanceFactor(root));

        convertToGreaterTree(root);
        System.out.print("Greater Tree Inorder: ");
        printInorder(root); 
    }

    private static void printInorder(TreeNode node) {
        if (node == null) return;
        printInorder(node.left);
        System.out.print(node.val + " ");
        printInorder(node.right);
    }
}
