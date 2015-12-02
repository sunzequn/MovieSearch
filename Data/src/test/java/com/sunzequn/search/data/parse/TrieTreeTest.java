package com.sunzequn.search.data.parse;

import com.sunzequn.search.data.parser.words.TrieTree;
import org.junit.Test;

/**
 * Created by Sloriac on 15/12/2.
 */
public class TrieTreeTest {

    @Test
    public void tree() {
        TrieTree trieTree = new TrieTree();
        trieTree.add("中国");
        trieTree.add("中国人");
        trieTree.add("中国国粹");
        trieTree.print();
        System.out.println(trieTree.contains("中国人"));
        System.out.println(trieTree.contains("中国国"));
    }

}
