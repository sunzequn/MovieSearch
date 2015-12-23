package com.sunzequn.search.data.crawler.parser.tencent;

import com.sunzequn.search.data.crawler.parser.HttpMethod;
import com.sunzequn.search.data.crawler.parser.MultiThread;
import com.sunzequn.search.data.crawler.parser.youku.YouKuHtmlParser;
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
//
//        TimeUtil.start();
//        for (int i = 0; i < THREAD_NUM; i++) {
//            new Thread(() -> {
//                while (true) {
//
//                }
//
//            }, "thread" + i).start();
//        }
    }

    /**
     * Pull urls to visit from the specific website.
     */
    public static void pullUrls() {
        int num = 0;
        while (true) {
            baseUrl = parseList(baseUrl);
            num += 1;
            if (baseUrl == null) {
                break;
            }
        }
        System.out.println(baseUrl);
        System.out.println(num);
        System.out.println(urlQueue.unVisitedSize());
    }


    /**
     * Parse the web of movies list and put new movie url to <code>UrlQueue</code>.
     *
     * @param url the url to parse
     * @return a new url of to parse if possible otherwise null
     */
    public static String parseList(String url) {

        Document document = pullFromUrl(url, 50000, HttpMethod.Get);

        /**
         * There are two types of links: movies list and movie detail.
         * Add urls of movie detail to <code>UrlQueue</code> and
         * return the url of movies list to repeat.
         */
        Elements movies = document.select("a.mod_poster_130");
        for (Element movie : movies) {
            Element link = movie.select("a[href]").first();
            String movieLink = link.attr("href");
            System.out.println(movieLink);
            enUrl(movieLink);
        }

        //Parse the pages of bottom.
        Elements pages = document.select("a.next");
        System.out.println(pages.size());
        if (pages != null && pages.size() > 0) {
            Element page = pages.first();
            Element nextPage = page.select("a").first();
            //When it has next page.
            if (nextPage != null) {
                String pageLink = nextPage.attr("href");
                System.out.println(pageLink);
                return pageLink;
            }
            System.out.println(",,,,,,");
        } else {
            System.out.println(url);
        }
        System.out.println("////");
        return null;
    }
}
