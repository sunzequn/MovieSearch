package com.sunzequn.search.data.utils;

import org.junit.Test;

/**
 * Created by Sloriac on 15/12/19.
 */
public class TimeUtilTest {

    @Test
    public void duration() {
        TimeUtil.start();

        for (int i = 0; i < 100000; i++) {
            if (i % 100 == 0) {
                System.out.println(i);
            }
        }

        TimeUtil.end();

        TimeUtil.print();
    }
}
