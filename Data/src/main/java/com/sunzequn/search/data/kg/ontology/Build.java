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
 *
 * Build the ontology file of <code>YouKuMovie</code>.
 */
public class Build extends BaseBuild {

    private OntModel ontModel = super.ontModel;
    private OntClass movie;
    private OntClass person;
    private OntClass area;
    private OntClass director;
    private OntClass actor;

    private ObjectProperty direct;
    private ObjectProperty actIn;
    private ObjectProperty type;
    private ObjectProperty place;

    private DatatypeProperty rate;
    private DatatypeProperty date;
    private DatatypeProperty duration;
    private DatatypeProperty url;


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
        Create basic object properties.
         */
        direct = ontModel.createObjectProperty(Namespace.PROPERTY + Namespace.SLASH + Predicate.DIRECT);
        actIn = ontModel.createObjectProperty(Namespace.PROPERTY + Namespace.SLASH + Predicate.ACTIN);
        type = ontModel.createObjectProperty(Namespace.PROPERTY + Namespace.SLASH + Predicate.TYPE);
        place = ontModel.createObjectProperty(Namespace.PROPERTY + Namespace.SLASH + Predicate.AREA);
        /*
         Create basic data properties.
         */
        rate = ontModel.createDatatypeProperty(Namespace.PROPERTY + Namespace.SLASH + Predicate.RATE);
        date = ontModel.createDatatypeProperty(Namespace.PROPERTY + Namespace.SLASH + Predicate.DATE);
        duration = ontModel.createDatatypeProperty(Namespace.PROPERTY + Namespace.SLASH + Predicate.DURATION);
        url = ontModel.createDatatypeProperty(Namespace.PROPERTY + Namespace.SLASH + Predicate.URL);

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

        List<OntClass> typeClasses = buildSubClass(youKuMovie.getType(), Namespace.MOVIE, movie);
        addRelation(movieInstance, typeClasses, type);
        List<OntClass> areaClasses = buildSubClass(youKuMovie.getArea(), Namespace.AREA, area);
        addRelation(movieInstance, areaClasses, place);
        List<Individual> actorInstances = buildInstance(youKuMovie.getActors(), Namespace.ACTOR, actor);
        addRelation(actorInstances, movieInstance, actIn);
        List<Individual> directorInstances = buildInstance(youKuMovie.getDirectors(), Namespace.DIRECTOR, director);
        addRelation(directorInstances, movieInstance, direct);

        movieInstance.setOntClass(movie);
    }

    /**
     * Build a instance of <code>YouKuMovie</code>.
     *
     * @param youKuMovie the entity of <code>YouKuMovie</code> to be built
     * @param namespace  the URI of the instance
     * @return a <code>Individual</code> of the <code>YouKuMovie</code>
     */
    public Individual buildBasicMovie(YouKuMovie youKuMovie, String namespace) {

        Individual movieInstance = ontModel.createIndividual(namespace, null);

        Literal dateLiteral = ontModel.createLiteral(youKuMovie.getDate());
        Literal durationLiteral = ontModel.createLiteral(youKuMovie.getDuration());
        Literal urlLiteral = ontModel.createLiteral(youKuMovie.getUrl());
        Literal ratingLiteral = ontModel.createLiteral(youKuMovie.getRating());

        movieInstance.addProperty(date, dateLiteral);
        movieInstance.addProperty(duration, durationLiteral);
        movieInstance.addProperty(url, urlLiteral);
        movieInstance.addProperty(rate, ratingLiteral);

        return movieInstance;
    }

    public void write() {
        super.writeRDF(ontModel, Namespace.FILEPATH);
    }



}
