import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class Solution {
    /**
     * Finds all unique combinations of candidates that sum up to the target.
     *
     * @param candidates An array of distinct integers.
     * @param target The target integer.
     * @return A list of all unique combinations.
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // Sorting the candidates helps with potential optimizations and can make the process more predictable.
        Arrays.sort(candidates); 
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    /**
     * The recursive backtracking helper function.
     *
     * @param result The list to store all valid combinations.
     * @param tempList The temporary list for the current combination.
     * @param candidates The array of candidate numbers.
     * @param remain The remaining target sum to reach.
     * @param start The starting index in the candidates array for the current search.
     */
    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] candidates, int remain, int start) {
        // Base case 1: A valid combination is found.
        if (remain == 0) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        // Base case 2: The sum exceeds the target.
        if (remain < 0) {
            return;
        }

        // Recursive step: Iterate through candidates.
        for (int i = start; i < candidates.length; i++) {
            // Choose: Add the current candidate to the temporary list.
            tempList.add(candidates[i]);
            
            // Explore: Recursively call the function with the updated target.
            // We pass 'i' (not 'i + 1') to allow for the reuse of the same number.
            backtrack(result, tempList, candidates, remain - candidates[i], i);
            
            // Backtrack: Remove the last element to explore other possibilities.
            tempList.remove(tempList.size() - 1);
        }
    }
}