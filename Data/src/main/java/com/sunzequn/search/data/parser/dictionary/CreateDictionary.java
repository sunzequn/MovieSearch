package com.sunzequn.search.data.parser.dictionary;

import com.sunzequn.search.data.entity.YouKuMovie;
import com.sunzequn.search.data.persistence.mongodb.MovieOperation;
import com.sunzequn.search.data.utils.WriteUtil;
import org.junit.Test;

import java.util.List;

/**
 * Created by Sloriac on 15/12/4.
 */
public class CreateDictionary {

    private static final String actorDictionary = "src/main/resources/dictionary/actor.txt";
    private static final String movieDictionary = "src/main/resources/dictionary/movie.txt";
    private static final String directorDictionary = "src/main/resources/dictionary/director.txt";

    @Test
    public void run() {
        MovieOperation movieOperation = new MovieOperation();
        List<YouKuMovie> movies = movieOperation.findAll("MovieSearch", "YouKuMovie");
        System.out.println(movies.size());
        WriteUtil actorWriter = new WriteUtil(actorDictionary, true);
        WriteUtil movieWriter = new WriteUtil(movieDictionary, true);
        WriteUtil directorWriter = new WriteUtil(directorDictionary, true);
        for (YouKuMovie movie : movies) {
            movieWriter.write(movie.getName());
            List<String> actors = movie.getActors();
            for (String acotr : actors) {
                actorWriter.write(acotr);
            }
            List<String> directors = movie.getDirectors();
            for (String direcotr : directors) {
                directorWriter.write(direcotr);
            }
        }
        actorWriter.close();
        movieWriter.close();
        directorWriter.close();

    }
}
