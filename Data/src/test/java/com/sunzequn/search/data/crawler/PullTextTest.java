package com.sunzequn.search.data.crawler;

import com.sunzequn.search.data.crawler.parser.HttpMethod;
import com.sunzequn.search.data.crawler.parser.PullText;
import org.junit.Test;

/**
 * Created by Sloriac on 15/11/25.
 */
public class PullTextTest extends PullText {

    @Test
    public void pull() {
        System.out.println(this.pullFromUrl("http://music.163.com/#/discover/artist/cat?id=1001", 3000, HttpMethod.Get));
    }
}
