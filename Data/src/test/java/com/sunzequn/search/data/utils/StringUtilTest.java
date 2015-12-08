package com.sunzequn.search.data.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void test() {

        List<String> strings = new ArrayList<>();


        String s1 = "孙泽群";

        char c = s1.charAt(0);
        System.out.println(c);

//        System.out.println(s1.substring(1,2));
//        String s2 = "泽";
//        strings.add(s1);
//        strings.add(s2);
//        System.out.println(strings.subList(0,1));
//        System.out.println(strings.subList(1,1));
//        System.out.println(strings.subList(0,2));
//        strings.addAll(2, strings.subList(0,2));
//        System.out.println(strings);

    }

    @Test
    public void same() {
        List<String> s1 = new ArrayList<>();
        s1.add("孙泽群");


        List<String> s2 = new ArrayList<>();
        s2.add("孙泽群");

        System.out.println(s1.equals(s2));

        s1.add("孙泽群");
        System.out.println(s1.equals(s2));
    }
}
