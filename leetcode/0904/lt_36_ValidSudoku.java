import java.util.HashSet;
import java.util.Set;

class Solution {
    /**
     * 檢查一個 9x9 的數獨棋盤是否有效。
     * 只需驗證已填入的數字是否符合規則，'.'(空) 則忽略。
     *
     * @param board 9x9 的字元陣列，代表數獨棋盤。
     * @return 如果棋盤有效，返回 true；否則返回 false。
     */
    public boolean isValidSudoku(char[][] board) {
        // 使用三個 Set 數組來追蹤每個數字在行、列和子九宮格中的出現情況
        // rows[i] 追蹤第 i 行的數字
        // cols[j] 追蹤第 j 列的數字
        // boxes[k] 追蹤第 k 個 3x3 子九宮格的數字
        Set<Character>[] rows = new HashSet[9];
        Set<Character>[] cols = new HashSet[9];
        Set<Character>[] boxes = new HashSet[9];

        // 初始化所有 Set
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        // 遍歷整個棋盤
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char cell = board[i][j];

                // 如果是 '.' (空)，則跳過
                if (cell == '.') {
                    continue;
                }

                // 計算當前單元格所在的 3x3 子九宮格的索引
                int boxIndex = (i / 3) * 3 + (j / 3);

                // 檢查當前數字是否已在同一行、同一列或同一子九宮格中出現
                if (rows[i].contains(cell) || cols[j].contains(cell) || boxes[boxIndex].contains(cell)) {
                    // 如果已出現，則棋盤無效
                    return false;
                }

                // 如果是新數字，則將其添加到對應的 Set 中
                rows[i].add(cell);
                cols[j].add(cell);
                boxes[boxIndex].add(cell);
            }
        }

        // 如果遍歷完成沒有發現重複，則棋盤有效
        return true;
    }
}