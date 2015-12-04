package com.sunzequn.search.data.parser.words;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Sloriac on 15/12/2.
 */
public class TrieTree {

    private static TrieTreeNode ROOT = new TrieTreeNode('/');

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
        if (root.isTerminal()) {
            return true;
        }
        return false;
    }

    public void add(String words) {
        words = words.trim();
        if (StringUtils.isEmpty(words)) {
            return;
        }
        TrieTreeNode root = ROOT;
        for (int i = 0; i < words.length(); i++) {
            char word = words.charAt(i);
            System.out.println(word + ".......");
            TrieTreeNode node = root.addNode(word);
            root = node;
        }
        root.setTerminal(true);
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
