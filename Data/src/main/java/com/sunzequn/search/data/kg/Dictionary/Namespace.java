package com.sunzequn.search.data.kg.Dictionary;

/**
 * Created by Sloriac on 15/11/28.
 */
public final class Namespace {

    public static final String PREFIX = "http://sunzequn.com/ontology";
    public static final String SLASH = "/";
    public static final String MOVIE = PREFIX + SLASH + "电影";
    public static final String AREA = PREFIX + SLASH + "地区";
    public static final String PERSON = PREFIX + SLASH + "人";
    public static final String DIRECTOR = PERSON + SLASH + "导演";
    public static final String ACTOR = PERSON + SLASH + "演员";
    public static final String PROPERTY = PREFIX + SLASH + "property";

    public static final String FILEPATH = "src/main/resources/movie.owl";


    private Namespace() {
    }
}
