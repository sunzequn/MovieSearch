package com.sunzequn.search.data.parser.segment;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Sloriac on 15/12/8.
 *
 * The wrapper of the result of word segmentation.
 */
public class Word {

    //The number of single words.
    private int singleWord;
    //The number of words.
    private int number;
    //The list of words.
    private List<String> words;

    /**
     * Public constructor without parameters
     * with the initialization of variables.
     */
    public Word() {
        singleWord = 0;
        number = 0;
        words = new ArrayList<>();
    }

    public int getSingleWord() {
        return singleWord;
    }

    public int getNumber() {
        return number;
    }

    public List<String> getWords() {
        return words;
    }

    public void add(String word) {
        if (!StringUtils.isEmpty(word)) {
            words.add(word);
            number += 1;
            if (word.length() == 1) {
                singleWord += 1;
            }
        }
    }

    public void reverse() {
        Collections.reverse(words);
    }
}
