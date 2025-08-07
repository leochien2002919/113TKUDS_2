import java.util.*;

public class TicTacToeBoard {
    private char[][] board = new char[3][3];

    public TicTacToeBoard() {
        for (char[] row : board) Arrays.fill(row, ' ');
    }

    public boolean placeMark(int row, int col, char player) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ')
            return false;
        board[row][col] = player;
        return true;
    }

    public boolean checkWin(char player) {
        for (int i = 0; i < 3; i++)
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player))
                return true;
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
               (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    public boolean isFull() {
        for (char[] row : board)
            for (char cell : row)
                if (cell == ' ') return false;
        return true;
    }

    public void printBoard() {
        for (char[] row : board)
            System.out.println(Arrays.toString(row));
    }
}
