package com.sunzequn.search.data.kg.fusion.alignment;

import com.sunzequn.search.data.entity.Movie;
import com.sunzequn.search.data.entity.TencentMovie;
import com.sunzequn.search.data.entity.YouKuMovie;
import com.sunzequn.search.data.kg.fusion.similarity.JaccardDis;
import com.sunzequn.search.data.kg.fusion.similarity.JaroWinklerDis;
import com.sunzequn.search.data.kg.fusion.similarity.LevenshteinDis;
import com.sunzequn.search.data.kg.fusion.similarity.VSM;
import com.sunzequn.search.data.persistence.mongodb.MovieOperation;
import org.ujmp.core.DenseMatrix;
import org.ujmp.core.Matrix;

import java.util.List;

/**
 * Created by Sloriac on 15/12/26.
 *
 * The class is used to match movies from YouKu and Tencent.
 */
public class EntityMapper {

    private static final double[] W = {0.4, 0.2, 0.2, 0.2};
    private static final double THRESHOLD = 0.7;
    private static final String DATABASE = "MovieSearch";
    private static final String YOUKU_MOVIE_COLLECTION = "YouKuMovie";
    private static final String TENCENT_MOVIE_COLLECTION = "TencentMovie";
    private static final String MOVIE_COLLECTION = "Movie";
    private static VSM vsm = new VSM();

    /**
     * Calculate the similarity of two movies.
     *
     * @param youKuMovie   the movie from Youku
     * @param tencentMovie the movie from tencent
     * @return a value representing the similarity of movies, the closer to 1, the more similar.
     */
    public static double mapping(YouKuMovie youKuMovie, TencentMovie tencentMovie) {

        double nameSimilarity = JaroWinklerDis.compute(youKuMovie.getName().toCharArray(),
                tencentMovie.getName().toCharArray());
        double directorSimilarity = LevenshteinDis.compute(youKuMovie.directors().toCharArray(),
                tencentMovie.directors().toCharArray());
        double actorSimilarity = JaccardDis.compute(youKuMovie.actors(), tencentMovie.actors());
        double generalSimilarity = vsm.cosine(youKuMovie.getGeneral(), tencentMovie.getGeneral());

        Matrix matrix = DenseMatrix.Factory.zeros(4, 1);
        matrix.setAsDouble(nameSimilarity, 0, 0);
        matrix.setAsDouble(directorSimilarity, 1, 0);
        matrix.setAsDouble(actorSimilarity, 2, 0);
        matrix.setAsDouble(generalSimilarity, 3, 0);

        Matrix w = DenseMatrix.Factory.importFromArray(W);
        return w.mtimes(matrix).getAsDouble(0, 0);
    }

    public static void main(String[] args) {
        int num = 0;
        MovieOperation movieOperation = new MovieOperation();
        List<YouKuMovie> youKuMovies = movieOperation.findAll(DATABASE, YOUKU_MOVIE_COLLECTION, YouKuMovie.class);
        List<TencentMovie> tencentMovies = movieOperation.findAll(DATABASE, TENCENT_MOVIE_COLLECTION, TencentMovie.class);
        for (int i = 752; i < youKuMovies.size(); i++) {
            YouKuMovie youKuMovie = youKuMovies.get(i);
            Movie movie = new Movie(youKuMovie);
            for (TencentMovie tencentMovie : tencentMovies) {
                double value = mapping(youKuMovie, tencentMovie);
                if (value >= THRESHOLD) {
                    num++;
                    movie.setTencentUrl(tencentMovie.getUrl());
                    System.out.println("###################");
                    System.out.println(youKuMovie);
                    System.out.println(tencentMovie);
                    break;
                }
            }
            if (movie.getTencentUrl() == null) {
                movie.setTencentUrl("暂无");
            }
            movieOperation.save(DATABASE, MOVIE_COLLECTION, movie);
        }
        System.out.println("Mapped entites: " + num);
    }
}
