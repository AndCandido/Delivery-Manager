package com.productdelivery.core.application.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

public class CopyPropertiesUtil {

    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullProperties(source));
    }

    public static String[] getNullProperties(Object source) {
        BeanWrapper src = new BeanWrapperImpl(source);
        Set<String> nullProperties = new HashSet<>();

        for (PropertyDescriptor propertyDescriptor : src.getPropertyDescriptors()) {
            String propertyName = propertyDescriptor.getName();
            if(src.getPropertyValue(propertyName) == null) {
                nullProperties.add(propertyName);
            }
        }

        String[] result = new String[nullProperties.size()];
        return nullProperties.toArray(result);
    }
}
