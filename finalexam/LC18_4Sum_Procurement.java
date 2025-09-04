import java.util.*;

public class LC18_4Sum_Procurement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long target = sc.nextLong();
        long[] nums = new long[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextLong();
        sc.close();

        Arrays.sort(nums);

        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                int L = j + 1, R = n - 1;
                while (L < R) {
                    long sum = nums[i] + nums[j] + nums[L] + nums[R];
                    if (sum == target) {
                        System.out.println(nums[i] + " " + nums[j] + " " + nums[L] + " " + nums[R]);

                        long leftVal = nums[L], rightVal = nums[R];
                        while (L < R && nums[L] == leftVal) L++;
                        while (L < R && nums[R] == rightVal) R--;
                    } else if (sum < target) {
                        L++;
                    } else {
                        R--;
                    }
                }
            }
        }
    }
}
