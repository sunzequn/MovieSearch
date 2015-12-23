package com.sunzequn.search.data.crawler.parser.youku;

import com.sunzequn.search.data.crawler.parser.HtmlParser;
import com.sunzequn.search.data.crawler.parser.HttpMethod;
import com.sunzequn.search.data.crawler.parser.PullText;
import com.sunzequn.search.data.entity.YouKuMovie;
import com.sunzequn.search.data.exception.ParseException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Sloriac on 15/11/24.
 * <p>
 * Parse a HTML document from the website of Youku.
 * It`s implement depends on your application.
 */
public class YouKuHtmlParser extends PullText implements HtmlParser<YouKuMovie> {

    private static final String DEFAULT_GENERAL = "暂无概况";

    /**
     * Parse urls from <code>UrlQueue</code>.
     * This method only applies to <url>http://movie.youku.com/</url>.
     *
     * @param url an unvisited url
     * @return a <code>YouKuMovie</code> if this web contains a movie otherwise null.
     */
    @Override
    public YouKuMovie parse(String url) {
        //Determine whether it can continue to parse urls.
        try {
            Document document;
            String listPrefix = "http://www.youku.com/v_olist/";
            String itemPrefix = "http://www.youku.com/show_page/";
            document = pullFromUrl(url, 5000, HttpMethod.Get);
            //There are two types of links: book list and book detail.
            if (url.startsWith(itemPrefix)) {
                //Parse the page of the bottom.
                YouKuMovie movie = parseItem(document, url);
                if (movie != null) {
                    return movie;
                }
            } else {
                throw new ParseException("Can not parse the url: " + url);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Parse the web of movie info.
     *
     * @param document source code of the web
     * @param url      the link of this movie
     * @return a <code>YouKuMovie</code> if this web contains a movie otherwise null.
     */
    @Override
    public YouKuMovie parseItem(Document document, String url) {

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

        Elements generals = detail.select("span.long");
        if (generals != null && generals.size() > 0) {
            String general = generals.first().text();
            movie.setGeneral(general);
        } else {
            movie.setGeneral(DEFAULT_GENERAL);
        }

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
        movie.setUrl(url);
        return movie;

    }

}
