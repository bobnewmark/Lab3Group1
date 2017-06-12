package com.shop.database.repositories;

import com.shop.database.entities.ObjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by said on 09.05.2017.
 */
@Repository
public interface ObjectTypeRepository extends JpaRepository<ObjectType, Integer> {

    ObjectType findById(int id);
    ObjectType findByName(String name);
}
