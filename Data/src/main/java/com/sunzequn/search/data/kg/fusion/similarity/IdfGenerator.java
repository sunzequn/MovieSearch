package com.sunzequn.search.data.kg.fusion.similarity;

import com.sunzequn.search.data.entity.TencentMovie;
import com.sunzequn.search.data.entity.YouKuMovie;
import com.sunzequn.search.data.parser.stanford.Segmenter;
import com.sunzequn.search.data.persistence.mongodb.MovieOperation;
import com.sunzequn.search.data.utils.WriteUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sloriac on 15/12/25.
 */
public class IdfGenerator {

    private static final String IDF_FILE = "Data/src/main/resources/idf/idf.txt";
    private static final String DATABASE = "MovieSearch";
    private static final String YOUKU_MOVIE_COLLECTION = "YouKuMovie";
    private static final String TENCENT_MOVIE_COLLECTION = "TencentMovie";

    private static Map<String, Double> termsWithIdf;
    private static List<YouKuMovie> youKuMovies;
    private static List<TencentMovie> tencentMovies;
    private static List<String> documents;
    private static WriteUtil writeUtil;
    private static MovieOperation movieOperation;
    private static Segmenter segmenter;
    private static TfIdf tfIdf;

    public static void main(String[] args) {
        init();
        getDocument();
        getTerms();
        calculate();
    }

    private static void init() {
        termsWithIdf = new HashMap<>();
        youKuMovies = new ArrayList<>();
        tencentMovies = new ArrayList<>();
        documents = new ArrayList<>();
        writeUtil = new WriteUtil(IDF_FILE, true);
        movieOperation = new MovieOperation();
        segmenter = new Segmenter();
        tfIdf = new TfIdf();
    }


    private static void getDocument() {
        youKuMovies = movieOperation.findAll(DATABASE, YOUKU_MOVIE_COLLECTION, YouKuMovie.class);
        tencentMovies = movieOperation.findAll(DATABASE, TENCENT_MOVIE_COLLECTION, TencentMovie.class);
        System.out.println("The number of youku movies : " + youKuMovies.size());
        System.out.println("The number of tencent movies : " + tencentMovies.size());
        for (YouKuMovie movie : youKuMovies) {
            String sentence = movie.getGeneral();
            if (sentence == null || sentence.length() == 10) {
                continue;
            }
            documents.add(movie.getGeneral());
        }
        for (TencentMovie movie : tencentMovies) {
            String sentence = movie.getGeneral();
            if (sentence == null || sentence.length() == 0) {
                continue;
            }
            documents.add(movie.getGeneral());
        }
        System.out.println("the number of documents : " + documents.size());
    }

    private static void getTerms() {
        for (String document : documents) {
            List<String> terms = segmenter.seg(document);
            for (String term : terms) {
                termsWithIdf.putIfAbsent(term, new Double(0));
            }
        }
        System.out.println("the number of terms : " + termsWithIdf.size());
    }

    private static void calculate() {
        for (Map.Entry<String, Double> entry : termsWithIdf.entrySet()) {
            String term = entry.getKey();
            if (StringUtils.isEmpty(term)) {
                continue;
            }
            double idf = tfIdf.idf(documents, term);
            String line = term + " " + idf;
            writeUtil.write(line);
        }
        writeUtil.close();
    }

}

