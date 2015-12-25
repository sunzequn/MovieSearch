package com.sunzequn.search.data.parser.dictionary;

import com.sunzequn.search.data.entity.YouKuMovie;
import com.sunzequn.search.data.parser.FilePath;
import com.sunzequn.search.data.persistence.mongodb.MovieOperation;
import com.sunzequn.search.data.utils.WriteUtil;

import java.util.List;

/**
 * Created by Sloriac on 15/12/4.
 */
public class CreateDictionary {

    public static void main(String[] args){

        MovieOperation movieOperation = new MovieOperation();
        List<YouKuMovie> movies = movieOperation.findAll("MovieSearch", "YouKuMovie", YouKuMovie.class);
        System.out.println(movies.size());
        WriteUtil actorWriter = new WriteUtil(FilePath.actorDictionary, true);
        WriteUtil movieWriter = new WriteUtil(FilePath.movieDictionary, true);
        WriteUtil directorWriter = new WriteUtil(FilePath.directorDictionary, true);
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
