package com.sunzequn.search.data.parser.segment;

import com.sunzequn.search.data.parser.FilePath;
import com.sunzequn.search.data.utils.ReadUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by Sloriac on 15/12/2.
 *
 * A wrapper of trie tree, an ordered tree data structure
 * that is used to store a dynamic character set.
 */
public class TrieTree {

    /**
     * The Singleton instance of this class.
     */
    private static final TrieTree instance = new TrieTree();

    /**
     * The root node of trie tree.
     */
    private static TrieTreeNode ROOT;

    /**
     * The private constructor for the Singleton instance of this class.
     * This method also adds dictionaries to trie tree.
     */
    private TrieTree() {
        ROOT = new TrieTreeNode('/');
        ReadUtil readUtil;
        readUtil = new ReadUtil(FilePath.movieDictionary);
        List<String> movies = readUtil.readByLine();
        add(movies);
    }

    public static TrieTree instance() {
        return instance;
    }

    public boolean contains(String words) {
        words = words.trim();
        if (StringUtils.isEmpty(words)) {
            return false;
        }
        TrieTreeNode root = ROOT;
        for (int i = 0; i < words.length(); i++) {
            char word = words.charAt(i);
            TrieTreeNode node = root.getNode(word);
            if (node == null) {
                return false;
            } else {
                root = node;
            }
        }
        return root.isTerminal();
    }

    public void add(String words) {
        words = words.trim();
        if (StringUtils.isEmpty(words)) {
            return;
        }
        TrieTreeNode root = ROOT;
        for (int i = 0; i < words.length(); i++) {
            char word = words.charAt(i);
            TrieTreeNode node = root.addNode(word);
            root = node;
        }
        root.setTerminal(true);
    }

    public void add(List<String> words) {
        for (String word : words) {
            add(word);
        }
    }

    public void print() {
        print(ROOT);
    }

    public void print(TrieTreeNode node) {
        if (node.isTerminal()) {
            System.out.println(" " + node.getWord() + "#");
        } else {
            System.out.println(" " + node.getWord());
        }
        for (TrieTreeNode treeNode : node.getNodes()) {
            print(treeNode);
        }
    }

}
