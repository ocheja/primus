/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.data.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */
@Converter
public class BooleanConverter implements AttributeConverter<Object, Object>{

    @Override
    public Object convertToDatabaseColumn(Object attribute) {
        boolean active = (Boolean) attribute;
        if(!active)
            return 0;
        else
            return 1;
    }

    @Override
    public Object convertToEntityAttribute(Object dbData) {
        Integer status = (Integer) dbData;
        if(status == 0)
            return false;
        else
            return true;
    }
    
}
