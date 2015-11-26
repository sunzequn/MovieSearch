package com.sunzequn.search.data.mongodb;

import com.sunzequn.search.data.entity.YouKuMovie;
import com.sunzequn.search.data.persistence.mongodb.MovieOpeartion;
import org.junit.Test;

/**
 * Created by Sloriac on 15/11/26.
 */
public class MovieOperationTest {

    @Test
    public void insert() {

        YouKuMovie movie = new YouKuMovie();

        movie.setName("如果你还爱我");
        movie.setRating("10");
        movie.setType("青春/爱情");
        movie.setDate("2015-12-28");
        movie.setArea("大陆");
        movie.setDirectors("孙泽群");
        movie.setActors("孙泽群/张乃凡");
        movie.setAlias("你不爱我的");
        movie.setDuration("5年");

        System.out.println(movie);
//
//        MovieOpeartion movieOpeartion = new MovieOpeartion();
//        movieOpeartion.save(movie);


    }
}
