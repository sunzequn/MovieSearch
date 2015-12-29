package com.sunzequn.search.data.kg.ontology;

import com.sunzequn.search.data.kg.constant.Namespace;
import org.apache.jena.ontology.OntDocumentManager;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;

/**
 * Created by Sloriac on 15/12/29.
 */
public class SparqlQuery {

    OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);

    public SparqlQuery() {
        ontModel.read(Namespace.FILEPATH);
    }

    public void query(String sparql) {
        Query query = QueryFactory.create(sparql);
        QueryExecution qexec = QueryExecutionFactory.create(query, ontModel);
        ResultSet results = qexec.execSelect();
        for (; results.hasNext(); ) {
            QuerySolution soln = results.nextSolution();
            System.out.println(soln);
//            Resource name = soln.getResource("movie");
//            System.out.println(name);
        }
    }
}
