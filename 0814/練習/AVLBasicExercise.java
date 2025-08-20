
public class AVLBasicExercise {

    static class Node {
        int key, height;
        Node left, right;
        Node(int k){ key = k; height = 1; }
    }

    Node root;

    static int h(Node n){ return n == null ? 0 : n.height; }
    static int bal(Node n){ return n == null ? 0 : h(n.left) - h(n.right); }
    static void upd(Node n){ if(n!=null) n.height = 1 + Math.max(h(n.left), h(n.right)); }

    static Node rr(Node y){
        Node x = y.left, t2 = x.right;
        x.right = y; y.left = t2;
        upd(y); upd(x);
        return x;
    }
    static Node lr(Node x){
        Node y = x.right, t2 = y.left;
        y.left = x; x.right = t2;
        upd(x); upd(y);
        return y;
    }
    static Node rebalance(Node n){
        upd(n);
        int b = bal(n);
        if(b > 1){
            if(bal(n.left) < 0) n.left = lr(n.left);
            return rr(n);
        }
        if(b < -1){
            if(bal(n.right) > 0) n.right = rr(n.right);
            return lr(n);
        }
        return n;
    }

    Node insert(Node n, int key){
        if(n == null) return new Node(key);
        if(key < n.key) n.left = insert(n.left, key);
        else if(key > n.key) n.right = insert(n.right, key);
        else return n; 
        return rebalance(n);
    }
    public void insert(int key){ root = insert(root, key); }

    public boolean search(int key){
        Node cur = root;
        while(cur != null){
            if(key == cur.key) return true;
            cur = key < cur.key ? cur.left : cur.right;
        }
        return false;
    }

    public int height(){ return h(root); }

    public boolean isAVL(){ return isAVL(root).ok; }

    static class Check { boolean ok; int height; Check(boolean o,int h){ok=o;height=h;} }
    static Check isAVL(Node n){
        if(n == null) return new Check(true, 0);
        Check L = isAVL(n.left), R = isAVL(n.right);
        boolean ok = L.ok && R.ok && Math.abs(L.height - R.height) <= 1;
        int height = 1 + Math.max(L.height, R.height);
        return new Check(ok, height);
    }

    public static void main(String[] args){
        AVLBasicExercise avl = new AVLBasicExercise();
        int[] arr = {10, 20, 30, 40, 50, 25};
        for(int x: arr) avl.insert(x);

        System.out.println("Height: " + avl.height());
        System.out.println("Search 25: " + avl.search(25));
        System.out.println("Is AVL: " + avl.isAVL());
    }
}
