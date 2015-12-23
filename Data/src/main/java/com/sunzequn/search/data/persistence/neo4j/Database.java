package com.sunzequn.search.data.persistence.neo4j;

import com.sunzequn.search.data.exception.NeoException;
import com.sunzequn.search.data.utils.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

/**
 * Created by Sloriac on 15/12/17.
 * <p>
 * Connect to a Neo4j instance running on the localhost on the default port 7474.
 * This class is thread safe.
 */
public class Database {

    /**
     * The Singleton instance of this class.
     */
    private static final Database instance = new Database();

    private static final String NEO_PATH_KEY = "neopath";
    private GraphDatabaseService graphDb;

    /**
     * Constructor for creating the Singleton instance of this class.
     */
    private Database() {
        String path = PropertiesUtil.getValue(NEO_PATH_KEY);
        if (!StringUtils.isEmpty(path)) {
            graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(path);
            registerShutdownHook(graphDb);
        } else {
            try {
                throw new NeoException("The value neo4j is not defined in the config file.");
            } catch (NeoException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns the Singleton instance of this class.
     *
     * @return singleton instance
     */
    public static Database instance() {
        return instance;
    }

    /**
     * Registers a shutdown hook for the Neo4j instance
     * so that it shuts down nicely when the VM exits
     * (even if you "Ctrl-C" the running application).
     *
     * @param graphDb the neo4j instance
     */
    private static void registerShutdownHook(final GraphDatabaseService graphDb) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                graphDb.shutdown();
            }
        });
    }

    /**
     * Get the neo4j database instance.
     * The <code>GraphDatabaseService</code> instance can be shared among multiple threads.
     * Note however that you can’t create multiple instances pointing to the same database.
     * So this class uses Singleton pattern.
     *
     * @return the single instance of a database
     */
    public GraphDatabaseService getGraphDb() {
        return graphDb;
    }

}
