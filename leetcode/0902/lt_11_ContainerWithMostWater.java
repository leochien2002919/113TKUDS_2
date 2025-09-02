// 題目：盛最多水的容器
// 給定一個長度為 n 的整數陣列 height，代表直方圖的高度。
// 找到兩條線，與 x 軸圍成的容器能儲存最多的水，並回傳最大水量。

class Solution {
    public int maxArea(int[] height) {
        // 使用雙指針法，初始指針在陣列兩端
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        // 當左右指針未相遇時持續迴圈
        while (left < right) {
            // 計算當前容器的高度（由較短的那根柱子決定）
            int minHeight = Math.min(height[left], height[right]);
            // 計算容器的寬度（兩指針之間的距離）
            int width = right - left;
            // 更新最大水量
            maxArea = Math.max(maxArea, minHeight * width);

            // 移動較短的指針，嘗試找到更高的柱子以獲得更大容積
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    // 測試 main 函式
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] height1 = {1,8,6,2,5,4,8,3,7};
        int[] height2 = {1,1};
        System.out.println(sol.maxArea(height1)); // 預期輸出：49
        System.out.println(sol.maxArea(height2)); // 預期輸出：1
    }
}

/*
解題思路：
1. 題目要求找出兩條直線，與 x 軸形成容器，能容納最多的水。
2. 容積公式為：min(左線高, 右線高) × (右指針 - 左指針)。
3. 為避免 O(n^2) 的暴力解法，使用「雙指針」：
   - 左指針在開頭，右指針在結尾。
   - 每次計算容積，更新最大值。
   - 移動較短的那條線的指針，因為移動較長的線不可能增加容積。
4. 時間複雜度 O(n)，空間複雜度 O(1)。
*/
