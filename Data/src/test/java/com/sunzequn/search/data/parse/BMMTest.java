package com.sunzequn.search.data.parse;

import com.sunzequn.search.data.parser.segment.BMM;
import com.sunzequn.search.data.parser.segment.TrieTree;
import com.sunzequn.search.data.parser.segment.Word;
import org.junit.Test;

import java.util.List;

/**
 * Created by Sloriac on 15/12/8.
 */
public class BMMTest {

    @Test
    public void test() {

        TrieTree trieTree = TrieTree.instance();
        BMM bmm = new BMM(trieTree);
        String sentence = "超自然事件簿窃听风云勇士";
        Word word = bmm.segment(sentence);
        System.out.println(word.getNumber());
        System.out.println(word.getWords());

    }
}
