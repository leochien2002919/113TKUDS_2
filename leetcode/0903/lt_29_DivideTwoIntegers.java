class Solution {
    public int divide(int dividend, int divisor) {
        // Handle the overflow edge case for 32-bit signed integers.
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Determine the sign of the result.
        boolean isNegative = (dividend < 0) ^ (divisor < 0);

        // Convert to long to handle the edge case of Integer.MIN_VALUE.
        long ldividend = Math.abs((long) dividend);
        long ldivisor = Math.abs((long) divisor);

        long quotient = 0;

        while (ldividend >= ldivisor) {
            // Find the largest power of 2 for which ldivisor * 2^p <= ldividend.
            long tempDivisor = ldivisor;
            long multiple = 1;

            while (ldividend >= (tempDivisor << 1)) {
                tempDivisor <<= 1;
                multiple <<= 1;
            }

            // Subtract this largest multiple of the divisor from the dividend.
            ldividend -= tempDivisor;
            // Add the corresponding multiple to the quotient.
            quotient += multiple;
        }

        // Apply the correct sign and check for overflow.
        if (isNegative) {
            return (int) -quotient;
        } else {
            // The problem statement ensures the quotient fits within the integer range,
            // with the exception of the MIN_VALUE / -1 case, which is already handled.
            return (int) quotient;
        }
    }
}