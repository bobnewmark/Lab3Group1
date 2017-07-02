package com.shop.database.services;

import com.shop.database.entities.Attribute;
import com.shop.database.entities.Parameter;

import java.util.List;

/**
 * <code>ParameterService</code> is an interface for working with parameter entities in database.
 */
public interface ParameterService {
    Parameter save(Parameter parameter);
    List<Parameter> findByObject(Object object);
    List<Parameter> findByAttribute(Attribute attribute);
    List<Parameter> findByValue(String value);
    Parameter findByObjectAndAttribute(int ob_id, int attr_id);
}
