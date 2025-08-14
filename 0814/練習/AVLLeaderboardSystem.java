import java.util.*;

public class AVLLeaderboardSystem {

    static class Node {
        int score, playerId, height, size;
        Node left, right;
        Node(int score, int playerId){
            this.score=score; this.playerId=playerId;
            this.height=1; this.size=1;
        }
    }

    Node root;
    Map<Integer,Integer> playerScore = new HashMap<>(); 

    static int cmpKey(int s1,int p1,int s2,int p2){
        if(s1!=s2) return (s2 - s1);
        return Integer.compare(p1, p2); 
    }

    static int h(Node n){ return n==null?0:n.height; }
    static int sz(Node n){ return n==null?0:n.size; }
    static void pull(Node n){
        if(n!=null){
            n.height = 1 + Math.max(h(n.left), h(n.right));
            n.size   = 1 + sz(n.left) + sz(n.right);
        }
    }
    static int bal(Node n){ return n==null?0:h(n.left)-h(n.right); }

    static Node rr(Node y){
        Node x=y.left,t2=x.right;
        x.right=y; y.left=t2;
        pull(y); pull(x);
        return x;
    }
    static Node lr(Node x){
        Node y=x.right,t2=y.left;
        y.left=x; x.right=t2;
        pull(x); pull(y);
        return y;
    }
    static Node rebalance(Node n){
        pull(n);
        int b=bal(n);
        if(b>1){
            if(bal(n.left)<0) n.left=lr(n.left);
            return rr(n);
        }
        if(b<-1){
            if(bal(n.right)>0) n.right=rr(n.right);
            return lr(n);
        }
        return n;
    }

    Node insert(Node n, int score, int playerId){
        if(n==null) return new Node(score, playerId);
        int c = cmpKey(score, playerId, n.score, n.playerId);
        if(c < 0) n.left  = insert(n.left, score, playerId);
        else if(c > 0) n.right = insert(n.right, score, playerId);
        else return n;
        return rebalance(n);
    }

    Node delete(Node n, int score, int playerId){
        if(n==null) return null;
        int c = cmpKey(score, playerId, n.score, n.playerId);
        if(c < 0) n.left = delete(n.left, score, playerId);
        else if(c > 0) n.right = delete(n.right, score, playerId);
        else{
            if(n.left==null || n.right==null){
                n = (n.left!=null)? n.left : n.right;
            }else{

                Node s = n.right;
                while(s.left!=null) s=s.left;
                n.score=s.score; n.playerId=s.playerId;
                n.right = delete(n.right, s.score, s.playerId);
            }
        }
        if(n==null) return null;
        return rebalance(n);
    }

    public void addOrUpdateScore(int playerId, int score){
        if(playerScore.containsKey(playerId)){
            int old = playerScore.get(playerId);
            root = delete(root, old, playerId);
        }
        playerScore.put(playerId, score);
        root = insert(root, score, playerId);
    }

    public int getRank(int playerId){
        Integer sc = playerScore.get(playerId);
        if(sc == null) return -1;
        return rank(root, sc, playerId);
    }

    int rank(Node n, int score, int playerId){
        int r = 1;
        while(n != null){
            int c = cmpKey(score, playerId, n.score, n.playerId);
            if(c < 0){
                n = n.left;
            }else if(c > 0){
                r += sz(n.left) + 1;
                n = n.right;
            }else{
                return r + sz(n.left);
            }
        }
        return -1;
    }

    public int[] select(int k){
        if(k<=0 || k>sz(root)) return null;
        Node n = root;
        while(n != null){
            int left = sz(n.left);
            if(k == left + 1) return new int[]{n.playerId, n.score};
            if(k <= left) n = n.left;
            else { k -= left + 1; n = n.right; }
        }
        return null;
    }

    public List<int[]> topK(int k){
        k = Math.min(k, sz(root));
        ArrayList<int[]> res = new ArrayList<>();
        dfsTopK(root, res, k);
        return res;
    }
    void dfsTopK(Node n, List<int[]> out, int k){
        if(n==null || out.size()>=k) return;
        dfsTopK(n.left, out, k);
        if(out.size()<k) out.add(new int[]{n.playerId, n.score});
        dfsTopK(n.right, out, k);
    }

    public static void main(String[] args){
        AVLLeaderboardSystem lb = new AVLLeaderboardSystem();
        lb.addOrUpdateScore(101, 2500);
        lb.addOrUpdateScore(102, 2600);
        lb.addOrUpdateScore(103, 2600);
        lb.addOrUpdateScore(104, 2400);
        lb.addOrUpdateScore(101, 2700);

        System.out.println("Rank(101): " + lb.getRank(101));
        System.out.println("Rank(103): " + lb.getRank(103)); 
        System.out.println("Top3:");
        for(int[] p: lb.topK(3)) System.out.println(p[0]+" -> "+p[1]);

        int[] k2 = lb.select(2);
        System.out.println("Select #2: player="+k2[0]+", score="+k2[1]);
    }
}
