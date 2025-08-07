import java.util.*;

class MultiWayTreeNode {
    String data;
    List<MultiWayTreeNode> children;

    MultiWayTreeNode(String data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

    void addChild(MultiWayTreeNode child) {
        children.add(child);
    }
}

public class MultiWayTreeAndDecisionTree {

    public static void dfs(MultiWayTreeNode node) {
        if (node == null) return;
        System.out.print(node.data + " ");
        for (MultiWayTreeNode child : node.children) {
            dfs(child);
        }
    }

    public static void bfs(MultiWayTreeNode root) {
        if (root == null) return;
        Queue<MultiWayTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            MultiWayTreeNode node = queue.poll();
            System.out.print(node.data + " ");
            queue.addAll(node.children);
        }
    }

    public static int getHeight(MultiWayTreeNode node) {
        if (node == null) return 0;
        int maxChildHeight = 0;
        for (MultiWayTreeNode child : node.children) {
            maxChildHeight = Math.max(maxChildHeight, getHeight(child));
        }
        return maxChildHeight + 1;
    }

    public static void printDegree(MultiWayTreeNode node) {
        if (node == null) return;
        System.out.println("節點 [" + node.data + "] 的度數為：" + node.children.size());
        for (MultiWayTreeNode child : node.children) {
            printDegree(child);
        }
    }

    static class DecisionNode {
        String question;
        DecisionNode yes;
        DecisionNode no;

        DecisionNode(String question) {
            this.question = question;
        }
    }

    public static void playDecisionTree(DecisionNode root, Scanner scanner) {
        DecisionNode current = root;
        while (current.yes != null && current.no != null) {
            System.out.print(current.question + " (y/n): ");
            String ans = scanner.nextLine().trim().toLowerCase();
            if (ans.equals("y")) {
                current = current.yes;
            } else {
                current = current.no;
            }
        }
        System.out.println("猜測結果：" + current.question);
    }

    public static void main(String[] args) {
        MultiWayTreeNode root = new MultiWayTreeNode("A");
        MultiWayTreeNode b = new MultiWayTreeNode("B");
        MultiWayTreeNode c = new MultiWayTreeNode("C");
        MultiWayTreeNode d = new MultiWayTreeNode("D");
        MultiWayTreeNode e = new MultiWayTreeNode("E");
        MultiWayTreeNode f = new MultiWayTreeNode("F");

        root.addChild(b);
        root.addChild(c);
        b.addChild(d);
        b.addChild(e);
        c.addChild(f);

        System.out.println("DFS 走訪：");
        dfs(root);
        System.out.println("\nBFS 走訪：");
        bfs(root);

        System.out.println("\n\n樹的高度：" + getHeight(root));
        System.out.println("各節點度數：");
        printDegree(root);

        DecisionNode q1 = new DecisionNode("這個數字 > 5？");
        DecisionNode q2 = new DecisionNode("這個數字 > 8？");
        DecisionNode q3 = new DecisionNode("這個數字 > 3？");

        q1.yes = q2;
        q1.no = q3;

        q2.yes = new DecisionNode("10");
        q2.no = new DecisionNode("7");
        q3.yes = new DecisionNode("4");
        q3.no = new DecisionNode("1");

        System.out.println("\n--- 猜數字遊戲 ---");
        Scanner scanner = new Scanner(System.in);
        playDecisionTree(q1, scanner);
        scanner.close();
    }
}
