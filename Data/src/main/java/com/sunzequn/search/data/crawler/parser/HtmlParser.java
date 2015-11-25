package com.sunzequn.search.data.crawler.parser;

import com.sunzequn.search.data.crawler.exception.ConfigException;
import com.sunzequn.search.data.crawler.exception.ParseException;
import com.sunzequn.search.data.crawler.wrapper.UrlQueue;
import com.sunzequn.search.data.entity.YouKuMovie;
import com.sunzequn.search.data.utils.IOUtil;
import com.sunzequn.search.data.utils.PropertiesUtil;
import com.sunzequn.search.data.utils.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sloriac on 15/11/24.
 * <p>
 * Parse a HTML document.
 * It`s implement depends on your application.
 */
public class HtmlParser extends PullText {

    /**
     * The wrapper of unvisited urls and visited urls.
     */
    private UrlQueue urlQueue;

    /**
     * The first url.
     */
    private String baseUrl;

    /**
     * Constructor for <code>HtmlParser</code> with the initialization of variables.
     */
    public HtmlParser() {
        init();
    }

    /**
     * Initialize variables and put the base url to the queue for unvisited urls.
     */
    public void init() {
        urlQueue = UrlQueue.instance();
        try {
            baseUrl = PropertiesUtil.getValue("baseUrl");
            if (baseUrl == null) {
                throw new ConfigException("The value of: baseUrl is not defined in config.");
            } else {
                urlQueue.enUnvisitedUrlsQueue(baseUrl);
            }
        } catch (ConfigException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parse urls from <code>UrlQueue</code>.
     * This method only applies to <url>http://movie.youku.com/</url>.
     */
    public void parse() {
        //Determine whether it can continue to parse urls.
        try {
            if (!urlQueue.ifContinue()) {
                throw new ParseException("Failed to initialize UrlQueue, please check your config. ");
            }

            Document document;
            String url;
            String listPrefix = "http://www.youku.com/v_olist/";
            String itemPrefix = "http://www.youku.com/show_page/";
            IOUtil ioUtil = new IOUtil();

            while (urlQueue.ifContinue()) {
                //Get a unvisited url from <code>UrlQueue</code>.
                url = urlQueue.deUnvisitedUrlsQueue();
                document = this.pullFromUrl(url, 3000, HttpMethod.Get);
                //Mark the url as visited.
                urlQueue.markVisited(url);

                //There are two types of links: book list and book detail.
                if (url.startsWith(listPrefix)) {
                    //Parse the list of books.
                    parseList(document);

                } else if (url.startsWith(itemPrefix)) {
                    System.out.println("----------");
                    //Parse the page of the bottom.
                    YouKuMovie movie = parseItem(document);
                    if (movie != null) {
                        ioUtil.write(movie.toString());
                    }

                } else {
                    throw new ParseException("Can not parse the url: " + url);
                }
            }
            ioUtil.close();

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void parseList(Document document) {

        String webPrefix = "http://www.youku.com";

        Elements movies = document.select("div.p-link");
        for (Element movie : movies) {
            Element link = movie.select("a[href]").first();
            String movieLink = link.attr("href");
            System.out.println(movieLink);
            urlQueue.enUnvisitedUrlsQueue(movieLink);
        }
        //Parse the pages of bottom.
        Element page = document.select("li.next").first();
        Element nextPage = page.select("a").first();
        //When it has next page.
        if (nextPage != null) {
            String pageLink = nextPage.attr("href");
            System.out.println(webPrefix + pageLink);
            urlQueue.enUnvisitedUrlsQueue(webPrefix + pageLink);
        }
    }

    public YouKuMovie parseItem(Document document) {

        YouKuMovie movie = new YouKuMovie();
        String excluded = "优酷出品";
        Element detail = document.select("div.s_main").first();

        String type = detail.select("span.type").get(1).text();
        movie.setType(type);
        if (type.contains(excluded))
            return null;

        Elements dateNodes = detail.select("span.pub");
        if (dateNodes.size() <= 1) {
            return null;
        }
        String date = dateNodes.get(1).text();
        movie.setDate(date);

        Elements aliasNodes = detail.select("span.alias");
        if (aliasNodes.size() == 0) {
            return null;
        }
        String alias = aliasNodes.first().text();
        movie.setAlias(alias);

        String name = detail.select("span.name").first().text();
        movie.setName(name);
        String rating = detail.select("span.rating").first().text();
        movie.setRating(rating);
        String duration = detail.select("span.duration").first().text();
        movie.setDuration(duration);
        String directors = detail.select("span.director").first().text();
        movie.setDirectors(directors);
        String actors = detail.select("span.actor").first().text();
        movie.setActors(actors);
        String area = detail.select("span.area").first().text();
        movie.setArea(area);

        System.out.println(movie);
        return movie;

    }


}
