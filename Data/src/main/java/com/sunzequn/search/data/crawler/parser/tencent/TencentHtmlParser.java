package com.sunzequn.search.data.crawler.parser.tencent;

import com.sunzequn.search.data.crawler.parser.HtmlParser;
import com.sunzequn.search.data.crawler.parser.HttpMethod;
import com.sunzequn.search.data.crawler.parser.PullText;
import com.sunzequn.search.data.entity.TencentMovie;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

/**
 * Created by Sloriac on 15/12/23.
 * <p>
 * Parse a HTML document from the website of Tencent Video.
 * It`s implement depends on your application.
 */
public class TencentHtmlParser extends PullText implements HtmlParser<TencentMovie> {

    private static final int TIMEOUT = 5000;

    @Override
    public TencentMovie parse(String url) {
        Document document = pullFromUrl(url, TIMEOUT, HttpMethod.Get);
        if (document != null) {
            TencentMovie movie = parseItem(document, url);
            return movie;
        }
        return null;
    }

    @Override
    public TencentMovie parseItem(Document document, String url) {
        Elements content = document.select("div.intro_inner");
        if (content != null && content.size() > 0) {
            TencentMovie movie = new TencentMovie();
            movie.setUrl(url);
            Elements titles = content.select("div.intro_title");
            if (titles.size() == 0) {
                return null;
            }
            String title = titles.get(0).text();
            movie.setName(title);

            Elements infos = content.select("li.list_item");
            for (Element info : infos) {
                String key = info.select("span.list_lbl").text();
                String value = info.select("ul.name_list").text();
                if (key.startsWith("导演")) {
                    movie.setDirectors(value);
                } else if (key.startsWith("主演")) {
                    movie.setActors(value);
                } else if (key.startsWith("简介")) {
                    movie.setGeneral(info.select("p.intro_summary").text());
                }
            }
            return movie;
        }
        return null;
    }

    @Test
    public void test() {
        parse("http://v.qq.com/cover/c/c17yx4mna94c9rw.html");
    }
}
