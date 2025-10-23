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
    static final int R = 256;
    static final long p = (long) 543211019;

    // create a hash set
    public static long hash(String s, int n) {
        long h = 0;
        for (int i = 0; i < n; i++) {
            h = (h * R + s.charAt(i) % p);
        }
        return h;
    }

    public static int STRCount(String sequence, String STR) {
        int seqLength = sequence.length(), strLength = STR.length();
        if (strLength > seqLength) return 0; //  STR cannot be longer than the DNA sequence

        int maxCount = 0;
        long strHash = hash(STR, strLength),
                seqHash = hash(sequence.substring(0, strLength), strLength);

        long[] array = new long[seqLength/strLength + 1];
        array[0] = seqHash;
        for (int i = strLength; i < seqLength; i++) {
            int count = 0;
            // begin checking for consecutive appearances
            while (strHash == seqHash) {
                count++;
                // subtract first*R^(m-1) modded
                long r_pow = 1;
                for (int j = 0; j < i-1; j++) r_pow = r_pow*R%p;
                long new_hash = (long) ((seqHash + p) - sequence.charAt(i - strLength) * r_pow % p) % p;
                    seqHash = ((new_hash * R) + sequence.charAt(i)) % p;
            }
            maxCount = Math.max(maxCount, count);
            if (array[i-strLength+1] == 0) {
                array[i-strLength+1] = hash(sequence.substring(1 + (i - strLength), i+1), strLength);
            }
            seqHash = array[i-strLength+1];
        }


         /*
         DP

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


        BASIC
        int maxCount = 0;
        int i = 0;
        while (i <= seqLength - strLength) {
            int count = 0;
            while (i + strLength <= seqLength && sequence.substring(i, i + strLength).equals(STR)) {
                count++;
                i += strLength;
            }
            i -= strLength;
            maxCount= Math.max(maxCount, count);
            i++;
        }
        /*
        for (int i = 0; i <= seqLength - strLength; i++) {
            int count = 0, j = i;
            while (j + strLength <= seqLength && sequence.substring(j, j + strLength).equals(STR)) {
                count++;
                j += strLength;
            }
            maxCount = Math.max(maxCount, count);
        }
        */
        return maxCount;

    }
}
