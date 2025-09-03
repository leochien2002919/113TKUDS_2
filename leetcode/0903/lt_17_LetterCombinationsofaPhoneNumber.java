import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

class Solution {

    // 使用一個 Map 來儲存數字到字母的映射關係，這比 if-else 語句更清晰。
    private Map<Character, String> phoneMap = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};

    private List<String> result = new ArrayList<>();
    private String digits;

    public List<String> letterCombinations(String digits) {
        // Step 1: 處理邊界情況。如果輸入字串為空，直接返回空列表。
        if (digits == null || digits.length() == 0) {
            return result;
        }

        this.digits = digits;
        
        // Step 2: 呼叫回溯函數，從索引 0 和空字串開始。
        backtrack(0, new StringBuilder());
        
        return result;
    }

    /**
     * 回溯函數，用於遞迴生成所有組合。
     * @param index 當前正在處理的 digits 字符串中的索引。
     * @param path  目前為止所生成的字母組合。
     */
    private void backtrack(int index, StringBuilder path) {
        // Step 3 (Base Case): 遞迴的終止條件。當路徑長度等於輸入數字字符串的長度時，
        // 表示我們已經生成了一個完整的組合。將其加入結果列表。
        if (path.length() == digits.length()) {
            result.add(path.toString());
            return;
        }

        // Step 4 (Recursive Step): 獲取當前數字對應的字母。
        char digit = digits.charAt(index);
        String letters = phoneMap.get(digit);

        // 遍歷當前數字的所有字母。
        for (int i = 0; i < letters.length(); i++) {
            char letter = letters.charAt(i);

            // 選擇：將當前字母添加到路徑中。
            path.append(letter);

            // 探索：遞迴呼叫，處理下一個數字。
            backtrack(index + 1, path);

            // 回溯：從路徑中移除最後一個字母，以便探索下一個可能性。
            path.deleteCharAt(path.length() - 1);
        }
    }
}