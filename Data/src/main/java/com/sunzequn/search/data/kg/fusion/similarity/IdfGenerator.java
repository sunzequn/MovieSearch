package com.sunzequn.search.data.kg.fusion.similarity;

import com.sun.xml.internal.bind.v2.model.core.ID;
import com.sunzequn.search.data.entity.TencentMovie;
import com.sunzequn.search.data.entity.YouKuMovie;
import com.sunzequn.search.data.persistence.mongodb.MovieOperation;
import com.sunzequn.search.data.utils.WriteUtil;

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
    private static WriteUtil writeUtil;
    private static MovieOperation movieOperation;

    public static void main(String[] args) {
        init();
    }

    private static void init() {
        termsWithIdf = new HashMap<>();
        youKuMovies = new ArrayList<>();
        tencentMovies = new ArrayList<>();
        writeUtil = new WriteUtil(IDF_FILE, true);
        movieOperation = new MovieOperation();
        youKuMovies = movieOperation.findAll(DATABASE, YOUKU_MOVIE_COLLECTION, YouKuMovie.class);
        tencentMovies = movieOperation.findAll(DATABASE, TENCENT_MOVIE_COLLECTION, TencentMovie.class);

        System.out.println(youKuMovies.size());
        System.out.println(tencentMovies.size());
    }


}
