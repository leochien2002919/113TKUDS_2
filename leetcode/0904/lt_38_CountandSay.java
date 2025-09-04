class Solution {
    /**
     * Finds the nth term of the count-and-say sequence.
     *
     * @param n The positive integer representing the term to find.
     * @return The nth term as a string.
     */
    public String countAndSay(int n) {
        if (n == 1) {
            return "1"; // Base case
        }
        
        String s = "1";
        // Iterate from 2 to n to generate the sequence
        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            int count = 1;
            // Traverse the previous string to generate the new one
            for (int j = 1; j < s.length(); j++) {
                if (s.charAt(j) == s.charAt(j - 1)) {
                    // If characters are the same, increment the count
                    count++;
                } else {
                    // If characters are different, append the count and the character
                    sb.append(count).append(s.charAt(j - 1));
                    // Reset count for the new character
                    count = 1;
                }
            }
            // Append the count and the last character of the previous string
            sb.append(count).append(s.charAt(s.length() - 1));
            // Update the string for the next iteration
            s = sb.toString();
        }
        
        return s;
    }
}