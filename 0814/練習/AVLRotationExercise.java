
public class AVLRotationExercise {
    static class Node {
        int key, height;
        Node left, right;
        Node(int k){ key=k; height=1; }
    }
    static int h(Node n){ return n==null?0:n.height; }
    static void upd(Node n){ if(n!=null) n.height = 1 + Math.max(h(n.left), h(n.right)); }
    static int bal(Node n){ return n==null?0:h(n.left)-h(n.right); }

    static Node rightRotate(Node y){
        Node x = y.left, t2 = x.right;
        x.right = y; y.left = t2;
        upd(y); upd(x);
        return x;
    }

    static Node leftRotate(Node x){
        Node y = x.right, t2 = y.left;
        y.left = x; x.right = t2;
        upd(x); upd(y);
        return y;
    }

    static Node rebalance(Node n){
        upd(n);
        int b = bal(n);
        if(b > 1){
            if(bal(n.left) < 0) n.left = leftRotate(n.left);
            return rightRotate(n);
        }
        if(b < -1){
            if(bal(n.right) > 0) n.right = rightRotate(n.right);
            return leftRotate(n);
        }
        return n;
    }

    static Node insert(Node n, int key){
        if(n==null) return new Node(key);
        if(key < n.key) n.left = insert(n.left, key);
        else if(key > n.key) n.right = insert(n.right, key);
        return rebalance(n);
    }

    static void inorder(Node n){
        if(n==null) return;
        inorder(n.left);
        System.out.print(n.key+" ");
        inorder(n.right);
    }

    public static void main(String[] args){

        Node r1=null; for(int x:new int[]{30,20,10}) r1=insert(r1,x);
        System.out.print("LL inorder: "); inorder(r1); System.out.println();

        Node r2=null; for(int x:new int[]{10,20,30}) r2=insert(r2,x);
        System.out.print("RR inorder: "); inorder(r2); System.out.println();

        Node r3=null; for(int x:new int[]{30,10,20}) r3=insert(r3,x);
        System.out.print("LR inorder: "); inorder(r3); System.out.println();

        Node r4=null; for(int x:new int[]{10,30,20}) r4=insert(r4,x);
        System.out.print("RL inorder: "); inorder(r4); System.out.println();
    }
}
