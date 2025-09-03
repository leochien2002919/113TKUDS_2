class Solution {
    public int strStr(String haystack, String needle) {
        int h = haystack.length();
        int n = needle.length();

        // Iterate through all possible starting positions in haystack.
        for (int i = 0; i <= h - n; i++) {
            // Check if the substring of haystack, starting at 'i' and with
            // length 'n', matches the entire needle.
            if (haystack.substring(i, i + n).equals(needle)) {
                return i; // Found a match, return the starting index.
            }
        }

        // If the loop completes without finding a match, return -1.
        return -1;
    }
}