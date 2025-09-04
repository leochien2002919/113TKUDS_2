import java.util.*;

public class LC40_CombinationSum2_Procurement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] candidates = new int[n];
        for (int i = 0; i < n; i++) candidates[i] = sc.nextInt();
        sc.close();

        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        for (List<Integer> comb : res) {
            for (int x : comb) System.out.print(x + " ");
            System.out.println();
        }
    }

    private static void backtrack(int[] candidates, int remain, int start, List<Integer> path, List<List<Integer>> res) {
        if (remain == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) continue;
            if (candidates[i] > remain) break;
            path.add(candidates[i]);
            backtrack(candidates, remain - candidates[i], i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }
}
