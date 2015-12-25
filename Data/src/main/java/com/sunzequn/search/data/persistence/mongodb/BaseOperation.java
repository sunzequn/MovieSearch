package com.sunzequn.search.data.persistence.mongodb;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


/**
 * Created by Sloriac on 15/11/26.
 * <p>
 * Base operations for Mongodb.
 */
public abstract class BaseOperation {

    private Client client = Client.instance();

    public MongoDatabase getDatabase(String database) {
        return client.getDatabase(database);
    }

    public void createCollection(String database, String collection) {
        getDatabase(database).createCollection(collection);
    }

    public MongoCollection getCollection(String database, String collection) {
        return getDatabase(database).getCollection(collection);
    }

    public void insert(String database, String collection, Document document) {
        MongoCollection mongoCollection = getCollection(database, collection);
        mongoCollection.insertOne(document);
    }

    public FindIterable<Document> getAll(String database, String collection) {
        MongoCollection mongoCollection = getCollection(database, collection);
        return mongoCollection.find();
    }

}
