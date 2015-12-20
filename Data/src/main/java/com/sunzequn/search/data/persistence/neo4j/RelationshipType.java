package com.sunzequn.search.data.persistence.neo4j;

/**
 * Created by Sloriac on 15/12/19.
 * <p>
 * A relationship interface which defines relationships between nodes.
 */
public interface RelationshipType {
    /**
     * A single method that returns the relationship name.
     *
     * @return the relationship name
     */
    public String name();
}
