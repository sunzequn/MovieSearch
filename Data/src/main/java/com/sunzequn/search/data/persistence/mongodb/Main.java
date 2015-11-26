package com.sunzequn.search.data.persistence.mongodb;

import org.junit.Test;

/**
 * Created by Sloriac on 15/11/26.
 */
public class Main extends BaseOperation {

    @Test
    public void init() {
        super.createCollection("MovieSearch", "YouKuMovie");
    }
}
