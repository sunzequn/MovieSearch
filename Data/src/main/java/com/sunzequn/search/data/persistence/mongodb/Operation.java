package com.sunzequn.search.data.persistence.mongodb;

import java.util.List;

/**
 * Created by Sloriac on 15/11/27.
 */
public interface Operation {

    public <T> void save(String collection, T t);

    public <T> List<T> findAll(String database, String collection);
}
