package com.youche.utils.reflect;

import lombok.extern.log4j.Log4j2;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Log4j2
public class ReflectionUtils {

    public static <T> BeanInfo getBeanInfo(Class<T> beanType) {
        BeanInfo beanInfo = null;

        try {
            beanInfo = Introspector.getBeanInfo(beanType);
        } catch (IntrospectionException e) {
            log.error("", e);
        }

        return beanInfo;
    }

    public static <T> T newInstance(Class<T> beanType) {
        T instance = null;
        try {
            instance = beanType.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            log.error("", e);
        }

        return instance;
    }

    public static Map<String, PropertyDescriptor> propertyDescriptorMap(BeanInfo beanInfo) {
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        Map<String, PropertyDescriptor> propertyDescriptorMap = new HashMap<>();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            propertyDescriptorMap.put(propertyDescriptor.getName(), propertyDescriptor);
        }

        return propertyDescriptorMap;
    }

    public static Class forName(String className) {
        try {
            Class clazz = Class.forName(className);
            return clazz;
        } catch (ClassNotFoundException e) {
            throw new ReflectionException(e);
        }
    }

    public static <T> Method getAccessibleMethod(Class<T> type, String propertyName) {
        try {
            Method method = type.getDeclaredMethod(propertyName);
            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException e) {
            log.error("", e);
        }
        return null;
    }

    public static <T> Field getAccessibleField(Class<T> type, String propertyName) {
        try {
            Field propertyField = type.getDeclaredField(propertyName);
            propertyField.setAccessible(true);
            return propertyField;
        } catch (NoSuchFieldException e) {
            log.error("", e);
        }
        return null;
    }

    public static Object getMethodValue(Object obj, Method readMethod) {
        Object value = null;
        try {
            value = readMethod.invoke(obj);
        } catch (IllegalAccessException e) {
            log.error("", e);
        } catch (InvocationTargetException e) {
            log.error("", e);
        }
        return value;
    }

    public static void setMethodValue(Object obj, Method method, Object objects) {
        if (method == null) {
            return;
        }
        try {
            if (Timestamp.class.getName().equals(method.getParameterTypes()[0].getName())) {
                if (null != objects && !"null".equals(objects)) {
                    method.invoke(obj, new Timestamp((long) objects));
                }
            } else if (Date.class.getName().equals(method.getParameterTypes()[0].getName())) {
                if (null != objects && !"null".equals(objects)) {
                    method.invoke(obj, new Timestamp((long) objects));
                }
            } else if (java.sql.Date.class.getName().equals(method.getParameterTypes()[0].getName())) {
                if (null != objects && !"null".equals(objects)) {
                    method.invoke(obj, new Timestamp((long) objects));
                }
            } else {
                method.invoke(obj, objects);
            }
        } catch (IllegalAccessException e) {
            log.error("", e);
        } catch (InvocationTargetException e) {
            log.error("", e);
        }
    }

    public static class ReflectionException extends RuntimeException {

        public ReflectionException(Throwable cause) {
            super(cause);
        }
    }
}
