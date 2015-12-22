package com.sunzequn.search.data.crawler.parser;

import com.sunzequn.search.data.crawler.exception.ConfigException;
import com.sunzequn.search.data.crawler.wrapper.UrlQueue;
import com.sunzequn.search.data.entity.Url;
import com.sunzequn.search.data.entity.YouKuMovie;
import com.sunzequn.search.data.persistence.mongodb.MovieOperation;
import com.sunzequn.search.data.utils.PropertiesUtil;
import com.sunzequn.search.data.utils.TimeUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Sloriac on 15/12/22.
 */
public class YouKuMultiThread extends PullText {

    private static final String YOUKU_MOVIE_COLLECTION = "YouKuMovie";
    private static final String YOUKU_URL_COLLECTION = "YouKuUrl";
    private static final int THREAD_NUM = 10;

    //The wrapper of unvisited urls and visited urls.
    private static UrlQueue urlQueue;

    //The first url.
    private static String baseUrl;

    //The number of visited urls.
    private static int num = 0;

    //The class providing operations of MongoDB.
    private static MovieOperation movieOperation;

    /**
     * Main method.
     */
    public static void main(String[] args) {
        init();

        TimeUtil.start();

        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(() -> {
                YouKuHtmlParser parser = new YouKuHtmlParser();
                while (true) {
                    String url = getUrl();
                    if (url == null) {
                        return;
                    }
                    YouKuMovie movie = parser.parse(url);
                    if (movie != null) {
                        markVisited(url);
                        Url url1 = new Url(url);
                        saveUrl(url1);
                        saveMovie(movie);
                        System.out.println(movie);
                    }
                }

            }, "thread" + i).start();
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
    public static synchronized void saveUrl(Url url) {
        movieOperation.save(YOUKU_URL_COLLECTION, url);
    }

    /**
     * Save visited movies to MongoDB.
     * This is also a synchronized method.
     */
    public static synchronized void saveMovie(YouKuMovie movie) {
        movieOperation.save(YOUKU_MOVIE_COLLECTION, movie);
    }

    /**
     * Initialize variables and put the base urls to the queue for unvisited urls.
     */
    public static void init() {

        movieOperation = new MovieOperation();
        urlQueue = UrlQueue.instance();
        try {
            baseUrl = PropertiesUtil.getValue("youkuUrl");
            if (baseUrl == null) {
                throw new ConfigException("The value of: baseUrl is not defined in config.");
            }

            while (true) {
                baseUrl = parseList(baseUrl);
                if (baseUrl == null) {
                    break;
                }
            }
            System.out.println(urlQueue.unVisitedSize());

        } catch (ConfigException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parse the web of movies list and put new movie url to <code>UrlQueue</code>.
     *
     * @param url the url to parse
     * @return a new url of to parse if possible otherwise null
     */
    public static String parseList(String url) {

        String webPrefix = "http://www.youku.com";

        Document document = pullFromUrl(url, 5000, HttpMethod.Get);

        /**
         * There are two types of links: movies list and movie detail.
         * Add urls of movie detail to <code>UrlQueue</code> and
         * return the url of movies list to repeat.
         */
        Elements movies = document.select("div.p-link");
        for (Element movie : movies) {
            Element link = movie.select("a[href]").first();
            String movieLink = link.attr("href");
            enUrl(movieLink);
        }
        //Parse the pages of bottom.
        Element page = document.select("li.next").first();
        Element nextPage = page.select("a").first();
        //When it has next page.
        if (nextPage != null) {
            String pageLink = nextPage.attr("href");
            return (webPrefix + pageLink);
        }
        return null;
    }

}
