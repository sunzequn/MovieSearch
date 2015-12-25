package com.sunzequn.search.data.crawler.parser;

import com.sunzequn.search.data.crawler.wrapper.UrlQueue;
import com.sunzequn.search.data.entity.Url;
import com.sunzequn.search.data.exception.ConfigException;
import com.sunzequn.search.data.persistence.mongodb.MovieOperation;
import com.sunzequn.search.data.utils.PropertiesUtil;
import com.sunzequn.search.data.utils.TimeUtil;

/**
 * Created by Sloriac on 15/12/22.
 */
public abstract class MultiThread extends PullText {

    private static final String DATABASE = "MovieSearch";

    //The wrapper of unvisited urls and visited urls.
    protected static UrlQueue urlQueue;

    //The first url.
    protected static String baseUrl;

    //The number of visited urls.
    protected static int num = 0;

    //The class providing operations of MongoDB.
    protected static MovieOperation movieOperation;

    /**
     * Initialize variables and put the base urls to the queue for unvisited urls.
     */
    public static void init(String configKey) {

        movieOperation = new MovieOperation();
        urlQueue = UrlQueue.instance();
        try {
            baseUrl = PropertiesUtil.getValue(configKey);
            if (baseUrl == null) {
                throw new ConfigException("The value of: baseUrl is not defined in config.");
            }
        } catch (ConfigException e) {
            e.printStackTrace();
        }
    }

    /**
     * Provide a url for the thread that invokes this method.
     * This is a synchronized method and when one thread is executing it for an object,
     * all other threads that invoke this method for the same object block (suspend execution)
     * until the first thread is done with the object.
     *
     * @return an unvisited url if permitted otherwise null.
     */
    public static synchronized String getUrl() {
        if (urlQueue.ifContinue()) {
            num += 1;
            System.out.println(num);
            return urlQueue.deUnvisitedUrlsQueue();
        }
        TimeUtil.end();
        TimeUtil.print();
        System.out.println("The number of visited urls is " + num);
        return null;
    }

    /**
     * Put an unvisited url to <code>UrlQueue</code>.
     */
    public static synchronized void enUrl(String url) {
        urlQueue.enUnvisitedUrlsQueue(url);
    }

    /**
     * Mark a url as visited.
     *
     * @param url the url to mark
     */
    public static synchronized void markVisited(String url) {
        urlQueue.markVisited(url);
    }

    /**
     * Save visited urls to MongoDB.
     * This is also a synchronized method.
     */
    public static synchronized void saveUrl(String collection, Url url) {
        movieOperation.save(DATABASE, collection, url);
    }

    /**
     * Save visited movies to MongoDB.
     * This is also a synchronized method.
     */
    public static synchronized <T> void saveMovie(String collection, T t) {
        movieOperation.save(DATABASE, collection, t);
    }

}
