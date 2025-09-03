import java.util.Arrays;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        // Step 1: 對陣列進行排序，這是雙指標法的先決條件。
        // 排序後，我們可以更有效地控制和的大小，向右移動left會增加和，向左移動right會減少和。
        Arrays.sort(nums);

        // Step 2: 初始化最接近的和。
        // 這裡用一個非常大的數來初始化，或者用前三個元素的和來初始化也可以。
        // 為了確保初始值是有效的，我們使用前三個元素的和。
        int closestSum = nums[0] + nums[1] + nums[2];

        // Step 3: 遍歷陣列，固定第一個元素。
        // 迴圈到倒數第三個元素，因為我們需要至少三個元素來組成一個和。
        for (int i = 0; i < nums.length - 2; i++) {
            // Step 4: 設置雙指標。
            int left = i + 1;
            int right = nums.length - 1;

            // Step 5: 雙指標迴圈，尋找另外兩個元素。
            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];

                // Step 6: 檢查當前和與目標的差距，並更新最接近的和。
                // 如果當前的和與目標的絕對差值小於之前記錄的最小絕對差值，則更新 closestSum。
                if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
                    closestSum = currentSum;
                }

                // Step 7: 根據當前和與目標的比較結果來移動指標。
                if (currentSum < target) {
                    // 如果和太小，需要增加和，所以將左指標向右移動。
                    left++;
                } else if (currentSum > target) {
                    // 如果和太大，需要減少和，所以將右指標向左移動。
                    right--;
                } else {
                    // 如果和等於目標，表示已經找到完美解，直接返回。
                    // 根據題目假設，只有一個解，所以這一定是我們要找的。
                    return currentSum;
                }
            }
        }

        // Step 8: 返回最終找到的最接近的和。
        return closestSum;
    }
}
