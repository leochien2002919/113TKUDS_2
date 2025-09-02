// 題目：Integer to Roman
// 給定一個整數 num (1 <= num <= 3999)，轉換成對應的羅馬數字字串。

class Solution {
    public String intToRoman(int num) {
        // 定義所有羅馬數字與對應的數值
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {
            "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
        };

        StringBuilder sb = new StringBuilder();

        // 從最大值開始嘗試，直到 num 被完全轉換
        for (int i = 0; i < values.length && num > 0; i++) {
            while (num >= values[i]) {
                num -= values[i];       // 減去對應的值
                sb.append(symbols[i]);  // 加上羅馬數字
            }
        }
        return sb.toString();
    }

    // 測試用 main 方法
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.intToRoman(3749)); // 預期輸出：MMMDCCXLIX
        System.out.println(sol.intToRoman(58));   // 預期輸出：LVIII
        System.out.println(sol.intToRoman(1994)); // 預期輸出：MCMXCIV
    }
}

/*
解題思路：
1. 羅馬數字的規則：
   - 一般由大到小拼接，例如 3000 = "MMM"。
   - 特殊數字採用「減法表示法」，如 4 = "IV"，9 = "IX"，40 = "XL"，90 = "XC"，400 = "CD"，900 = "CM"。
2. 先定義所有可能出現的數字與羅馬符號對應表，並按由大到小排序。
3. 從最大值開始，若 num >= 對應值，就持續減去並拼接符號。
4. 直到 num 減為 0 為止，拼接結果即為答案。
5. 時間複雜度 O(1)，因為最大數字固定為 3999，最多處理的迴圈步數有限。
*/
