// 題目：3Sum
// 給定一個整數陣列 nums，找出所有不重複的三元組 [nums[i], nums[j], nums[k]]，
// 使得 nums[i] + nums[j] + nums[k] == 0。

import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // 先排序，方便去重與雙指針操作
        Arrays.sort(nums);

        // 固定第一個數字 nums[i]，再用雙指針找另外兩個數字
        for (int i = 0; i < nums.length - 2; i++) {
            // 避免重複，若當前數字和前一個一樣，跳過
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 移動 left，並跳過重複的數字
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // 移動 right，並跳過重複的數字
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;  // 總和太小，左指針右移
                } else {
                    right--; // 總和太大，右指針左移
                }
            }
        }
        return result;
    }

    // 測試用 main 方法
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        int[] nums2 = {0, 1, 1};
        int[] nums3 = {0, 0, 0};

        System.out.println(sol.threeSum(nums1)); // 預期輸出：[[-1, -1, 2], [-1, 0, 1]]
        System.out.println(sol.threeSum(nums2)); // 預期輸出：[]
        System.out.println(sol.threeSum(nums3)); // 預期輸出：[[0, 0, 0]]
    }
}

/*
解題思路：
1. 題目要求找到所有三元組 (i, j, k)，使得 nums[i] + nums[j] + nums[k] == 0，且不重複。
2. 解法流程：
   - 先對 nums 排序，這樣相同數字會相鄰，方便去重。
   - 使用三層思路，但實際只需兩層：
     a. 第一層固定一個數字 nums[i]。
     b. 第二層使用雙指針 (left, right) 從兩側向內搜尋，直到找到符合條件的組合。
   - 若總和 < 0，移動 left（因為排序後增大數字）。
   - 若總和 > 0，移動 right（因為排序後減小數字）。
   - 若總和 == 0，加入解答，並略過重複的數字。
3. 為避免重複：
   - 固定 nums[i] 時，若與前一個相同，跳過。
   - 移動 left / right 時，若與前一個相同，也要跳過。
4. 時間複雜度 O(n^2)，因為排序 O(n log n) + 雙指針 O(n^2)。
   空間複雜度 O(1)（輸出不計）。
*/
