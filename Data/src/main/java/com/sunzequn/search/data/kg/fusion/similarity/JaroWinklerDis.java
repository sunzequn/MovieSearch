package com.sunzequn.search.data.kg.fusion.similarity;

import java.util.Arrays;

/**
 * Created by Sloriac on 15/12/23.
 * <p>
 * The Jaro–Winkler distance is a measure of similarity between two strings.
 * It is a variant of the Jaro distance metric.
 * This measure is designed and best suited for short strings such as person names.
 * The score is normalized such that 0 equates to no similarity and 1 is an exact match.
 */
public class JaroWinklerDis {


    //The value of the threshold used for adding the Winkler bonus, the default value is 0.7.
    private static double threshold = 0.7;

    /**
     * Compute Jaro-Winkler distance.
     *
     * @param source source string
     * @param target target string
     * @return their Jaro-Winkler distance
     */
    public static double compute(char[] source, char[] target) {
        int[] results = distance(source, target);
        //the number of matched character
        double matches = (double) results[0];
        if (matches == 0) {
            return 0;
        }
        // Jaro Distance
        double dj = ((matches / source.length + matches / target.length + (matches - results[1]) / matches)) / 3;
        // Jaro-Winkler Distance, the scaling factor is computed by Math.min(0.1f, 1f / mtp[3])
        double djw = dj < threshold ? dj : dj + Math.min(0.1f, 1f / results[3]) * results[2] * (1 - dj);
        return djw;
    }

    private static int[] distance(char[] source, char[] target) {
        char[] max, min;
        if (source.length > target.length) {
            max = source;
            min = target;
        } else {
            max = target;
            min = source;
        }
        /*
        Compute the matching range.
        The two strings s1, s2 are considered as matched if
        the distance of them is is equal or less than floor(max(|s1|,|s2|)/2)-1.
         */
        int range = Math.max(max.length / 2 - 1, 0);
        /*
        Record the index of same characters in the long string
        when comparing the short string with the long one.
         */
        int[] matchIndexes = new int[min.length];
        Arrays.fill(matchIndexes, -1);
        /*
        Record if the character is matched
        when comparing the short string with the long one.
         */
        boolean[] matchFlags = new boolean[max.length];
        //the number of matched characters
        int matches = 0;
        // The loop begins with the short string.
        for (int i = 0; i < min.length; i++) {
            char ch = min[i];
            // the matching range, from (index-range) to (index+range)
            for (int j = Math.max(i - range, 0), end = Math.min(i + range + 1, max.length); j < end; j++) {
                /*
                Exclude the characters that has been matched.
                Jump out of the loop if find matched a character pair
                 */
                if (!matchFlags[j] && ch == max[j]) {
                    matchIndexes[i] = j;
                    matchFlags[j] = true;
                    matches++;
                    break;
                }
            }
        }
        //Copy the matched characters in the short string and retail the original order.
        char[] ch1 = new char[matches];
        //Copy the matched characters in the long string and retail the original order.
        char[] ch2 = new char[matches];
        for (int i = 0, si = 0; i < min.length; i++) {
            if (matchIndexes[i] != -1) {
                ch1[si] = min[i];
                si++;
            }
        }
        for (int i = 0, si = 0; i < max.length; i++) {
            if (matchFlags[i]) {
                ch2[si] = max[i];
                si++;
            }
        }

        //Compute the number of transpositions.
        int transpositions = 0;
        for (int i = 0; i < ch1.length; i++) {
            if (ch1[i] != ch2[i]) {
                transpositions++;
            }
        }

        //Compute the number of the prefix of two strings.
        int prefix = 0;
        for (int i = 0; i < min.length; i++) {
            if (source[i] == target[i]) {
                prefix++;
            } else {
                break;
            }
        }

        // return the number of matched characters, transpositions, the prefix, and the maximum length
        return new int[]{matches, transpositions / 2, prefix, max.length};
    }
}
