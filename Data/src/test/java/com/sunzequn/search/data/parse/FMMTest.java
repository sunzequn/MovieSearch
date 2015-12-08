package com.sunzequn.search.data.parse;

import com.sunzequn.search.data.parser.segment.FMM;
import com.sunzequn.search.data.parser.segment.TrieTree;
import com.sunzequn.search.data.parser.segment.Word;
import org.junit.Test;

import java.util.List;

/**
 * Created by Sloriac on 15/12/8.
 */
public class FMMTest {

    @Test
    public void test() {

        TrieTree trieTree = TrieTree.instance();
        FMM fmm = new FMM(trieTree);
        String sentence = "超自然事件簿窃听风云勇士";
        Word word = fmm.segment(sentence);
        System.out.println(word.getNumber());
        System.out.println(word.getWords());

    }
}
