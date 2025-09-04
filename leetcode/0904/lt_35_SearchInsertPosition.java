class Solution {
    /**
     * 在已排序陣列中搜尋目標值，若存在則返回索引，否則返回應插入的索引。
     *
     * @param nums 升序排列的整數陣列。
     * @param target 要搜尋的目標值。
     * @return 目標值的索引，或其應插入的索引。
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                // 如果找到目標值，直接返回其索引
                return mid;
            } else if (nums[mid] < target) {
                // 如果中間值小於目標，表示目標在右半邊
                left = mid + 1;
            } else {
                // 如果中間值大於目標，表示目標在左半邊
                right = mid - 1;
            }
        }
        
        // 循環結束後，left 指向的是目標值應插入的位置
        return left;
    }
}
