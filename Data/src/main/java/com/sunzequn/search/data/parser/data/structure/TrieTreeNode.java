package com.sunzequn.search.data.parser.data.structure;

/**
 * Created by Sloriac on 15/12/1.
 * <p>
 * A wrapper of nodes in the trie tree.
 */
public class TrieTreeNode {

    private char word;
    private TrieTreeNode[] nodes = new TrieTreeNode[0];

    public char getWord() {
        return word;
    }

    public void setWord(char word) {
        this.word = word;
    }

    public TrieTreeNode[] getNodes() {
        return nodes;
    }

    public void setNodes(TrieTreeNode[] nodes) {
        this.nodes = nodes;
    }

    public TrieTreeNode getNode(char word) {
        for (TrieTreeNode node : nodes) {
            if (node.getWord() == word) {
                return node;
            }
        }
        return null;
    }


}
