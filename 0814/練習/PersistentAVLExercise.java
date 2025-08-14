import java.util.*;

public class PersistentAVLExercise {

    static class PNode {
        final int key, height;
        final PNode left, right;
        PNode(int key, PNode left, PNode right){
            this.key = key; this.left = left; this.right = right;
            this.height = 1 + Math.max(h(left), h(right));
        }
    }

    final List<PNode> versions = new ArrayList<>();

    static int h(PNode n){ return n==null?0:n.height; }
    static int bal(PNode n){ return n==null?0:h(n.left)-h(n.right); }

    static PNode mk(int key, PNode L, PNode R){ return new PNode(key, L, R); }

    static PNode rr(PNode y){
        PNode x = y.left;
        PNode t2 = x.right;

        PNode newY = mk(y.key, t2, y.right);
        PNode newX = mk(x.key, x.left, newY);
        return newX;
    }
    static PNode lr(PNode x){
        PNode y = x.right;
        PNode t2 = y.left;
        PNode newX = mk(x.key, x.left, t2);
        PNode newY = mk(y.key, newX, y.right);
        return newY;
    }

    static PNode rebalance(PNode n){
        int b = bal(n);
        if(b > 1){
            if(bal(n.left) < 0)
                return rr(mk(n.key, lr(n.left), n.right));
            return rr(n); 
        }
        if(b < -1){
            if(bal(n.right) > 0) 
                return lr(mk(n.key, n.left, rr(n.right)));
            return lr(n);
        }
        return n;
    }

    static PNode insert(PNode n, int key){
        if(n==null) return mk(key, null, null);
        if(key < n.key)  return rebalance(mk(n.key, insert(n.left, key), n.right));
        if(key > n.key)  return rebalance(mk(n.key, n.left, insert(n.right, key)));
        return n;
    }

    public int createVersionByInsert(int baseVersionIndex, int key){
        PNode base = (baseVersionIndex<0 || baseVersionIndex>=versions.size()) ? null : versions.get(baseVersionIndex);
        PNode nxt = insert(base, key);
        versions.add(nxt);
        return versions.size()-1;
    }

    public boolean search(int versionIndex, int key){
        PNode n = versions.get(versionIndex);
        while(n!=null){
            if(key==n.key) return true;
            n = key < n.key ? n.left : n.right;
        }
        return false;
    }

    public int height(int versionIndex){
        return h(versions.get(versionIndex));
    }

    public static void main(String[] args){
        PersistentAVLExercise pavl = new PersistentAVLExercise();
        int v0 = pavl.createVersionByInsert(-1, 20);
        int v1 = pavl.createVersionByInsert(v0, 10);
        int v2 = pavl.createVersionByInsert(v1, 30);
        int v3 = pavl.createVersionByInsert(v2, 25);
        int v4 = pavl.createVersionByInsert(v3, 40);

        System.out.println("v2 height = " + pavl.height(v2));
        System.out.println("v2 has 25? " + pavl.search(v2, 25));
        System.out.println("v4 has 25? " + pavl.search(v4, 25));
    }
}
