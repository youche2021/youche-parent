package com.youche.utils.convert;

import com.youche.utils.lang.StringNameUtils;
import com.youche.utils.reflect.CastUtils;
import com.youche.utils.reflect.ReflectionUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.CollectionUtils;

import java.beans.BeanInfo;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

/**
 * Bean 转换工具类
 */
@Log4j2
public class BeanConvertUtils {

    /**
     * 原样转换
     * @param beanObject
     * @param <T>
     * @return
     */
    public static <T> Map beanToMap(T beanObject) {
        return beanToMap(beanObject, false);
    }

    /**
     * 提供参数，默认是转成 _ 小写格式
     *
     * @param beanObject
     * @param camelToUnderline
     * @param <T>
     * @return
     */
    public static <T> Map beanToMap(T beanObject, boolean camelToUnderline) {
        if (beanObject == null) {
            return null;
        }

        Class<T> beanType = (Class<T>) beanObject.getClass();
        BeanInfo beanInfo = ReflectionUtils.getBeanInfo(beanType); // 获取类属性
        if (beanInfo == null) {
            return null;
        }

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        String propertyName;
        Object value;
        Map mapObject = new HashMap();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            propertyName = propertyDescriptor.getName();
            value = ReflectionUtils.getMethodValue(beanObject, propertyDescriptor.getReadMethod());

            if (camelToUnderline) {
                propertyName = StringNameUtils.camelToUnderline(propertyName);
            }

            mapObject.put(propertyName, value);
        }

        return mapObject;
    }

    /**
     * Map 转成 Bean
     * @param beanType
     * @param mapObject
     * @param <T>
     * @return
     */
    public static <T> T mapToBean(Class<T> beanType, Map mapObject) {
        if (beanType == null || mapObject == null) {
            return null;
        }

        T instance = ReflectionUtils.newInstance(beanType); // 创建 JavaBean 对象

        if (CollectionUtils.isEmpty(mapObject)) {
            return instance;
        }

        BeanInfo beanInfo = ReflectionUtils.getBeanInfo(beanType); // 获取类属性
        if (beanInfo == null) {
            return null;
        }

        Map<String, PropertyDescriptor> propertyDescriptorMap = ReflectionUtils.propertyDescriptorMap(beanInfo);

        String key;
        String propertyName;
        PropertyDescriptor propertyDescriptor;
        Object value;
        for (Object keyObject : mapObject.keySet()) {
            if (keyObject == null) {
                continue;
            }

            key = String.valueOf(keyObject).trim();
            propertyName = StringNameUtils.underlineToCamel(key);
            propertyDescriptor = propertyDescriptorMap.get(propertyName);

            value = cast(mapObject.get(key), propertyDescriptor);

            setMethodValue(instance, propertyDescriptor, value);
        }

        return instance;
    }

    private static Object cast(Object value, PropertyDescriptor propertyDescriptor) {
        if (value == null || propertyDescriptor == null) {
            return null;
        }

        Class propertyClazz = propertyDescriptor.getPropertyType();

        if (value.getClass().equals(propertyClazz)) {
            return value;
        }

        return CastUtils.cast(value, propertyClazz);
    }

    private static <T> void setMethodValue(T instance, PropertyDescriptor propertyDescriptor, Object value) {
        if (instance == null || propertyDescriptor == null) {
            return ;
        }
        ReflectionUtils.setMethodValue(instance, propertyDescriptor.getWriteMethod(), value);
    }

}