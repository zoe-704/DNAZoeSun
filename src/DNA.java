/**
 * DNA
 * <p>
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *</p>
 * <p>
 * Completed by: Zoe Sun
 *</p>
 */

public class DNA {
    static final int RADIX = 4; // only 4 letters, would be 256 otherwise
    static final long P = 543_211_019;

    // Creates hash using horner's method
    public static long hash(String s, int start, int n) {
        long h = 0;
        for (int i = start; i < start + n; i++) {
            h = (h * RADIX + s.charAt(i)) % P;
        }
        return h;
    }

    public static int STRCount(String sequence, String STR) {
        int seqLength = sequence.length(), strLength = STR.length();
        if (strLength > seqLength) return 0; //  STR cannot be longer than the DNA sequence
        // Initialize counters and index to 0
        int maxCount = 0, count = 0, i = 0;

        // Hash STR and first subsequence of length strLength
        long strHash = hash(STR, 0, strLength),
                seqHash = hash(sequence, 0, strLength);

        // Iterate through the entire sequence
        while (i < seqLength) {
            // Match: increment counter and go to next consecutive candidate
            if (strHash == seqHash) {
                count++;
                i += strLength;
            }
            // No match: update counters and increment index by 1
            else {
                maxCount = Math.max(count, maxCount);
                count = 0;
                i++;
            }
            // Rehash for current subsequence
            if (i + strLength < seqLength) {
                seqHash = hash(sequence, i, strLength);
            }
            maxCount = Math.max(maxCount, count); // Update max
        }
        return maxCount;
    }
}

        /* DP approach
        int dp[] = new int[seqLength], maxCount = 0;
        // Iterate backwards and check if an STR sequence starts at index i
        for (int i = seqLength - strLength; i >= 0; i--) {
            if (sequence.substring(i, i + strLength).equals(STR)) {
                if (i + strLength < seqLength) {
                    dp[i] = dp[i + strLength] + 1; // increment counter of consecutive str sequences
                } else dp[i] = 1;
                maxCount = Math.max(maxCount, dp[i]);
            }
        }
        return maxCount;

    }
}
*/