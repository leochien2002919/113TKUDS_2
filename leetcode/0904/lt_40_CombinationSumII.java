import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // Step 1: Sort the candidates to group duplicate numbers together.
        Arrays.sort(candidates);
        
        // Start the backtracking process from the first element (index 0).
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    /**
     * The recursive backtracking helper function.
     *
     * @param result The list to store all valid combinations.
     * @param tempList The temporary list for the current combination.
     * @param candidates The array of candidate numbers.
     * @param remain The remaining sum to reach the target.
     * @param start The starting index for the current search.
     */
    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] candidates, int remain, int start) {
        // Base case 1: Found a valid combination.
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
            // Step 2: Skip duplicates.
            // This condition prevents duplicate combinations by ensuring we only
            // pick the first occurrence of a number at a given recursion level.
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            // Pruning: If the current number is already greater than the remaining target,
            // we can stop searching this branch because the array is sorted.
            if (candidates[i] > remain) {
                break;
            }

            // Choose: Add the current candidate to the temporary list.
            tempList.add(candidates[i]);
            
            // Explore: Recursively call for the next number.
            // Pass 'i + 1' to ensure each element is used only once.
            backtrack(result, tempList, candidates, remain - candidates[i], i + 1);
            
            // Backtrack: Remove the last element to try the next possibility.
            tempList.remove(tempList.size() - 1);
        }
    }
}