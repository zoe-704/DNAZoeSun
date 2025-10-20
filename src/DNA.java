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

    public static int STRCount(String sequence, String STR) {

        int seqLength = sequence.length(), strLength = STR.length();
        if (strLength > seqLength) return 0; //  STR cannot be longer than the DNA sequence
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

        /*
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
