import java.util.*;

public class LC27_RemoveElement_Recycle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int val = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        sc.close();

        int len = removeElement(arr, val);

        System.out.println(len);
        for (int i = 0; i < len; i++) {
            System.out.print(arr[i]);
            if (i < len - 1) System.out.print(" ");
        }
    }

    public static int removeElement(int[] nums, int val) {
        int write = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[write++] = nums[i];
            }
        }
        return write;
    }
}
