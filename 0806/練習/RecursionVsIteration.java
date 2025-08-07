public class RecursionVsIteration {

    public static int factorialR(int n) {
        if (n <= 1) return 1;
        return n * factorialR(n - 1);
    }

    public static int factorialI(int n) {
        int res = 1;
        for (int i = 2; i <= n; i++) res *= i;
        return res;
    }

    public static int productR(int[] arr, int idx) {
        if (idx == arr.length) return 1;
        return arr[idx] * productR(arr, idx + 1);
    }

    public static int productI(int[] arr) {
        int res = 1;
        for (int v : arr) res *= v;
        return res;
    }

    public static int vowelCountR(String s, int i) {
        if (i == s.length()) return 0;
        char c = Character.toLowerCase(s.charAt(i));
        return ("aeiou".indexOf(c) >= 0 ? 1 : 0) + vowelCountR(s, i + 1);
    }

    public static int vowelCountI(String s) {
        int count = 0;
        for (char c : s.toCharArray())
            if ("aeiouAEIOU".indexOf(c) >= 0) count++;
        return count;
    }

    public static boolean isBalanced(String s, int index, int count) {
        if (index == s.length()) return count == 0;
        if (count < 0) return false;
        if (s.charAt(index) == '(') return isBalanced(s, index + 1, count + 1);
        if (s.charAt(index) == ')') return isBalanced(s, index + 1, count - 1);
        return isBalanced(s, index + 1, count);
    }

    public static boolean isBalancedI(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') count++;
            else if (c == ')') count--;
            if (count < 0) return false;
        }
        return count == 0;
    }
}
