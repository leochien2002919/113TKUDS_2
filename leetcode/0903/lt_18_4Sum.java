import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // 存放最終結果的列表
        List<List<Integer>> result = new ArrayList<>();
        
        // Step 1: 排序陣列，這是使用雙指標法的關鍵前提。
        Arrays.sort(nums);
        int n = nums.length;

        // Step 2: 遍歷陣列，固定第一個數字。
        // 迴圈到 n-3 是因為需要至少四個元素。
        for (int i = 0; i < n - 3; i++) {
            // 去重處理：如果當前數字與前一個數字相同，跳過以避免重複的四元組。
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            // Step 3: 遍歷陣列，固定第二個數字。
            // 迴圈從 i+1 開始，以確保四個數字是不同的。
            for (int j = i + 1; j < n - 2; j++) {
                // 去重處理：如果當前數字與前一個數字相同，跳過。
                if (j > i + 1 && nums[j] == nums[j-1]) {
                    continue;
                }

                // Step 4: 設置雙指標。
                int left = j + 1;
                int right = n - 1;

                // Step 5: 雙指標迴圈，尋找剩下的兩個數字。
                while (left < right) {
                    // 使用 long 型別來計算和，以避免整數溢位。
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        // 找到一個符合條件的四元組，將其加入結果。
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // 移動指標，並處理重複的數字，以確保四元組的唯一性。
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }

                        // 移動到下一個不同的數字。
                        left++;
                        right--;
                    } else if (sum < target) {
                        // 如果和太小，需要增加它，向右移動 left 指標。
                        left++;
                    } else {
                        // 如果和太大，需要減少它，向左移動 right 指標。
                        right--;
                    }
                }
            }
        }

        return result;
    }
}