// 題目：Roman to Integer
// 給定一個有效的羅馬數字字串 s (1 <= s.length <= 15)，將它轉換為對應的整數。

import java.util.*;

class Solution {
    public int romanToInt(String s) {
        // 建立符號與數值對應表
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int total = 0;

        // 從左到右掃描字串
        for (int i = 0; i < s.length(); i++) {
            int value = map.get(s.charAt(i));

            // 如果當前數字比下一個小，代表要用減法（如 IV = 4）
            if (i + 1 < s.length() && value < map.get(s.charAt(i + 1))) {
                total -= value;
            } else {
                total += value;
            }
        }
        return total;
    }

    // 測試用 main 方法
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.romanToInt("III"));      // 預期輸出：3
        System.out.println(sol.romanToInt("LVIII"));    // 預期輸出：58
        System.out.println(sol.romanToInt("MCMXCIV"));  // 預期輸出：1994
    }
}

/*
解題思路：
1. 羅馬數字通常是由大到小排列，相加即可。
   例如：LVIII = L(50) + V(5) + III(3) = 58。
2. 但遇到「小數字在大數字前面」的情況，要做減法：
   - IV = 4（5 - 1）
   - IX = 9（10 - 1）
   - XL = 40（50 - 10）
   - XC = 90（100 - 10）
   - CD = 400（500 - 100）
   - CM = 900（1000 - 100）
3. 解法：
   - 建立對照表：I=1, V=5, X=10, L=50, C=100, D=500, M=1000。
   - 從左到右遍歷字串：
     - 若當前值 < 下一個值 → 減去當前值。
     - 否則 → 加上當前值。
4. 時間複雜度 O(n)，其中 n = s.length (最多 15)，非常快。
*/
