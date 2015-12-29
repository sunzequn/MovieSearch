package com.sunzequn.search.data.parser.pattern;

/**
 * Created by Sloriac on 15/12/29.
 */
public class Template {

    private String[] words;
    private String sparql;

    public Template() {
    }

    public Template(String[] words, String sparql) {
        this.words = words;
        this.sparql = sparql;
    }

    public void setWords(String[] words) {
        this.words = words;
    }

    public String[] getWords() {
        return words;
    }

    public String getSparql() {
        return sparql;
    }

    public void setSparql(String sparql) {
        this.sparql = sparql;
    }
}
