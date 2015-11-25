package com.sunzequn.search.data.utils;

import org.junit.Test;

/**
 * Created by Sloriac on 15/11/25.
 */
public class StringUtilTest {

    @Test
    public void removePrefix() {
        System.out.println(StringUtil.removePrefix("导演:孙泽群", "导演:"));
    }

    @Test
    public void removeSuffix() {
        System.out.println(StringUtil.removeSuffix("128分钟", "分钟"));
    }

    @Test
    public void split() {
        System.out.println(StringUtil.split("sunzequn/sloriac", "/"));
    }
}
