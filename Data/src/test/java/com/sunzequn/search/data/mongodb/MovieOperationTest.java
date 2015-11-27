package com.sunzequn.search.data.mongodb;

import com.sunzequn.search.data.entity.YouKuMovie;
import com.sunzequn.search.data.persistence.mongodb.MovieOpeartion;
import org.junit.Test;

/**
 * Created by Sloriac on 15/11/26.
 */
public class MovieOperationTest {

    MovieOpeartion movieOpeartion = new MovieOpeartion();

    @Test
    public void save() {
        YouKuMovie movie = new YouKuMovie();

        System.out.println(movie);
//        movieOpeartion.save(movie);
    }

    @Test
    public void findAll() {
        System.out.println(movieOpeartion.findAll("MovieSearch", "YouKuMovie"));
    }
}
