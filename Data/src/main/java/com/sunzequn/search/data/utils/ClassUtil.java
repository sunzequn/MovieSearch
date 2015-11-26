package com.sunzequn.search.data.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Sloriac on 15/11/26.
 */
public class ClassUtil {

    /**
     * Get the value of a property by it`s name.
     *
     * @param t         The object from which we retrieve values.
     * @param fieldName The name of the property.
     * @return the value of the property.
     */
    public static <T> Object getFieldValue(T t, String fieldName) {
        try {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, t.getClass());
            Method method = propertyDescriptor.getReadMethod();
            if (method == null) {
                throw new RuntimeException("No read method for bean property "
                        + t.getClass() + " " + fieldName);
            }
            Object value = method.invoke(t, new Object[]{});
            return value;
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
