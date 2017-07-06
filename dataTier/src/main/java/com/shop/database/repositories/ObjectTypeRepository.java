package com.shop.database.repositories;

import com.shop.database.entities.ObjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <code>ObjectTypeRepository</code> is a repository class that manages work with object types.
 */
@Repository
public interface ObjectTypeRepository extends JpaRepository<ObjectType, Integer> {

    ObjectType findById(int id);
    ObjectType findByName(String name);
}
