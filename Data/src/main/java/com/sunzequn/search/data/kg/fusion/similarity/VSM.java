package com.sunzequn.search.data.kg.fusion.similarity;

import com.sunzequn.search.data.parser.stanford.Segmenter;
import com.sunzequn.search.data.utils.ReadUtil;
import org.apache.commons.lang3.StringUtils;
import org.ujmp.core.DenseMatrix;
import org.ujmp.core.Matrix;
import org.ujmp.core.calculation.Calculation.*;

import java.util.*;

/**
 * Created by Sloriac on 15/12/26.
 * <p>
 * Model a document in the Vector Space and
 * use a well-known similarity measure (Cosine Similarity)
 * to calculate the similarity between different documents.
 */
public class VSM {

    private static final String IDF_FILE = "Data/src/main/resources/idf/idf.txt";
    private Map<String, Double> idfs;
    private TfIdf tfIdf;
    private Segmenter segmenter;

    public VSM() {
        init();
    }

    private void init() {
        idfs = new HashMap<>();
        tfIdf = new TfIdf();
        segmenter = new Segmenter();
        getIDFs();
    }

    private void getIDFs() {
        ReadUtil readUtil = new ReadUtil(IDF_FILE);
        List<String> lines = readUtil.readByLine();
        readUtil.close();
        for (String line : lines) {
            String[] idf = StringUtils.split(line, " ");
            String key = idf[0];
            Double value = Double.valueOf(idf[1]);
            idfs.putIfAbsent(key, value);
        }
    }

    public double cosine(String document1, String document2) {
        Matrix matrix = toVector(document1, document2);
        Matrix matrix1 = matrix.selectRows(Ret.NEW, 0);
        Matrix matrix2 = matrix.selectRows(Ret.NEW, 1);
        return matrix1.cosineSimilarityTo(matrix2, true);
    }

    public Matrix toVector(String document1, String document2) {
        Set<String> allTerms = new HashSet<>();
        List<String> terms1 = segmenter.seg(document1);
        List<String> terms2 = segmenter.seg(document2);
        allTerms.addAll(terms1);
        allTerms.addAll(terms2);
        List<String> indexes = new ArrayList<>(allTerms);
        int num = indexes.size();
        Matrix matrix = DenseMatrix.Factory.zeros(2, num);

        for (int i = 0; i < indexes.size(); i++) {
            String term = indexes.get(i);
            if (StringUtils.isEmpty(term)) {
                continue;
            }
            double tfidf1 = tfIdf.tf(terms1, term) * idfs.get(term).doubleValue();
            double tfidf2 = tfIdf.tf(terms2, term) * idfs.get(term).doubleValue();
            matrix.setAsDouble(tfidf1, 0, i);
            matrix.setAsDouble(tfidf2, 1, i);
        }
        return matrix;
    }
}
