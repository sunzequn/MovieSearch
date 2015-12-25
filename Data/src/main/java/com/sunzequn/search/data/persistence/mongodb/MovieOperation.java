package com.sunzequn.search.data.persistence.mongodb;

import com.mongodb.client.FindIterable;
import com.sunzequn.search.data.entity.YouKuMovie;
import org.bson.Document;

import java.util.List;

/**
 * Created by Sloriac on 15/11/26.
 */
public class MovieOperation extends BaseOperation implements Operation {


    @Override
    public <T> void save(String database, String collection, T t) {
        Document document = Mapping.toDocument(t);
        insert(database, collection, document);
    }

    @Override
    public <T> List<T> findAll(String database, String collection, Class clazz) {
        FindIterable<Document> iterable = getAll(database, collection);
        return Mapping.toList(iterable, clazz);
    }

}
