package com.sunzequn.search.data.kg.ontology;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Sloriac on 15/11/28.
 */
public abstract class BaseBuild {

    protected OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);

    public OntModel getOntModel() {
        return ontModel;
    }

    public void writeRDF(OntModel ontModel, String filePath) {

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        RDFWriter rdfWriter = ontModel.getWriter("RDF/XML");
        rdfWriter.setProperty("showXMLDeclaration", "true");
        rdfWriter.setProperty("showDoctypeDeclaration", "true");
        rdfWriter.write(ontModel, fileOutputStream, null);
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
