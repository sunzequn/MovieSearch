package com.sunzequn.search.data.kg.fusion.alignment;

import com.sunzequn.search.data.entity.TencentMovie;
import com.sunzequn.search.data.entity.YouKuMovie;
import com.sunzequn.search.data.kg.fusion.similarity.JaccardDis;
import com.sunzequn.search.data.kg.fusion.similarity.JaroWinklerDis;
import com.sunzequn.search.data.kg.fusion.similarity.LevenshteinDis;
import com.sunzequn.search.data.persistence.mongodb.MovieOperation;

/**
 * Created by Sloriac on 15/12/26.
 */
public class EntityMapper {

    private static final double[] W = {0.4, 0.2, 0.2, 0.2};

    public void mapping(YouKuMovie youKuMovie, TencentMovie tencentMovie) {

        double nameSimilarity = JaroWinklerDis.compute(youKuMovie.getName().toCharArray(),
                tencentMovie.getName().toCharArray());
        double directorSimilarity = LevenshteinDis.compute(youKuMovie.directors().toCharArray(),
                tencentMovie.directors().toCharArray());
        double actorSimilarity = JaccardDis.compute(youKuMovie.actors(), tencentMovie.actors());


    }
}
