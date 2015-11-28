package com.sunzequn.search.data.kg;

import com.sunzequn.search.data.kg.Dictionary.Namespace;
import com.sunzequn.search.data.kg.ontology.BaseBuild;
import org.apache.jena.ontology.*;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sloriac on 15/11/26.
 */
public class Test extends BaseBuild {

    @org.junit.Test
    public void test() {


        final String SOURCE = "http://hhu.edu.cn/ontology";
        final String NS = SOURCE + "#";
        OntModel baseOnt = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);

        OntClass animal = baseOnt.createClass(NS + "animal");
        OntClass plant = baseOnt.createClass(NS + "plant");

        OntClass tree = baseOnt.createClass(NS + "tree");
        tree.setSuperClass(plant);

        OntClass giraffe = baseOnt.createClass(NS + "giraffe");
        giraffe.setSuperClass(animal);

        OntClass leaf = baseOnt.createClass(NS + "leaf");
        leaf.setSuperClass(plant);

        OntClass rabbit = baseOnt.createClass(NS + "rabbit");
        rabbit.setSuperClass(animal);

        OntProperty eatProperty = baseOnt.createOntProperty(NS + "eat");
        Literal eatLiteral = baseOnt.createLiteral("eat");
        OntProperty propertyName = baseOnt.createObjectProperty(NS + "propertyName");
        eatProperty.addLiteral(propertyName, eatLiteral);


        giraffe.setPropertyValue(eatProperty, tree);
        rabbit.setPropertyValue(eatProperty, leaf);


        Individual individual = baseOnt.createIndividual(NS + "rabbit/兔子", rabbit);


//        OntClass search = baseOnt.getOntClass("sds");
//        System.out.println(search+"sdsadsadadsadasd");


        String queryString = "prefix ns:<http://hhu.edu.cn/ontology#> " +
                "prefix rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                "select ?animal { ?animal rdf:type ns:rabbit}";
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, baseOnt);

        ResultSet results = qexec.execSelect();

        for (; results.hasNext(); )

        {

            QuerySolution soln = results.nextSolution();

            Resource name = soln.getResource("animal");
            System.out.println(name);


        }

//        OntClass individual = baseOnt.createClass(NS+"sss");
//        individual.addSuperClass(furniture);
//
//
//
//
//        OntClass o = baseOnt.getOntClass(NS + "rabbit");
//        Iterator<OntProperty> iterator = o.listDeclaredProperties();
//        while (iterator.hasNext()) {
//            OntProperty ontProperty = iterator.next();
//            System.out.println(ontProperty);
//        }
//
//        OntProperty width = baseOnt.createOntProperty(NS+"width");
//        width.addLiteral(width, "width is");
////        bed.addLiteral(width, "1m");
////        Literal l = baseOnt.createTypedLiteral("1m");
//        individual.setPropertyValue(width, bed);
////        width.addDomain(individual);
////        width.addRange(bed);
////
//
//        OntProperty ontProperty = baseOnt.createOntProperty( NS + "isA" );
////        ontProperty.addDomain(bed);
////        ontProperty.addRange(furniture);
//        ontProperty.addLiteral(ontProperty, "isa");
//


//        ObjectProperty objectProperty = baseOnt.getObjectProperty(NS + "isA");
//        System.out.println(objectProperty.getLabel("en"));
//        System.out.println(objectProperty);
//
//        OntClass b = baseOnt.getOntClass(NS+"Bed");
//        Statement statement = b.getRequiredProperty(isa);

//        System.out.println(statement.getSubject());
//        System.out.println("---");
//        System.out.println(statement.getPredicate());
//        System.out.println("]]]]");
//        System.out.println(statement.getObject());
        //输出owl文件到文件系统

        writeRDF(baseOnt, Namespace.FILEPATH);

    }

    @org.junit.Test
    public void tt() {

        List<String> strings = new ArrayList<>();
        t(strings);
        System.out.println(strings);
    }

    public void t(List<String> strings) {
        strings.add("sdsd");
    }
}
