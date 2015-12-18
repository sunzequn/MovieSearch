package com.sunzequn.search.data.persistence;

import com.sunzequn.search.data.persistence.neo4j.Database;
import org.junit.Test;

/**
 * Created by Sloriac on 15/12/18.
 */
public class Neo4jTest {

    @Test
    public void database() {

        Database database = Database.instance();
        System.out.println(database.getGraphDb());
    }
}
