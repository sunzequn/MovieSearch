package com.sunzequn.search.data.kg.fusion.similarity;

import java.util.List;

/**
 * Created by Sloriac on 15/12/25.
 * <p>
 * Calculate the tf-idf score of a term.
 */
public class TfIdf {

    /**
     * Calculate the tf(term frequency) of term.
     *
     * @param totalterms Array of all the words under processing document
     * @param term       term of which tf is to be calculated.
     * @return tf(term frequency) of term termToCheck
     */
    public double tf(List<String> totalterms, String term) {
        if (totalterms.size() == 0) {
            return -1;
        }
        double count = 0;
        for (String string : totalterms) {
            if (string.equalsIgnoreCase(term))
                count++;
        }
        return count / totalterms.size();
    }

    /**
     * Calculated idf(inverse document frequency) of term.
     *
     * @param documents all the documents
     * @param term      term of which idf is to be calculated.
     * @return idf score
     */
    public double idf(List<String> documents, String term) {
        if (documents.size() == 0) {
            return -1;
        }
        double count = 0;
        for (String document : documents) {
            if (document.contains(term)) {
                count++;
            }
        }
        return Math.log(documents.size() / count);
    }
}
