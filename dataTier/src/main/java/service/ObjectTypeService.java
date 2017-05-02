package service;


import entity.ObjectType;

import java.util.List;

/**
 * Created by said on 02.05.2017.
 */

public interface ObjectTypeService {

    void addObjectType(ObjectType objectType);
    void deleteObjectType(ObjectType objectType);
    ObjectType getObjectTypeById(int id);
    void editObjectType(ObjectType objectType);
    List<ObjectType> getAllObjectTypes();


}
