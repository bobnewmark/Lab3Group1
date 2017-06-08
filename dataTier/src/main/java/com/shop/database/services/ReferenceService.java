package com.shop.database.services;

import com.shop.database.entities.Attribute;
import com.shop.database.entities.Object;
import com.shop.database.entities.Reference;

import java.util.List;

/**
 * Created by said on 07.05.2017.
 */
public interface ReferenceService {
    void save(Reference attribute);
    List<Reference> findByObject(Object object);
    List<Reference> findByRefObject(Object refObject);
    List<Reference> findByAttribute(Attribute attribute);
    List<Reference> findByName(String name);
}
