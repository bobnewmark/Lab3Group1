package com.shop.database.services;

import com.shop.database.entities.Object;
import com.shop.database.entities.ObjectType;
import com.shop.database.exceptions.RegistrationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * <code>ObjectService</code> is an interface for working with object entities in database.
 */
public interface ObjectService {
    Object save(Object object) throws RegistrationException;
    Object findById(int id);
    List<Object> findByName(String name);
    List<Object> findByObjectType(ObjectType objectType);
    Page<Object> findByNameContaining(String keyword, Pageable pageable);
    List<Object> findByNameAttrAndObjectType(String name, String otName, String value);
    List<Object> findByParent(Object object);
    void delete(int id);
    List<Object> getObjectByAttribute(String typeName, String name, Pageable pageable);
    Page<Object> getObjectByTypes(List<String> names, Pageable pageable);
    Page<Object> getObjectByType(String name, Pageable pageable);
    Page<Object> getAllProducts(Pageable pageable);
}
