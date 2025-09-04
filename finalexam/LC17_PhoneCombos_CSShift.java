import java.util.*;

public class LC17_PhoneCombos_CSShift {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String digits = sc.hasNextLine() ? sc.nextLine().trim() : "";

        if (digits.length() == 0) return;

        String[] map = {
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz"
        };

        StringBuilder path = new StringBuilder();
        dfs(0, digits, path, map);
    }

    private static void dfs(int idx, String digits, StringBuilder path, String[] map) {
        if (idx == digits.length()) {
            System.out.println(path.toString());
            return;
        }

        char d = digits.charAt(idx);
        if (d < '2' || d > '9') return;

        String letters = map[d - '2'];
        for (int i = 0; i < letters.length(); i++) {
            path.append(letters.charAt(i));
            dfs(idx + 1, digits, path, map);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
