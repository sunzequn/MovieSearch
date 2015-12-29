package com.sunzequn.search.data.kg.ontology;

import com.sunzequn.search.data.entity.YouKuMovie;
import com.sunzequn.search.data.persistence.mongodb.MovieOperation;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Resource;

import java.util.List;

/**
 * Created by Sloriac on 15/11/28.
 */
public class Main {

    public static void main(String[] args){

        Build build = new Build();
        MovieOperation movieOperation = new MovieOperation();
        List<YouKuMovie> movieList = movieOperation.findAll("MovieSearch", "YouKuMovie", YouKuMovie.class);
        for (YouKuMovie movie : movieList) {
            System.out.println(movie);
            build.buildMovie(movie);
        }
        build.write();
    }
}
