import java.util.*;

public class AVLRangeQueryExercise {

    static class Node {
        int key, height;
        Node left, right;
        Node(int k){ key=k; height=1; }
    }

    Node root;

    static int h(Node n){ return n==null?0:n.height; }
    static void upd(Node n){ if(n!=null) n.height = 1 + Math.max(h(n.left), h(n.right)); }
    static int bal(Node n){ return n==null?0:h(n.left)-h(n.right); }
    static Node rr(Node y){ Node x=y.left,t2=x.right; x.right=y; y.left=t2; upd(y); upd(x); return x; }
    static Node lr(Node x){ Node y=x.right,t2=y.left; y.left=x; x.right=t2; upd(x); upd(y); return y; }
    static Node rebalance(Node n){
        upd(n);
        int b=bal(n);
        if(b>1){ if(bal(n.left)<0) n.left=lr(n.left); return rr(n); }
        if(b<-1){ if(bal(n.right)>0) n.right=rr(n.right); return lr(n); }
        return n;
    }

    Node insert(Node n, int key){
        if(n==null) return new Node(key);
        if(key<n.key) n.left=insert(n.left,key);
        else if(key>n.key) n.right=insert(n.right,key);
        return rebalance(n);
    }
    public void insert(int key){ root = insert(root,key); }

    public List<Integer> rangeQuery(int min, int max){
        ArrayList<Integer> res = new ArrayList<>();
        range(root, min, max, res);
        return res;
    }

    void range(Node n, int min, int max, List<Integer> out){
        if(n==null) return;
        if(n.key > min) range(n.left, min, max, out);
        if(n.key >= min && n.key <= max) out.add(n.key);
        if(n.key < max) range(n.right, min, max, out);
    }

    public static void main(String[] args){
        AVLRangeQueryExercise t = new AVLRangeQueryExercise();
        for(int x: new int[]{15,10,20,8,12,17,25,19,30}) t.insert(x);
        System.out.println(t.rangeQuery(12, 22));
    }
}
