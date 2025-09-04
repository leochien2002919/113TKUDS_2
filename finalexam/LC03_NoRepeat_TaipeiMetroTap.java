import java.util.*;

public class LC03_NoRepeat_TaipeiMetroTap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.hasNext() ? sc.next() : "";

        Map<Character, Integer> lastIndex = new HashMap<>();
        int left = 0, maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            if (lastIndex.containsKey(c)) {
                left = Math.max(left, lastIndex.get(c) + 1);
            }

            lastIndex.put(c, right);

            maxLen = Math.max(maxLen, right - left + 1);
        }

        System.out.println(maxLen);
    }
}
