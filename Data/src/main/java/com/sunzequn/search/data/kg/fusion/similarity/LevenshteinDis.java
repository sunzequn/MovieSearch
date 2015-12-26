package com.sunzequn.search.data.kg.fusion.similarity;

/**
 * Created by Sloriac on 15/12/23.
 * <p>
 * A simple form of edit distance called Levenshtein distance
 * where the edit operations of insertions, deletions, and substitutions
 * are allowed and each given an equal weight of 1.
 */
public class LevenshteinDis {

    /**
     * Compute edit distance and normalize this distance into a number between 0 and 1.
     * The idea is subtracting the distance from the length of the larger of the two strings
     * and dividing by the same length.
     * The larger this value is, the nearer the edit distance could be.
     *
     * @param source source string
     * @param target target string
     * @return a number between 0 and 1, representing the similarity.
     */
    public static double compute(char[] source, char[] target) {

        int sourceLen = source.length;
        int targetLen = target.length;
        double maxLen = sourceLen > targetLen ? sourceLen : targetLen;
        double dis = distance(source, target);
        if (maxLen != 0) {
            return 1 - dis / maxLen;
        }
        return 0;
    }

    /**
     * Compute edit distance.
     *
     * @param source source string
     * @param target target string
     * @return the number of least operations that will transform source string into target
     */
    private static int distance(char[] source, char[] target) {

        int sourceLen = source.length;
        int targetLen = target.length;
        //Allocate distance matrix.
        int dis[][] = new int[sourceLen + 1][targetLen + 1];

        /*
        Initialize upper bound on distance.
         */
        for (int i = 0; i <= sourceLen; i++) {
            dis[i][0] = i;
        }
        for (int i = 0; i <= targetLen; i++) {
            dis[0][i] = i;
        }

        for (int i = 1; i <= targetLen; i++) {
            for (int j = 1; j <= sourceLen; j++) {
                if (target[i - 1] == source[j - 1]) {
                    //Cost is same as previous match.
                    dis[j][i] = dis[j - 1][i - 1];
                } else {
                    //Cost is 1 for an insertion, deletion, or substitution.
                    dis[j][i] = Math.min(Math.min(dis[j - 1][i], dis[j][i - 1]), dis[j - 1][i - 1]) + 1;
                }
            }
        }
        return dis[sourceLen][targetLen];

    }
}
