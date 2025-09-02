// 題目：Longest Common Prefix
// 給定一個字串陣列 strs，找出最長的公共前綴 (Longest Common Prefix)。
// 若沒有公共前綴，回傳空字串 ""。

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        // 假設第一個字串是初始前綴
        String prefix = strs[0];

        // 從第二個字串開始逐一比較
        for (int i = 1; i < strs.length; i++) {
            // 不斷縮短 prefix，直到當前字串以 prefix 開頭
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }

    // 測試用 main 方法
    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] strs1 = {"flower","flow","flight"};
        String[] strs2 = {"dog","racecar","car"};
        System.out.println(sol.longestCommonPrefix(strs1)); // 預期輸出：fl
        System.out.println(sol.longestCommonPrefix(strs2)); // 預期輸出：""
    }
}

/*
解題思路：
1. 最長公共前綴 (LCP) 的定義是：所有字串的前綴部分相同的最長子字串。
2. 假設第一個字串為 prefix，依序與後續字串比較。
3. 若當前字串不是以 prefix 開頭，就將 prefix 去掉最後一個字元，直到符合或變成空字串。
4. 若最後 prefix 變成空字串，代表沒有公共前綴。
5. 時間複雜度 O(n * m)，其中 n = 字串數量，m = 最長字串長度。
*/
