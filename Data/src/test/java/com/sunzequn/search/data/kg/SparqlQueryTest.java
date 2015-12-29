package com.sunzequn.search.data.kg;

import com.sunzequn.search.data.kg.ontology.SparqlQuery;
import com.sunzequn.search.data.parser.pattern.Mapper;
import org.junit.Test;

/**
 * Created by Sloriac on 15/12/29.
 */
public class SparqlQueryTest {

    public static void main(String[] args) {

        SparqlQuery query = new SparqlQuery();
        Mapper mapper = new Mapper();

//        String sparql1 = mapper.getSparql("洪金宝指导的电影有哪些").trim();
//        query.query(sparql1);
//
//        String sparql2 = mapper.getSparql("查传谊指导的电影的演员有哪些").trim();
//        query.query(sparql2);
//
//        String sparql3 = mapper.getSparql("捉妖记的导演是谁").trim();
//        query.query(sparql3);
//
//        String sparql4 = mapper.getSparql("捉妖记的演员有哪些").trim();
//        query.query(sparql4);

        String sparql5 = mapper.getSparql("成龙参演的电影有哪些").trim();
        query.query(sparql5);
    }
}
