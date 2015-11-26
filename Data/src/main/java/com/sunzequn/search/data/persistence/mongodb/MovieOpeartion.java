package com.sunzequn.search.data.persistence.mongodb;

import com.sunzequn.search.data.entity.YouKuMovie;
import com.sunzequn.search.data.utils.ClassUtil;
import org.bson.Document;

import java.lang.reflect.Field;

/**
 * Created by Sloriac on 15/11/26.
 */
public class MovieOpeartion extends BaseOperation {

    private static final String database = "MovieSearch";
    private static final String youKuMovieCollection = "YouKuMovie";

    public <T> void save(T t) {
        Document document = new Document();
        Class clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String key = field.getName();
            Object value = ClassUtil.getFieldValue(t, key);
            document.append(key, value);
            System.out.println(key + " : " + value);
        }
        insert(database, youKuMovieCollection, document);
    }
}
