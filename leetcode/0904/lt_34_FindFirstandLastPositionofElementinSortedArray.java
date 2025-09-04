class Solution {
    public int[] searchRange(int[] nums, int target) {
        // 使用兩個 helper function 分別尋找起始和結束位置
        int firstOccurrence = findFirst(nums, target);
        int lastOccurrence = findLast(nums, target);
        
        return new int[]{firstOccurrence, lastOccurrence};
    }

    /**
     * Helper function to find the first (leftmost) occurrence of the target.
     * 尋找目標值的最左邊索引
     */
    private int findFirst(int[] nums, int target) {
        int index = -1;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] >= target) {
                // 如果 nums[mid] >= target，表示目標值可能在左邊，
                // 或 mid 就是第一個出現的位置。
                // 所以我們繼續向左搜索。
                right = mid - 1;
            } else {
                // 如果 nums[mid] < target，目標值一定在右邊。
                left = mid + 1;
            }

            // 如果找到目標值，先記錄下可能的答案，然後繼續向左搜索
            if (nums[mid] == target) {
                index = mid;
            }
        }
        return index;
    }

    /**
     * Helper function to find the last (rightmost) occurrence of the target.
     * 尋找目標值的最右邊索引
     */
    private int findLast(int[] nums, int target) {
        int index = -1;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] <= target) {
                // 如果 nums[mid] <= target，表示目標值可能在右邊，
                // 或 mid 就是最後一個出現的位置。
                // 所以我們繼續向右搜索。
                left = mid + 1;
            } else {
                // 如果 nums[mid] > target，目標值一定在左邊。
                right = mid - 1;
            }

            // 如果找到目標值，先記錄下可能的答案，然後繼續向右搜索
            if (nums[mid] == target) {
                index = mid;
            }
        }
        return index;
    }
}