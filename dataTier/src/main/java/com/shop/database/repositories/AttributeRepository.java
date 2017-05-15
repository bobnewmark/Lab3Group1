package com.shop.database.repositories;

import com.shop.database.entities.Attribute;
import com.shop.database.entities.ObjectType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by said on 09.05.2017.
 */
public interface AttributeRepository extends JpaRepository<Attribute, Integer > {

    Attribute findById(int id);
    List<Attribute> findByName(String name);
    List<Attribute> findByObjectType(ObjectType objectType);
}
