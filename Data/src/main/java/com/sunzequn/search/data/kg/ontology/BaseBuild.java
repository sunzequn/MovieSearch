package com.sunzequn.search.data.kg.ontology;

import com.sunzequn.search.data.kg.constant.Namespace;
import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sloriac on 15/11/28.
 *
 * Provide basic methods for a specific <code>OntModel</code> to build ontology and write file.
 * This is a abstract class and other concrete classes that build ontology should extend it.
 */
public abstract class BaseBuild {

    protected OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);

    public OntModel getOntModel() {
        return ontModel;
    }

    /**
     * Build subclasses of a specific <code>OntClass</code>.
     *
     * @param suffixes   the list of suffixes of these subclasses
     * @param namespace  the mutual prefix of these subclasses`s URIs
     * @param superClass the superclass of these subclasses
     * @return these subclasses
     */
    public List<OntClass> buildSubClass(List<String> suffixes, String namespace, OntClass superClass) {

        List<OntClass> entities = new ArrayList<>();
        for (String suffix : suffixes) {
            String clazzNamespace = namespace + Namespace.SLASH + suffix;
            OntClass entity = ontModel.getOntClass(clazzNamespace);
            if (entity == null) {
                entity = ontModel.createClass(clazzNamespace);
                entity.setSuperClass(superClass);
            }
            entities.add(entity);
        }
        return entities;
    }

    /**
     * Create instances of a <code>OntClass</code>
     *
     * @param instances  the list of persons
     * @param namespace  the namespace of the type of <code>OntClass</code>
     * @param superClass the super class of instances
     * @return a list of <code>Individual</code>
     */
    public List<Individual> buildInstance(List<String> instances, String namespace, OntClass superClass) {

        List<Individual> individuals = new ArrayList<>();
        for (String instance : instances) {
            String instanceNamespace = namespace + Namespace.SLASH + instance;
            Individual individual = ontModel.getIndividual(instanceNamespace);
            if (individual == null) {
                individual = ontModel.createIndividual(instanceNamespace, superClass);
            }
            individuals.add(individual);
        }
        return individuals;
    }

    /**
     * Add property between an <code>OntResource</code> and some <code>OntResource</code>.
     *
     * @param clazz    a <code>OntResource</code>
     * @param clazzes  a list of <code>OntResource</code>
     * @param property the property between them
     * @param <T>      a generic type of <code>OntResource</code>
     */
    public <T extends OntResource> void addRelation(T clazz, List<T> clazzes, OntProperty property) {
        for (T t : clazzes) {
            clazz.addProperty(property, t);
        }
    }

    /**
     * Add property between some <code>OntResource</code> and an <code>OntResource</code>.
     *
     * @param clazzes  a list of <code>OntResource</code>
     * @param clazz    a <code>OntResource</code>
     * @param property the property between them
     * @param <T>      a generic type of <code>OntResource</code>
     */
    public <T extends OntResource> void addRelation(List<T> clazzes, T clazz, OntProperty property) {
        for (T t : clazzes) {
            t.addProperty(property, clazz);
        }
    }

    /**
     * Add property between an <code>Individual</code> and some <code>OntClass</code>.
     *
     * @param individual an <code>Individual</code>
     * @param clazzes    some <code>OntClass</code>
     * @param property   the property between them
     */
    public void addRelation(Individual individual, List<OntClass> clazzes, OntProperty property) {
        for (OntClass clazz : clazzes) {
            individual.addProperty(property, clazz);
        }
    }

    /**
     * Provide basic methods for writing RDF/XML files.
     *
     * @param ontModel a class defined in Jena for build ontology
     * @param filePath the file path in which you want to save ontology
     */
    public void writeRDF(OntModel ontModel, String filePath) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(filePath);
            RDFWriter rdfWriter = ontModel.getWriter("RDF/XML");
            rdfWriter.setProperty("showXMLDeclaration", "true");
            rdfWriter.setProperty("showDoctypeDeclaration", "true");
            rdfWriter.write(ontModel, fileOutputStream, null);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
