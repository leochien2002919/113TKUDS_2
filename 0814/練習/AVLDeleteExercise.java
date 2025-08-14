public class AVLDeleteExercise {

    static class Node {
        int key, height;
        Node left, right;
        Node(int k){ key=k; height=1; }
    }

    Node root;

    static int h(Node n){ return n==null?0:n.height; }
    static void upd(Node n){ if(n!=null) n.height = 1 + Math.max(h(n.left), h(n.right)); }
    static int bal(Node n){ return n==null?0:h(n.left)-h(n.right); }

    static Node rr(Node y){
        Node x=y.left,t2=x.right;
        x.right=y; y.left=t2;
        upd(y); upd(x);
        return x;
    }
    static Node lr(Node x){
        Node y=x.right,t2=y.left;
        y.left=x; x.right=t2;
        upd(x); upd(y);
        return y;
    }
    static Node rebalance(Node n){
        upd(n);
        int b = bal(n);
        if(b>1){
            if(bal(n.left)<0) n.left = lr(n.left);
            return rr(n);
        }
        if(b<-1){
            if(bal(n.right)>0) n.right = rr(n.right);
            return lr(n);
        }
        return n;
    }

    Node insert(Node n, int key){
        if(n==null) return new Node(key);
        if(key<n.key) n.left = insert(n.left,key);
        else if(key>n.key) n.right = insert(n.right,key);
        return rebalance(n);
    }
    public void insert(int key){ root = insert(root,key); }

    static Node minNode(Node n){
        while(n.left!=null) n=n.left;
        return n;
    }

    Node delete(Node n, int key){
        if(n==null) return null;
        if(key < n.key) n.left = delete(n.left,key);
        else if(key > n.key) n.right = delete(n.right,key);
        else{
            if(n.left==null || n.right==null){
                n = (n.left!=null) ? n.left : n.right;
            }else{

                Node s = minNode(n.right);
                n.key = s.key;
                n.right = delete(n.right, s.key);
            }
        }
        if(n==null) return null;
        return rebalance(n);
    }
    public void delete(int key){ root = delete(root,key); }

    public static void main(String[] args){
        AVLDeleteExercise t = new AVLDeleteExercise();
        int[] a={20,10,30,5,15,25,40,22,27};
        for(int x:a) t.insert(x);
        t.delete(5);
        t.delete(25);
        t.delete(20);
        System.out.println("Done deletions.");
    }
}
