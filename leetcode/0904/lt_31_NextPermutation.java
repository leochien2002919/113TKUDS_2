import java.util.Arrays;
import java.util.Collections;

public class Solution { // Changed class name from NextPermutation to Solution

    /**
     * Finds the next lexicographically greater permutation of the given array.
     * The replacement must be in place and use only constant extra memory.
     *
     * @param nums The array of integers to be permuted.
     */
    public void nextPermutation(int[] nums) {
        // Step 1: 从右向左找到第一个非递增的数字
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // Step 2: 如果找到了这样的数字 (i >= 0)，则从右向左找到第一个比 nums[i] 大的数字
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            // 交换 nums[i] 和 nums[j]
            swap(nums, i, j);
        }

        // Step 3: 反转从 i+1 到阵列结尾的部分
        // 这是为了将剩余的数字排列成最小的顺
        reverse(nums, i + 1);
    }

    /**
     * Helper method to swap two elements in an array.
     *
     * @param nums The array.
     * @param i    Index of the first element.
     * @param j    Index of the second element.
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * Helper method to reverse a portion of an array.
     *
     * @param nums  The array.
     * @param start The starting index for the reversal.
     */
    private void reverse(int[] nums, int start) {
        int i = start;
        int j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
}