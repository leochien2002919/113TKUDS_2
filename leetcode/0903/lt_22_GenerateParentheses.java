import java.util.ArrayList;
import java.util.List;

class Solution {
    private List<String> result = new ArrayList<>();
    private int n;

    public List<String> generateParenthesis(int n) {
        this.n = n;
        // Step 1: 從空字串和計數器為 0 開始，啟動回溯過程。
        backtrack(new StringBuilder(), 0, 0);
        return result;
    }

    /**
     * 回溯函數，用於遞迴地生成有效的括號組合。
     * @param currentString 目前為止所生成的括號字串。
     * @param openCount 已使用的左括號數量。
     * @param closeCount 已使用的右括號數量。
     */
    private void backtrack(StringBuilder currentString, int openCount, int closeCount) {
        // Step 2 (Base Case): 終止條件。當字串長度達到 2*n 時，表示已經找到一個完整的組合。
        if (currentString.length() == 2 * n) {
            result.add(currentString.toString());
            return;
        }

        // Step 3 (Recursive Step - Add Left Parenthesis):
        // 只有當已使用的左括號數量小於 n 時，我們才能添加一個左括號。
        if (openCount < n) {
            currentString.append('('); // 選擇
            backtrack(currentString, openCount + 1, closeCount); // 探索
            currentString.deleteCharAt(currentString.length() - 1); // 回溯
        }

        // Step 4 (Recursive Step - Add Right Parenthesis):
        // 只有當已使用的右括號數量小於左括號數量時，我們才能添加一個右括號。
        // 這是確保括號順序正確的關鍵條件。
        if (closeCount < openCount) {
            currentString.append(')'); // 選擇
            backtrack(currentString, openCount, closeCount + 1); // 探索
            currentString.deleteCharAt(currentString.length() - 1); // 回溯
        }
    }
}