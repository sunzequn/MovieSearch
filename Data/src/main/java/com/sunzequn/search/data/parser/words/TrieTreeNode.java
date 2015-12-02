package com.sunzequn.search.data.parser.words;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sloriac on 15/12/1.
 * <p>
 * A wrapper of nodes in the trie tree.
 */
public class TrieTreeNode {

    private char word;
    private List<TrieTreeNode> nodes;
    private boolean terminal;

    public TrieTreeNode() {
    }

    public TrieTreeNode(char word) {
        this.word = word;
        nodes = new ArrayList<>();
        terminal = false;
    }

    public char getWord() {
        return word;
    }

    public void setWord(char word) {
        this.word = word;
    }

    public List<TrieTreeNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<TrieTreeNode> nodes) {
        this.nodes = nodes;
    }

    public boolean isTerminal() {
        return terminal;
    }

    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }

    public TrieTreeNode getNode(char word) {
        for (TrieTreeNode node : nodes) {
            if (node.getWord() == (word)) {
                return node;
            }
        }
        return null;
    }

    public TrieTreeNode addNode(char word) {

        TrieTreeNode newNode = getNode(word);
        if (newNode == null) {
            newNode = new TrieTreeNode(word);
            if (nodes.size() > 0) {
                for (int i = 0; i < nodes.size(); i++) {
                    if (nodes.get(i).getWord() > word) {
                        List<TrieTreeNode> temp = nodes.subList(i, nodes.size());
                        nodes.addAll(i + 1, temp);
                        nodes.add(i, newNode);
                        return newNode;
                    }
                }
            }
            nodes.add(newNode);
            return newNode;
        } else {
            return newNode;
        }
    }

}