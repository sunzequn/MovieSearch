package com.sunzequn.search.data.parser;

import com.sunzequn.search.data.kg.ontology.SparqlQuery;
import com.sunzequn.search.data.parser.pattern.Mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Sloriac on 15/12/30.
 */
public class Main {

    public static void main(String[] args) {
        SparqlQuery query = new SparqlQuery();
        Mapper mapper = new Mapper();
        BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                System.out.print("please input a sentence (input null if you want to exit) : ");
                String sentence = strin.readLine();
                if (sentence.equals("null")) {
                    return;
                }
                String sparql = mapper.getSparql(sentence);
                if (sparql == null) {
                    System.out.println("No temalate mapped");
                    return;
                }
                sparql = sparql.trim();
                query.query(sparql);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
