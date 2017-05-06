package com.shop.database.repositories;

import com.shop.database.entities.Reference;

import java.util.List;

/**
 * Created by said on 06.05.2017.
 */
public interface ReferenceRepository extends Repo<Reference> {

    int countRows();
    List<Reference> findByObjectId(int objectId);
    List<Reference> findByReferenceId(int referenceId);
    List<Reference> findByName(int name);
}
