package com.sunzequn.search.data.crawler.parser.tencent;

import com.sunzequn.search.data.crawler.parser.HtmlParser;
import com.sunzequn.search.data.crawler.parser.HttpMethod;
import com.sunzequn.search.data.crawler.parser.MultiThread;
import com.sunzequn.search.data.crawler.parser.youku.YouKuHtmlParser;
import com.sunzequn.search.data.entity.TencentMovie;
import com.sunzequn.search.data.entity.Url;
import com.sunzequn.search.data.entity.YouKuMovie;
import com.sunzequn.search.data.utils.TimeUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Sloriac on 15/12/22.
 */
public class TencentMultiThread extends MultiThread {

    private static final String MOVIE_COLLECTION = "TencentMovie";
    private static final String URL_COLLECTION = "TencentUrl";
    private static final String URL_KEY = "tencentUrl";
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
                TencentHtmlParser parser = new TencentHtmlParser();
                while (true) {
                    String url = getUrl();
                    if (url == null) {
                        return;
                    }
                    TencentMovie movie = parser.parse(url);
                    if (movie != null) {
                        markVisited(url);
                        saveMovie(MOVIE_COLLECTION, movie);
                        Url url1 = new Url(url);
                        saveUrl(URL_COLLECTION, url1);
                    }
                }

            }, "thread" + i).start();
        }
    }

    /**
     * Pull urls to visit from the specific website.
     */
    public static void pullUrls() {
        String prefix = "http://v.qq.com/movielist/1/0/0/0/";
        String suffix = "/20/0/0/-1.html";
        for (int i = 0; i < 250; i++) {
            System.out.println(i + 1);
            parseList(prefix + i + suffix);
        }
        System.out.println(urlQueue.unVisitedSize());
    }

    /**
     * Parse the web of movies list and put new movie url to <code>UrlQueue</code>.
     *
     * @param url the url to parse
     */
    public static void parseList(String url) {
        Document document = pullFromUrl(url, 50000, HttpMethod.Get);
        Elements movies = document.select("a.mod_poster_130");
        for (Element movie : movies) {
            Element link = movie.select("a[href]").first();
            String movieLink = link.attr("href");
            enUrl(movieLink);
        }
    }
}
