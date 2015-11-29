package com.sunzequn.search.data.persistence.mongodb;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.sunzequn.search.data.entity.YouKuMovie;
import com.sunzequn.search.data.utils.ClassUtil;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sloriac on 15/11/27.
 */
public class Mapping {

    public static <T> Document toDocument(T t) {

        Document document = new Document();
        Class clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String key = field.getName();
            Object value = ClassUtil.getFieldValue(t, key);
            document.append(key, value);
            System.out.println(key + " : " + value);
        }
        return document;
    }

    public static <T> T toBean(Document document, Class clazz) {

        T t = null;
        try {
            t = (T) clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String key = field.getName();
            Object value = document.get(key);
            ClassUtil.setFieldValue(t, key, value);
        }
        return t;
    }

    public static <T> List<T> toList(FindIterable<Document> iterable, Class clazz) {
        List<T> list = new ArrayList<>();
        iterable.forEach((Block<Document>) document -> {
            list.add(toBean(document, clazz));
        });
        return list;
    }
}
