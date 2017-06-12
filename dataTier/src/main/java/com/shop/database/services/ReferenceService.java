package com.shop.database.services;

import com.shop.database.entities.Attribute;
import com.shop.database.entities.Object;
import com.shop.database.entities.Reference;
import com.sun.org.apache.regexp.internal.RE;

import java.util.List;

/**
 * Created by said on 07.05.2017.
 */
public interface ReferenceService {
    void save(Reference reference);
    List<Reference> findByObject(Object object);
    List<Reference> findByRefObject(Object refObject);
    List<Reference> findByAttribute(Attribute attribute);
    List<Reference> findByName(String name);
    Reference findById(int id);
    List<Reference> findByObjectAndRefObject(Object object, Object refObject);
    void delete(Reference reference);


}
