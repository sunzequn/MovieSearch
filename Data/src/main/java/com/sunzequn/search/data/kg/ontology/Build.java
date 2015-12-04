package com.sunzequn.search.data.kg.ontology;

import com.sunzequn.search.data.entity.YouKuMovie;
import com.sunzequn.search.data.kg.constant.Predicate;
import org.apache.jena.ontology.*;
import com.sunzequn.search.data.kg.constant.Namespace;
import org.apache.jena.rdf.model.Literal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sloriac on 15/11/27.
 */
public class Build extends BaseBuild {

    private OntModel ontModel = super.ontModel;
    private OntClass movie;
    private OntClass person;
    private OntClass area;
    private OntClass director;
    private OntClass actor;

    private OntProperty direct;
    private OntProperty actIn;
    private OntProperty rate;
    private OntProperty date;
    private OntProperty duration;
    private OntProperty url;
    private OntProperty type;
    private OntProperty place;

    /**
     * Default constructor for <code>Build</code> with initializations.
     */
    public Build() {
        init();
    }

    @Override
    public OntModel getOntModel() {
        return ontModel;
    }

    /**
     * Create basic supper classes.
     */
    public void init() {

        /*
        Create subclasses of <code>Thing</code>
         */
        movie = ontModel.createClass(Namespace.MOVIE);
        person = ontModel.createClass(Namespace.PERSON);
        area = ontModel.createClass(Namespace.AREA);
        /*
        Create basic subclasses of classes above.
         */
        director = ontModel.createClass(Namespace.DIRECTOR);
        actor = ontModel.createClass(Namespace.ACTOR);
        director.setSuperClass(person);
        actor.setSuperClass(person);

        /*
        Create basic properties.
         */
        direct = ontModel.createOntProperty(Namespace.PROPERTY + Namespace.SLASH + Predicate.DIRECT);
        actIn = ontModel.createOntProperty(Namespace.PROPERTY + Namespace.SLASH + Predicate.ACTIN);
        rate = ontModel.createOntProperty(Namespace.PROPERTY + Namespace.SLASH + Predicate.RATE);
        date = ontModel.createOntProperty(Namespace.PROPERTY + Namespace.SLASH + Predicate.DATE);
        duration = ontModel.createOntProperty(Namespace.PROPERTY + Namespace.SLASH + Predicate.DURATION);
        url = ontModel.createOntProperty(Namespace.PROPERTY + Namespace.SLASH + Predicate.URL);
        type = ontModel.createOntProperty(Namespace.PROPERTY + Namespace.SLASH + Predicate.TYPE);
        place = ontModel.createOntProperty(Namespace.PROPERTY + Namespace.SLASH + Predicate.AREA);
    }

    /**
     * Build a movie entity and add it to the basic knowledge graph.
     *
     * @param youKuMovie the entity to added to the knowledge graph
     */
    public void buildMovie(YouKuMovie youKuMovie) {

        String movieNamespace = Namespace.MOVIE + Namespace.SLASH + youKuMovie.getName();
        Individual movieInstance = ontModel.getIndividual(movieNamespace);
        if (movieInstance == null) {
            movieInstance = buildBasicMovie(youKuMovie, movieNamespace);
        }

        List<OntClass> typeClasses = buildClass(youKuMovie.getType(), Namespace.MOVIE, movie);
        addOntClass(movieInstance, typeClasses, type);
        List<OntClass> areaClasses = buildClass(youKuMovie.getArea(), Namespace.AREA, area);
        addRelation(movieInstance, areaClasses, place);
        List<Individual> actorInstances = buildInstance(youKuMovie.getActors(), Namespace.ACTOR, actor);
        addRelation(actorInstances, movieInstance, actIn);
        List<Individual> directorInstances = buildInstance(youKuMovie.getDirectors(), Namespace.DIRECTOR, director);
        addRelation(directorInstances, movieInstance, direct);
    }

    public Individual buildBasicMovie(YouKuMovie youKuMovie, String namespace) {

        Individual movieInstance = ontModel.createIndividual(namespace, null);

        Literal dateLiteral = ontModel.createLiteral(youKuMovie.getDate());
        Literal durationLiteral = ontModel.createLiteral(youKuMovie.getDuration());
        Literal urlLiteral = ontModel.createLiteral(youKuMovie.getUrl());
        Literal ratingLiteral = ontModel.createLiteral(youKuMovie.getRating());

        movieInstance.setPropertyValue(date, dateLiteral);
        movieInstance.setPropertyValue(duration, durationLiteral);
        movieInstance.setPropertyValue(url, urlLiteral);
        movieInstance.setPropertyValue(rate, ratingLiteral);

        return movieInstance;
    }


    public List<OntClass> buildClass(List<String> classes, String namespace, OntClass superClass) {

        List<OntClass> entities = new ArrayList<>();
        for (String clazz : classes) {
            String clazzNamespace = namespace + Namespace.SLASH + clazz;
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
     * Create instances of Person such a director or a actor.
     *
     * @param instances  the list of persons
     * @param namespace  the namespace of the type of persons,such as Director or Actor
     * @param superClass the super class of persons,such as Director or Actor
     * @return a list of <code>Individual</code>
     */
    public List<Individual> buildInstance(List<String> instances, String namespace, OntClass superClass) {

        List<Individual> individuals = new ArrayList<>();
        for (String person : instances) {
            String personNamespace = namespace + Namespace.SLASH + person;
            Individual individual = ontModel.getIndividual(personNamespace);
            if (individual == null) {
                individual = ontModel.createIndividual(personNamespace, superClass);
            }
            individuals.add(individual);
        }
        return individuals;
    }

    public <T extends OntResource> void addRelation(T clazz, List<T> clazzes, OntProperty property) {
        for (T t : clazzes) {
            clazz.setPropertyValue(property, t);
        }
    }

    public <T extends OntResource> void addRelation(List<T> clazzes, T clazz, OntProperty property) {
        for (T t : clazzes) {
            t.setPropertyValue(property, clazz);
        }
    }

    public void write() {
        super.writeRDF(ontModel, Namespace.FILEPATH);
    }

    public void addRelation(Individual individual, List<OntClass> clazzes, OntProperty property) {
        for (OntClass clazz : clazzes) {
            individual.setPropertyValue(property, clazz);
        }
    }

    public void addOntClass(Individual individual, List<OntClass> clazzes, OntProperty property) {
        for (OntClass clazz : clazzes) {
            individual.setOntClass(clazz);
        }
    }


}
