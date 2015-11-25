package com.sunzequn.search.data.crawler;

import com.sunzequn.search.data.crawler.parser.HtmlParser;
import org.junit.Test;

/**
 * Created by Sloriac on 15/11/25.
 */
public class HtmlParseTest {

    @Test
    public void parse() {
        HtmlParser htmlParser = new HtmlParser();
        htmlParser.parse();

    }
}
