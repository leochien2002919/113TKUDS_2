class Solution {
    // 使用三個 boolean 二維陣列來追蹤數字在行、列和 3x3 子九宮格中的使用情況
    private boolean[][] rowsUsed = new boolean[9][10];
    private boolean[][] colsUsed = new boolean[9][10];
    private boolean[][] boxesUsed = new boolean[9][10];

    /**
     * 解決數獨問題。此方法為回溯法的入口點。
     *
     * @param board 9x9 的字元陣列，代表數獨棋盤。
     */
    public void solveSudoku(char[][] board) {
        // 1. 預處理：遍歷初始棋盤，將已有的數字標記為已使用
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    int boxIndex = (i / 3) * 3 + j / 3;
                    
                    rowsUsed[i][num] = true;
                    colsUsed[j][num] = true;
                    boxesUsed[boxIndex][num] = true;
                }
            }
        }
        
        // 2. 開始回溯搜索，從 (0, 0) 位置開始
        backtrack(board, 0, 0);
    }
    
    /**
     * 回溯法的核心遞歸函數。
     *
     * @param board 當前數獨棋盤狀態。
     * @param row 當前正在處理的行索引。
     * @param col 當前正在處理的列索引。
     * @return 如果找到一個有效的解，返回 true；否則返回 false。
     */
    private boolean backtrack(char[][] board, int row, int col) {
        // 遞歸終止條件：如果 row == 9，表示已成功處理完所有行，找到一個有效解
        if (row == 9) {
            return true;
        }
        
        // 計算下一個要處理的單元格的索引
        int nextRow = col == 8 ? row + 1 : row;
        int nextCol = col == 8 ? 0 : col + 1;
        
        // 如果當前單元格已經有數字，則直接跳到下一個單元格
        if (board[row][col] != '.') {
            return backtrack(board, nextRow, nextCol);
        }
        
        // 如果單元格為空，嘗試填入數字 1 到 9
        for (int num = 1; num <= 9; num++) {
            char val = (char) (num + '0');
            int boxIndex = (row / 3) * 3 + col / 3;

            // 檢查當前數字是否在行、列和子九宮格中都未使用
            if (!rowsUsed[row][num] && !colsUsed[col][num] && !boxesUsed[boxIndex][num]) {
                // 1. 選擇 (Choose)：將數字填入單元格
                board[row][col] = val;
                // 更新追蹤數組的狀態
                rowsUsed[row][num] = true;
                colsUsed[col][num] = true;
                boxesUsed[boxIndex][num] = true;
                
                // 2. 探索 (Explore)：遞歸地解決下一個單元格
                if (backtrack(board, nextRow, nextCol)) {
                    // 如果遞歸調用成功（即找到了有效解），則直接返回 true
                    return true;
                }
                
                // 3. 回溯 (Backtrack)：如果遞歸失敗，撤銷當前的選擇
                board[row][col] = '.';
                // 恢復追蹤數組的狀態
                rowsUsed[row][num] = false;
                colsUsed[col][num] = false;
                boxesUsed[boxIndex][num] = false;
            }
        }
        
        // 如果數字 1-9 都無法填入此單元格，則返回 false，觸發上一層的回溯
        return false;
    }
}