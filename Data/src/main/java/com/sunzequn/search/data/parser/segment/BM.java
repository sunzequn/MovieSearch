package com.sunzequn.search.data.parser.segment;

/**
 * Created by Sloriac on 15/12/8.
 * <p>
 * Bi-direction Matching method.
 */
public class BM {

    private BMM bmm;
    private FMM fmm;
    private TrieTree trieTree;

    public BM() {
        trieTree = TrieTree.instance();
        bmm = new BMM(trieTree);
        fmm = new FMM(trieTree);
    }

    /**
     * Segment sentences into individual segment using Bi-direction Matching method.
     * In this method, I take two heuristic rules to choose the better result:
     * 1. If the length of two results(`length` means the number of segment) is not the same,
     * choose the short one.
     * 2. If the same, determining whether the two results are the same:
     * 2.1 If the two results are the same, choose any one.
     * 2.2 If not the same, choose the one which has less characters.
     *
     * @param sentence the sentence to be segmented.
     * @return a <code>Word</code>.
     */
    public Word segment(String sentence) {

        Word bmmWords = bmm.segment(sentence);
        Word fmmWords = fmm.segment(sentence);
        if (bmmWords.getNumber() != fmmWords.getNumber()) {
            return bmmWords.getNumber() < fmmWords.getNumber() ? bmmWords : fmmWords;
        } else {
            if (bmmWords.equals(fmmWords)) {
                return bmmWords;
            } else {
                return bmmWords.getSingleWord() < fmmWords.getSingleWord() ? bmmWords : fmmWords;
            }
        }
    }
}
