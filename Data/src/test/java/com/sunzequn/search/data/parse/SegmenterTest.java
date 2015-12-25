package com.sunzequn.search.data.parse;

import com.sunzequn.search.data.parser.stanford.Segmenter;
import org.junit.Test;

/**
 * Created by Sloriac on 15/12/25.
 */
public class SegmenterTest {

    public static void main(String[] args){
        Segmenter segmenter = new Segmenter();
        System.out.println(segmenter.seg("我是孙泽群"));
    }
}
