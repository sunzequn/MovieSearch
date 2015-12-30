package com.sunzequn.search.data.kg.ontology;

import com.sunzequn.search.data.kg.constant.Namespace;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.ModelFactory;

/**
 * Created by Sloriac on 15/12/29.
 *
 * The class is used to query knowledge base.
 */
public class SparqlQuery {

    OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);

    public SparqlQuery() {
        ontModel.read(Namespace.FILEPATH);
    }

    /**
     * Execute a sparql query and print the results.
     *
     * @param sparql a sparql query
     */
    public void query(String sparql) {
        if (sparql == null) {
            System.out.println("sqarql error");
            return;
        }
        Query query = QueryFactory.create(sparql);
        QueryExecution qexec = QueryExecutionFactory.create(query, ontModel);
        ResultSet results = qexec.execSelect();
        for (; results.hasNext(); ) {
            QuerySolution soln = results.nextSolution();
            System.out.println(soln);
        }
    }
}
