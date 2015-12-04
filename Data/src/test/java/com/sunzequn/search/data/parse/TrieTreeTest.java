package com.sunzequn.search.data.parse;

import com.sunzequn.search.data.parser.FilePath;
import com.sunzequn.search.data.parser.words.TrieTree;
import com.sunzequn.search.data.utils.ReadUtil;
import com.sunzequn.search.data.utils.WriteUtil;
import org.junit.Test;

import java.util.List;

/**
 * Created by Sloriac on 15/12/2.
 */
public class TrieTreeTest {

    @Test
    public void tree() {
        TrieTree trieTree = new TrieTree();
//        trieTree.add("孙泽群");
//        trieTree.print();
//        System.out.println("----------------");
//        trieTree.add("陈洁");
//        trieTree.print();
//        System.out.println("----------------");
//        trieTree.add("安纪存");
//        trieTree.print();

        ReadUtil readUtil = new ReadUtil(FilePath.movieDictionary);
        List<String> strings = readUtil.readByLine();
        System.out.println(strings.size());
        for (String string : strings.subList(0, 20)) {
            System.out.println(string);
            trieTree.add(string);
        }
        trieTree.print();
    }

}
