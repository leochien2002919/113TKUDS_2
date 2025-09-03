import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        // 這個列表將儲存所有有效子字串的起始索引。
        List<Integer> ans = new ArrayList<>();

        // 處理邊界情況，如果 words 或 s 為空，直接返回空列表。
        if (words.length == 0 || s.length() == 0) {
            return ans;
        }

        int wordSize = words[0].length();
        int wordCount = words.length;
        int N = s.length();

        // 步驟 1: 建立 words 陣列中所有單字的頻率表。
        // 這個表是我們用來檢查是否找到有效組合的參考。
        HashMap<String, Integer> originalCount = new HashMap<>();
        for (String word : words) {
            originalCount.put(word, originalCount.getOrDefault(word, 0) + 1);
        }

        // 步驟 2: 透過 wordSize 建立多個獨立的滑動視窗。
        // 這是解決方案的關鍵優化，以確保不會錯過任何對齊。
        for (int offset = 0; offset < wordSize; offset++) {
            // 為每個獨立的滑動視窗建立一個臨時的頻率表。
            HashMap<String, Integer> currentCount = new HashMap<>();
            int start = offset;
            int count = 0;
            
            // 步驟 3: 在每個「賽道」上執行滑動視窗。
            for (int end = offset; end + wordSize <= N; end += wordSize) {
                String currWord = s.substring(end, end + wordSize);

                // 如果當前單字存在於原始單字表中...
                if (originalCount.containsKey(currWord)) {
                    currentCount.put(currWord, currentCount.getOrDefault(currWord, 0) + 1);
                    count++;

                    // 如果當前單字的數量超出了應有的數量，縮小視窗。
                    while (currentCount.get(currWord) > originalCount.get(currWord)) {
                        String startWord = s.substring(start, start + wordSize);
                        currentCount.put(startWord, currentCount.get(startWord) - 1);
                        start += wordSize;
                        count--;
                    }

                    // 如果視窗內單字數量等於總單字數，表示找到了一個有效的組合。
                    if (count == wordCount) {
                        ans.add(start);
                    }
                } else {
                    // 如果單字無效，重置視窗並重新開始。
                    count = 0;
                    start = end + wordSize;
                    currentCount.clear();
                }
            }
        }
        return ans;
    }
}