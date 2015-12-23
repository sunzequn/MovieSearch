package com.sunzequn.search.data.crawler.parser.tencent;

import com.sunzequn.search.data.crawler.parser.HtmlParser;
import com.sunzequn.search.data.crawler.parser.PullText;
import com.sunzequn.search.data.entity.TencentMovie;
import org.jsoup.nodes.Document;

/**
 * Created by Sloriac on 15/12/23.
 * <p>
 * Parse a HTML document from the website of Tencent Video.
 * It`s implement depends on your application.
 */
public class TencentHtmlParser extends PullText implements HtmlParser<TencentMovie> {

    @Override
    public TencentMovie parse(String url) {
        return null;
    }

    @Override
    public TencentMovie parseItem(Document document, String url) {
        return null;
    }
}
