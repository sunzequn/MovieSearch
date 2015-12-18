package com.sunzequn.search.data.persistence.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * Created by Sloriac on 15/11/26.
 * <p>
 * Connect to a MongoDB instance running on the localhost on the default port 27017.
 * This class is thread safe.
 */
public class Client {


    /**
     * The Singleton instance of this class.
     */
    private static final Client instance = new Client();

    /**
     * <code>MongoClient</code> is a MongoDB client with internal connection pooling.
     * It is thread safe.
     */
    private MongoClient mongoClient = null;

    /**
     * Constructor for creating the Singleton instance of this class.
     */
    private Client() {
        mongoClient = new MongoClient();
    }

    /**
     * Returns the Singleton instance of this class.
     *
     * @return singleton instance
     */
    public static Client instance() {
        return instance;
    }

    /**
     * Access a database.
     *
     * @param databaseName the name of the database
     * @return a instance of <code>MongoDatabase</code>
     */
    public MongoDatabase getDatabase(String databaseName) {
        return mongoClient.getDatabase(databaseName);
    }


}
