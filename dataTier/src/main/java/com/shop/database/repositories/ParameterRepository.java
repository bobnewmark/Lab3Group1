package com.shop.database.repositories;

import com.shop.database.entities.Attribute;
import com.shop.database.entities.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by said on 09.05.2017.
 */
public interface ParameterRepository extends JpaRepository<Parameter, Parameter.Key> {

    List<Parameter> findByObject(Object object);
    List<Parameter> findByAttribute(Attribute attribute);
    List<Parameter> findByValue(String value);
}
