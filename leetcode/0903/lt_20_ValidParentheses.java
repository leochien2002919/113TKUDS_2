import java.util.HashMap;
import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        // Step 1: 處理空字串的邊界情況。
        // 根據約束條件 s.length >= 1，但為了通用性，加入此檢查。
        if (s == null || s.length() == 0) {
            return true;
        }

        // 使用 Stack 來存儲遇到的左括號。
        Stack<Character> stack = new Stack<>();

        // 使用 HashMap 來建立括號的對應關係，方便比對。
        HashMap<Character, Character> bracketMap = new HashMap<>();
        bracketMap.put(')', '(');
        bracketMap.put('}', '{');
        bracketMap.put(']', '[');

        // Step 2: 遍歷輸入字串中的每個字元。
        for (char c : s.toCharArray()) {
            // Step 3: 如果是左括號，將其壓入堆疊。
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } 
            // Step 4: 如果是右括號。
            else if (c == ')' || c == '}' || c == ']') {
                // 檢查堆疊是否為空。如果為空，表示沒有對應的左括號，直接返回 false。
                if (stack.isEmpty()) {
                    return false;
                }
                
                // 彈出堆疊頂部的元素，並與當前右括號的對應左括號進行比對。
                char topElement = stack.pop();
                if (topElement != bracketMap.get(c)) {
                    // 如果不匹配，返回 false。
                    return false;
                }
            }
        }

        // Step 5: 遍歷結束後，檢查堆疊是否為空。
        // 如果堆疊為空，表示所有左括號都找到了對應的右括號，字串有效。
        // 如果堆疊不為空，表示有左括號沒有被關閉，字串無效。
        return stack.isEmpty();
    }
}