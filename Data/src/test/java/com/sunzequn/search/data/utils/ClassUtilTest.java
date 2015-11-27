package com.sunzequn.search.data.utils;

import com.sunzequn.search.data.entity.YouKuMovie;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sloriac on 15/11/27.
 */
public class ClassUtilTest {

    @Test
    public void setFieldValue() {

        YouKuMovie movie = new YouKuMovie();
        ClassUtil.setFieldValue(movie, "name", "ssss");
        List<String> strings = new ArrayList<>();
        strings.add("sss");
        strings.add("sdsds");
        ClassUtil.setFieldValue(movie, "actors", strings);
        System.out.println(movie);
    }
}
