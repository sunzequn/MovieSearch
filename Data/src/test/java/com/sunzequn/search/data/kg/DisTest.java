package com.sunzequn.search.data.kg;

import com.sunzequn.search.data.kg.fusion.similarity.JaccardDis;
import com.sunzequn.search.data.kg.fusion.similarity.JaroWinklerDis;
import com.sunzequn.search.data.kg.fusion.similarity.LevenshteinDis;
import org.junit.Test;

/**
 * Created by Sloriac on 15/12/23.
 */
public class DisTest {

    @Test
    public void distanceTest() {

        char[] source = "会员:速度与激情7".toCharArray();
        char[] target = "速度与激情".toCharArray();
        System.out.println("Jaro-Winkler distance: " + JaroWinklerDis.compute(source, target));
        System.out.println("Levenshtein distance: " + LevenshteinDis.compute(source, target));
        System.out.println("Jaccard distance: " + JaccardDis.compute(source, target));

    }
}
