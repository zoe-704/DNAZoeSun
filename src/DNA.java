/**
 * DNA
 * <p>
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *</p>
 * <p>
 * Completed by: [YOUR NAME HERE]
 *</p>
 */

public class DNA {

    public static int STRCount(String sequence, String STR) {
        int seqLength = sequence.length(), strLength = STR.length();
        if (strLength > seqLength) return 0; //  STR cannot be longer than the DNA sequence

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
