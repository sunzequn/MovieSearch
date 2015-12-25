package com.sunzequn.search.data.parser.stanford;

import java.io.PrintStream;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.util.PropertiesUtils;
import org.junit.Test;

/**
 * Created by Sloriac on 15/12/25.
 *
 * Stanford Chinese Word Segmenter.
 */
public class Segmenter {

    private static final String PATH_PREFIX = "Data/src/main/resources/segment";
    private CRFClassifier<CoreLabel>  segmenter;

    public Segmenter() {
        init();
    }

    private void init(){
        Properties properties = new Properties();
        properties.setProperty("sighanCorporaDict", PATH_PREFIX);
        properties.setProperty("serDictionary", PATH_PREFIX + "/dict-chris6.ser.gz");
        properties.setProperty("inputEncoding", "UTF-8");
        properties.setProperty("sighanPostProcessing", "true");
        segmenter = new CRFClassifier<>(properties);
        segmenter.loadClassifierNoExceptions(PATH_PREFIX + "/ctb.gz", properties);
    }

    /**
     * Segment Chinese sentence.
     *
     * @param sentence the Chinese sentence to segment.
     * @return a list of Chinese terms.
     */
    public List<String> seg(String sentence){
        List<String> segmented = segmenter.segmentString(sentence);
        return segmented;
    }

}
