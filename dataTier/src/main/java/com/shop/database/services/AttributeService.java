package com.shop.database.services;

import com.shop.database.entities.Attribute;
import com.shop.database.entities.ObjectType;

import java.util.List;

/**
 * <code>AttributeService</code> is an interface for working with attribute entities in database.
 */
public interface AttributeService {
    Attribute save(Attribute attribute);
    Attribute findById(int id);
    List<Attribute> findByName(String name);
    List<Attribute> findByObjectType(ObjectType objectType);
    Attribute findByNameAndObjectType (String name, ObjectType objectType);
}
