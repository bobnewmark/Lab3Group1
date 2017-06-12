package com.shop.database.repositories;

import com.shop.database.entities.Attribute;
import com.shop.database.entities.Object;
import com.shop.database.entities.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by said on 09.05.2017.
 */
@Repository
public interface ReferenceRepository extends JpaRepository<Reference, Integer> {

    List<Reference> findByObject(Object object);
    List<Reference> findByRefObject(Object refObject);
    List<Reference> findByAttribute(Attribute attribute);
    List<Reference> findByName(String name);
    Reference findById(int id);
    List<Reference> findByObjectAndRefObject(Object object, Object refObject);



}
