package com.sunzequn.search.data.parse;

import com.sunzequn.search.data.parser.segment.TrieTree;
import org.junit.Test;

/**
 * Created by Sloriac on 15/12/2.
 */
public class TrieTreeTest {

    @Test
    public void instance() {
        TrieTree trieTree = TrieTree.instance();
        trieTree.print();
    }

}
