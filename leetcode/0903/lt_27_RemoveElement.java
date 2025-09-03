class Solution {
    public int removeElement(int[] nums, int val) {
        // Pointer 'i' tracks the position for the next non-val element.
        int i = 0;
        
        // Pointer 'j' iterates through the entire array.
        for (int j = 0; j < nums.length; j++) {
            // If the element at 'j' is not equal to the value to be removed,
            // we keep it and move it to position 'i'.
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        
        // The final value of 'i' is the count of elements not equal to val.
        return i;
    }
}
