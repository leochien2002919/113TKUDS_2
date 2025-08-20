import java.io.*;
import java.util.*;

/*
 * Time Complexity: O(log(min(a, b)))
 * 遞迴歐幾里得算法，每次遞迴將其中一個數減小為餘數，
 * 最多進行 O(log(min(a,b))) 次。
 * Space Complexity: O(log(min(a, b)))
 */

public class M05_GCD_LCM_Recursive {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        long g = gcd(a, b);
        long l = (a / g) * b;
        System.out.println("GCD: " + g);
        System.out.println("LCM: " + l);
    }

    private static long gcd(long x, long y) {
        if (y == 0) return x;
        return gcd(y, x % y);
    }
}
