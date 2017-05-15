package com.shop.database.repositories;

import com.shop.database.entities.Object;
import com.shop.database.entities.ObjectType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by said on 09.05.2017.
 */
public interface ObjectRepository extends JpaRepository<Object, Integer> {

    Object findById(int id);
    List<Object> findByName(String name);
    List<Object> findByObjectType(ObjectType objectType);
}
