package com.sunzequn.search.data.parser.segment;

import com.sunzequn.search.data.exception.WordException;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Sloriac on 15/12/8.
 * <p>
 * Backward Maximum Matching method.
 */
public class BMM {

    private TrieTree trieTree;

    public BMM() {
    }

    public BMM(TrieTree trieTree) {
        this.trieTree = trieTree;
    }

    /**
     * Segment sentences into individual segment using Backward Maximum Matching method.
     *
     * @param sentence the sentence to be segmented.
     * @return a <code>Word</code>.
     */
    public Word segment(String sentence) {
        Word word = new Word();
        if (StringUtils.isEmpty(sentence)) {
            try {
                throw new WordException("The sentence can`t be empty.");
            } catch (WordException e) {
                e.printStackTrace();
            }
        }
        while (sentence.length() > 0) {
            String string = sentence;
            /*
            This is a loop that continues to determine whether the dictionary contains
            the sub-sentence and trims a character from the head if the sub-sentence is
            not in the dictionary until finding a word matching this sub-sentence or
            the sub-sentence is a character.
             */
            while (!trieTree.contains(string)) {
                if (string.length() == 1) {
                    break;
                }
                string = string.substring(1);
            }
            word.add(string);
            //Remove the word contained in the dictionary from the tail.
            sentence = sentence.substring(0, sentence.length() - string.length());
        }
        word.reverse();
        return word;
    }
}
