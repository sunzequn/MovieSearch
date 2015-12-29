package com.sunzequn.search.data.kg;

import com.sunzequn.search.data.kg.ontology.SparqlQuery;
import com.sunzequn.search.data.parser.pattern.Mapper;
import org.junit.Test;

/**
 * Created by Sloriac on 15/12/29.
 */
public class SparqlQueryTest {

    public static void main(String[] args) {

        Mapper mapper = new Mapper();
        String sparql = mapper.getSparql("孙泽群指导的电影").trim();
        SparqlQuery query = new SparqlQuery();
//        String sparql = "prefix NS:<http://sunzequn.com/ontology/> prefix propertyNS:<http://sunzequn.com/ontology/属性/> prefix directorNS:<http://sunzequn.com/ontology/人/导演/> prefix rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> select ?movie { ?movie rdf:type NS:电影 . directorNS:孙泽群 propertyNS:导演 ?movie}";
        query.query(sparql);
    }
}
