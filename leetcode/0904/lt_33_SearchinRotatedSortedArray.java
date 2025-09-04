class Solution {
    /**
     * 在一個旋轉排序數組中搜索目標值。
     *
     * @param nums 旋轉排序數組。
     * @param target 要搜索的目標值。
     * @return 如果找到目標值，返回其索引；否則返回 -1。
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // 判斷左半邊是否是有序的
            if (nums[left] <= nums[mid]) {
                // 如果目標值在左半邊的範圍內
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    // 目標值不在左半邊，則在右半邊
                    left = mid + 1;
                }
            } else { // 右半邊是有序的
                // 如果目標值在右半邊的範圍內
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    // 目標值不在右半邊，則在左半邊
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}