package com.sunzequn.search.data.kg.ontology;

import com.sunzequn.search.data.entity.YouKuMovie;
import com.sunzequn.search.data.persistence.mongodb.MovieOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Resource;
import org.junit.Test;

import java.util.List;

/**
 * Created by Sloriac on 15/11/28.
 */
public class Main {

    @Test
    public void run() {

        Build build = new Build();
        MovieOperation movieOperation = new MovieOperation();
        List<YouKuMovie> movieList = movieOperation.findAll("MovieSearch", "YouKuMovie");
        for (YouKuMovie movie : movieList) {
            System.out.println(movie);
            build.buildMovie(movie);
        }
        build.write();

//        OntModel ontModel = build.getOntModel();
//        String queryString = "prefix ns:<http://sunzequn.com/ontology/电影/> " +
//                "prefix rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
//                "select ?movie { ?movie rdf:type ns:爱情}";
//        Query query = QueryFactory.create(queryString);
//        QueryExecution qexec = QueryExecutionFactory.create(query, ontModel);
//
//        ResultSet results = qexec.execSelect();
//
//        for (; results.hasNext(); )
//
//        {
//            QuerySolution soln = results.nextSolution();
//
//            Resource name = soln.getResource("movie");
//            System.out.println(name);
//
//
//        }

    }
}
