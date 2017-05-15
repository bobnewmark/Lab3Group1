package com.shop.database.repositories;

import com.shop.database.entities.Object;
import com.shop.database.entities.Reference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by said on 09.05.2017.
 */
public interface ReferenceRepository extends JpaRepository<Reference, Reference.Key> {

    List<Reference> findByObject(Object object);
    List<Reference> findByRefObject(Object refObject);
    List<Reference> findByName(String name);
}
