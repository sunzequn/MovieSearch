package com.sunzequn.search.data.crawler.parser.youku;

import com.sunzequn.search.data.crawler.parser.HttpMethod;
import com.sunzequn.search.data.crawler.parser.MultiThread;
import com.sunzequn.search.data.entity.Url;
import com.sunzequn.search.data.entity.YouKuMovie;
import com.sunzequn.search.data.utils.TimeUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Sloriac on 15/12/22.
 */
public class YouKuMultiThread extends MultiThread {

    private static final String MOVIE_COLLECTION = "YouKuMovie";
    private static final String URL_COLLECTION = "YouKuUrl";
    private static final String URL_KEY = "youkuUrl";
    private static final int THREAD_NUM = 10;

    /**
     * Main method.
     */
    public static void main(String[] args) {
        init(URL_KEY);
        pullUrls();

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
                        saveUrl(URL_COLLECTION, url1);
                        saveMovie(MOVIE_COLLECTION, movie);
                    }
                }

            }, "thread" + i).start();
        }
    }

    /**
     * Pull urls to visit from the specific website.
     */
    public static void pullUrls() {
        while (true) {
            baseUrl = parseList(baseUrl);
            if (baseUrl == null) {
                break;
            }
        }
        System.out.println(urlQueue.unVisitedSize());
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
        Elements pages = document.select("li.next");
        if (pages != null && pages.size() > 0) {
            Element page = pages.first();
            Element nextPage = page.select("a").first();
            //When it has next page.
            if (nextPage != null) {
                String pageLink = nextPage.attr("href");
                return (webPrefix + pageLink);
            }
        }
        return null;
    }

}
