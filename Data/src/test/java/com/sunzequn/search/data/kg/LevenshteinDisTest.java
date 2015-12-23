package com.sunzequn.search.data.kg;

import com.sunzequn.search.data.kg.fusion.similarity.LevenshteinDis;
import org.junit.Test;

/**
 * Created by Sloriac on 15/12/23.
 */
public class LevenshteinDisTest {

    @Test
    public void distanceTest() {

        char[] source = "".toCharArray();
        char[] target = "s".toCharArray();
        System.out.println(LevenshteinDis.distance(source, target));
        System.out.println(LevenshteinDis.compute(source, target));

    }
}
