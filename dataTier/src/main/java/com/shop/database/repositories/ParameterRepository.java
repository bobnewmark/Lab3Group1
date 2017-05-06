package com.shop.database.repositories;

import com.shop.database.entities.Parameter;

import java.util.List;

/**
 * Created by said on 06.05.2017.
 */
public interface ParameterRepository extends Repo<Parameter> {
    int countRows();
    List<Parameter> findByObjectId(int objectId);
    List<Parameter> findByAttributeId(int attributeId);
    List<Parameter> findByValue(String value);
}
