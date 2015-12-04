package com.sunzequn.search.data.utils;

import org.junit.Test;

/**
 * Created by Sloriac on 15/11/25.
 */
public class WriteUtilTest {

    @Test
    public void write() {
        String file = "src/main/resources/movie.txt";
        WriteUtil writeUtil = new WriteUtil(file, true);
        for (int i = 0; i < 100; i++) {
            writeUtil.write("sunzequn");
        }
        writeUtil.close();
    }
}

