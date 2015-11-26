package com.sunzequn.search.data.persistence.mongodb;

import com.mongodb.client.MongoCollection;
import org.bson.Document;


/**
 * Created by Sloriac on 15/11/26.
 * <p>
 * Base operations for Mongodb.
 */
public abstract class BaseOperation {

    private Client client = Client.instance();

    public void createCollection(String database, String collection) {
        client.getDatabase(database).createCollection(collection);
    }

    public void insert(String dababase, String collection, Document document) {
        MongoCollection mongoCollection = client.getDatabase(dababase).getCollection(collection);
        mongoCollection.insertOne(document);
    }

}
