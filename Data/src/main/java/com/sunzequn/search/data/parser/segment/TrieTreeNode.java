package com.sunzequn.search.data.parser.segment;

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
        if (newNode != null) {
            return newNode;
        }
        newNode = new TrieTreeNode(word);
        if (nodes.size() > 0) {
            List<TrieTreeNode> temp = new ArrayList<>();
            boolean isInsert = false;
            for (int i = 0; i < nodes.size(); i++) {
                if (nodes.get(i).getWord() > word && !isInsert) {
                    isInsert = true;
                    temp.add(newNode);
                }
                temp.add(nodes.get(i));
            }
            nodes = temp;
            if (!isInsert) {
                nodes.add(newNode);
            }
            return newNode;
        }
        nodes.add(newNode);
        return newNode;
    }

}