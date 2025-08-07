import java.util.*;

public class AdvancedStringRecursion {

    public static void permute(String str, String path) {
        if (str.isEmpty()) {
            System.out.println(path);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            permute(str.substring(0, i) + str.substring(i + 1), path + str.charAt(i));
        }
    }

    public static boolean match(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean firstMatch = (!text.isEmpty() &&
                              (text.charAt(0) == pattern.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*')
            return match(text, pattern.substring(2)) ||
                   (firstMatch && match(text.substring(1), pattern));
        else
            return firstMatch && match(text.substring(1), pattern.substring(1));
    }

    public static String removeDuplicates(String s) {
        return removeHelper(s, "", new HashSet<>());
    }

    private static String removeHelper(String s, String res, Set<Character> seen) {
        if (s.isEmpty()) return res;
        char c = s.charAt(0);
        if (!seen.contains(c)) {
            seen.add(c);
            res += c;
        }
        return removeHelper(s.substring(1), res, seen);
    }

    public static void substrings(String s, String current) {
        if (s.isEmpty()) {
            if (!current.isEmpty()) System.out.println(current);
            return;
        }
        substrings(s.substring(1), current + s.charAt(0));
        substrings(s.substring(1), current);
    }
}
