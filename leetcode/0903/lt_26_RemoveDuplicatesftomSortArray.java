class Solution {
    public int removeDuplicates(int[] nums) {
        // Handle edge case for an empty array.
        if (nums.length == 0) {
            return 0;
        }

        // Pointer 'i' tracks the position for the next unique element.
        int i = 0; 
        
        // Pointer 'j' iterates through the entire array.
        for (int j = 1; j < nums.length; j++) {
            // Compare the elements at pointers 'i' and 'j'.
            if (nums[i] != nums[j]) {
                // If they are different, we have found a new unique element.
                // Increment 'i' to the next position for the new unique element.
                i++;
                // Place the new unique element at the 'i' position.
                nums[i] = nums[j];
            }
        }
        
        // The number of unique elements is 'i + 1'.
        return i + 1;
    }
}