package com.sunzequn.search.data.crawler.parser;

import org.jsoup.nodes.Document;

/**
 * Created by Sloriac on 15/12/23.
 * <p>
 * The interface defines the standards for it`s implementation classes.
 */
public interface HtmlParser<T> {

    /**
     * Parse the specific web content.
     *
     * @param url the url of this web
     * @return en entity
     */
    public T parse(String url);

    /**
     * Extract an entity from the web.
     *
     * @param document web content
     * @param url      the url of this web
     * @return an entity
     */
    public T parseItem(Document document, String url);

}
