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

        int maxCount = 0, count = 0;
        // hash for STR and first part of sequence
        long strHash = hash(STR, 0, strLength), seqHash = hash(sequence, 0, strLength);

        // Iterate through the entire sequence
        for (int i = 0; i <= seqLength - strLength; i++) {
            // Increment counter if subsequence and STR hashes match
            if (strHash == seqHash) {
                count++;
                maxCount = Math.max(maxCount, count);
                i += strLength-1; // move to next candidate
                if (i + strLength < seqLength) {
                    seqHash = hash(sequence, i + 1, strLength);
                }
            } else { // Subsequence and STR hashes do not match
                // Remove first letter and add letter to subsequence hash
                count = 0;
                long first = (long)Math.pow(RADIX, strLength - 1);
                if (i + strLength < seqLength) {
                    seqHash = ((seqHash - sequence.charAt(i) * first % P + P) % P);
                    seqHash = (seqHash * RADIX + sequence.charAt(i + strLength)) % P;
                }

                //seqHash = ((seqHash - sequence.charAt(i) * first % P + P) % P);
                //seqHash = (seqHash * RADIX + sequence.charAt(i + strLength)) % P;

                //seqHash = (seqHash + P) - (sequence.charAt(i) * first % P) % P;
                //seqHash = (seqHash * RADIX + sequence.charAt(i + strLength)) % P;
            }
        }
        return maxCount;
    }
}

    /*
            long curHash = hash(sequence.substring(j, j + strLength), strLength);;
            // begin checking for consecutive appearances
            while (j + strLength <= seqLength && curHash == strHash) {
                count++;
                j += strLength;
                // Update hash for next substring window
                if (j + strLength <= seqLength) {
                    curHash = (curHash + P - sequence.charAt(j - strLength) * RM % P) % P;
                    curHash = (curHash * RADIX + sequence.charAt(j + strLength - 1)) % P;
                }
            }
            maxCount = Math.max(maxCount, count);

            // Move rolling window by 1 character for outer loop
            if (i + strLength < seqLength) {
                seqHash = (seqHash + P - sequence.charAt(i) * RM % P) % P;
                seqHash = (seqHash * RADIX + sequence.charAt(i + strLength)) % P;
            }

        }
        /* DP approach
        int dp[] = new int[seqLength], maxCount = 0;
        // iterate backwards and check if an STR sequence starts at index i
        for (int i = seqLength - strLength; i >= 0; i--) {
            if (sequence.substring(i, i + strLength).equals(STR)) {
                if (i + strLength < seqLength) {
                    dp[i] = dp[i + strLength] + 1; // increment counter of consecutive str sequences
                } else dp[i] = 1;
                maxCount = Math.max(maxCount, dp[i]);
            }
        }
        */
    /*
        return maxCount;

    }
}
*/