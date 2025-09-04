import java.util.Stack;

class Solution {
    /**
     * 計算最長有效括號子字串的長度。
     *
     * @param s 輸入字串，只包含 '(' 和 ')'。
     * @return 最長有效括號子字串的長度。
     */
    public int longestValidParentheses(String s) {
        int maxLength = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // 初始化棧，推入 -1 作為起始邊界

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                // 如果是左括號，將其索引推入棧中
                stack.push(i);
            } else { // 遇到右括號 ')'
                // 彈出棧頂元素
                stack.pop();
                if (stack.empty()) {
                    // 如果棧空了，表示當前的右括號沒有匹配的左括號，
                    // 則將當前索引作為新的邊界推入棧中
                    stack.push(i);
                } else {
                    // 棧不為空，表示找到了有效的括號對。
                    // 計算當前有效子字串的長度：當前索引 i 減去新的棧頂索引。
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }
        return maxLength;
    }
}