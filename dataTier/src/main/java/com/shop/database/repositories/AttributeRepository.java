package com.shop.database.repositories;

import com.shop.database.entities.Attribute;
import com.shop.database.entities.ObjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <code>AttributeRepository</code> is a repository class that manages work with attributes.
 */
@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Integer > {

    Attribute findById(int id);
    List<Attribute> findByName(String name);
    List<Attribute> findByObjectType(ObjectType objectType);
    Attribute findByNameAndObjectType (String name, ObjectType objectType);
}
