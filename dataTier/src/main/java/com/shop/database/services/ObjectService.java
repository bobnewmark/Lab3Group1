package com.shop.database.services;

import com.shop.database.entities.Object;
import com.shop.database.entities.ObjectType;
import com.shop.database.exceptions.RegistrationException;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by said on 06.05.2017.
 */
public interface ObjectService {
    void save(Object object) throws RegistrationException;
    Object findById(int id);
    List<Object> findByName(String name);
    List<Object> findByObjectType(ObjectType objectType);
    List<Object> findByNameContaining(String keyword);
    List<Object> findByNameAttrAndObjectType(String name, String otName, String value);
    List<Object> findByParent(Object object);
    void delete(int id);
    Object findByLogin(String login);
}
