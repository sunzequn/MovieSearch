package com.sunzequn.search.data.kg.fusion.similarity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sloriac on 15/12/23.
 * <p>
 * The Jaccard measure, or similarity coefficient, is one approach to capturing
 * the intuition that strings which share more of the same characters are more similar.
 */
public class JaccardDis {

    /**
     * Compute Jaccard distance.
     *
     * @param source source string
     * @param target target string
     * @return a number between 0 and 1, representing the similarity
     */
    public static double compute(char[] source, char[] target) {
        int sourceLen = source.length;
        int targetLen = target.length;
        Set<Character> sourceSet = new HashSet<>();
        Set<Character> targetSet = new HashSet<>();
        for (int i = 0; i < sourceLen; i++) {
            sourceSet.add(source[i]);
        }
        for (int i = 0; i < targetLen; i++) {
            targetSet.add(target[i]);
        }
        Set<Character> intersection = new HashSet<>();
        Set<Character> union = new HashSet<>();
        intersection.addAll(sourceSet);
        intersection.retainAll(targetSet);
        union.addAll(sourceSet);
        union.addAll(targetSet);
        if (union.size() != 0) {
            return (double) intersection.size() / union.size();
        }
        return 0;
    }
}
